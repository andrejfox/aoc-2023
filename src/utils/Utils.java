package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Utils {
    public static List<String> readInputFile() {
        try {
            return Files.readAllLines(Path.of("./src/input.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
