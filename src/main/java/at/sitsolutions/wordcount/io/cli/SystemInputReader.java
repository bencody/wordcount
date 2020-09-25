package at.sitsolutions.wordcount.io.cli;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SystemInputReader implements InputReader {

    private final OutputPrinter outputPrinter;

    public SystemInputReader(OutputPrinter outputPrinter) {
        this.outputPrinter = outputPrinter;
    }

    @Override
    public List<String> readLines() {
        outputPrinter.print("Enter text: ");

        Scanner in = new Scanner(java.lang.System.in);
        return Collections.singletonList(in.nextLine());
    }
}
