package at.sitsolutions.wordcount.io.cli;

import java.util.Scanner;

public class SystemInputReader implements InputReader {

    private final OutputPrinter outputPrinter;

    public SystemInputReader(OutputPrinter outputPrinter) {
        this.outputPrinter = outputPrinter;
    }

    @Override
    public String readText() {
        outputPrinter.print("Enter text: ");

        Scanner in = new Scanner(java.lang.System.in);
        return in.nextLine();
    }
}
