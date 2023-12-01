package Day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Puzzle1 {
    public static void main(String[] args) {
    }

    private static List<String> readInputFile() {
        try {
            return Files.readAllLines(Path.of("input.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
