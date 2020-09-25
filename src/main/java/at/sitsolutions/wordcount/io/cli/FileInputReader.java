package at.sitsolutions.wordcount.io.cli;

import at.sitsolutions.wordcount.util.FileUtils;

import java.io.IOException;

public class FileInputReader implements InputReader {

    private final String filePath;

    public FileInputReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String readText() throws IOException {
        return String.join("\n", FileUtils.readLines(filePath));
    }
}
