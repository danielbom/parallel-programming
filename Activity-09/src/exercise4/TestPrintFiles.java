package exercise4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestPrintFiles {
    static Path dir = Path.of("/home/daniel/Downloads");

    public static void main(String[] args) throws IOException {
        PrintFiles p = new PrintFiles();
        Files.walkFileTree(dir, p);
    }
}
