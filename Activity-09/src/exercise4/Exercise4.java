package exercise4;

import java.io.File;
import java.nio.file.Path;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Faça um programa que periodicamente monitore mudanças em um conjunto de
 * arquivos especificados. Se ocorreram mudanças, o programa deve registrá-las
 * em um arquivo de log.
 * 
 * (Base) https://www.mkyong.com/java/java-how-to-list-all-files-in-a-directory/
 * (PrintFiles) https://docs.oracle.com/javase/tutorial/essential/io/walk.html
 * (Multiple callback functions)
 * https://stackoverflow.com/questions/27872387/can-a-java-lambda-have-more-than-1-parameter
 * 
 * @author daniel
 */
public class Exercise4 {

    static Path dir = Path.of("/home/daniel/Downloads");

    public static void main(String[] args) {
        ScheduledExecutorService schedule = Executors.newSingleThreadScheduledExecutor();

        DirectoryListener listener = new DirectoryListener(dir,
                (path, state) -> {
                    File file = path.toFile();
                    String prefix = file.isDirectory() ? "Directory" : "File";
                    switch (state) {
                        case CREATED:
                            System.out.println(prefix + " has been created '"
                                    + path + "'");
                            break;
                        case DELETED:
                            System.out.println(prefix + " has been deleted '"
                                    + path + "'");
                            break;
                        case CHANGED:
                            System.out.println(prefix + " has been changed '"
                                    + path + "'");
                            break;
                    }
                });

        schedule.scheduleAtFixedRate(listener, 0, 2, TimeUnit.SECONDS);
    }
}
