package at.sitsolutions.wordcount.io.cli;

import at.sitsolutions.wordcount.util.FileUtils;

import java.io.IOException;
import java.util.List;

public class FileInputReader implements InputReader {

    private final String filePath;

    public FileInputReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<String> readLines() throws IOException {
        return FileUtils.readLines(filePath);
    }
}
