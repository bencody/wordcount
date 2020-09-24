package at.sitsolutions.wordcount.io.cli;

import java.io.IOException;
import java.util.List;

public interface InputReader {

    List<String> readLines() throws IOException;
}
