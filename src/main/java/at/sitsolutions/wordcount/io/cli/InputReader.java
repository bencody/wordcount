package at.sitsolutions.wordcount.io.cli;

import java.io.IOException;

public interface InputReader {

    String readText() throws IOException;
}
