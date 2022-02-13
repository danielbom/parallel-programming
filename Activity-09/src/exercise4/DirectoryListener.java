package exercise4;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirectoryListener implements Runnable {

    private Path directory;
    private HashMap<Path, Long> modifications = new HashMap<Path, Long>();
    private Function<Path, State> consumer;

    enum State {
        DELETED, CREATED, CHANGED
    }

    @FunctionalInterface
    interface Function<One, Two> {
        public void apply(One one, Two two);
    }

    public DirectoryListener(Path directory, Function<Path, State> consumer) {
        this.directory = directory;
        this.consumer = consumer;
        startListen();
    }

    private void startListen() {
        try (Stream<Path> walk = Files.walk(directory)) {
            List<Path> list = walk.collect(Collectors.toList());
            list.forEach(p -> {
                long m = p.toFile().lastModified();
                modifications.put(p, m);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("Verifing changes...");
        try (Stream<Path> walk = Files.walk(directory)) {
            List<Path> list = walk.collect(Collectors.toList());
            List<Path> deleted = new ArrayList<Path>();
            for (Path p : list) {
                File f = p.toFile();
                long m = f.lastModified();
                long sm = modifications.getOrDefault(p, 0L);
                if (sm != m) {
                    if (sm == 0) {
                        consumer.apply(p, State.CREATED);
                    } else {
                        for (Map.Entry<Path, Long> values : modifications.entrySet()) {
                            Path path = values.getKey();
                            File file = path.toFile();
                            if (!file.exists())
                                deleted.add(path);
                        }
                        if (deleted.size() == 0)
                            consumer.apply(p, State.CHANGED);
                    }
                }
                modifications.put(p, m);
            }

            if (deleted.size() > 0) {
                deleted.forEach(p -> {
                    modifications.remove(p);
                    consumer.apply(p, State.DELETED);
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
