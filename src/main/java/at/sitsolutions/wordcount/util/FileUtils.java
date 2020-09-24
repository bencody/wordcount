package at.sitsolutions.wordcount.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {
    private FileUtils() {
    }

    public static List<String> readLines(String path) throws IOException {
        return Files.lines(Paths.get(path)).collect(Collectors.toList());
    }
}
