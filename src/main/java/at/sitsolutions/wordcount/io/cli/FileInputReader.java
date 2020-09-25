package at.sitsolutions.wordcount.io.cli;

import at.sitsolutions.wordcount.util.FileUtils;

import java.io.IOException;
import java.util.Optional;

public class FileInputReader implements InputReader {

    private final String filePath;
    private boolean wasRead = false;

    public FileInputReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Optional<String> readNext() throws IOException {
        if (wasRead) {
            return Optional.empty();
        }
        wasRead = true;
        return Optional.of(String.join("\n", FileUtils.readLines(filePath)));
    }
}
