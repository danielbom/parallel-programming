package exercise4;

import java.io.IOException;
import java.nio.file.Path;

public class TestWatchDir {
    static Path dir = Path.of("/home/daniel/Downloads");

    public static void main(String[] args) {
        boolean recursive = false;
        try {
            new WatchDir(dir, recursive).processEvents();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
