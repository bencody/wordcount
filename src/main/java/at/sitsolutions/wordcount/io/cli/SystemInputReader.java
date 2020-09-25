package at.sitsolutions.wordcount.io.cli;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

public class SystemInputReader implements InputReader {

    private final OutputPrinter outputPrinter;
    private final Scanner scanner;

    public SystemInputReader(OutputPrinter outputPrinter) {
        this.outputPrinter = outputPrinter;
        scanner = new Scanner(java.lang.System.in);
    }

    @Override
    public Optional<String> readNext() {
        outputPrinter.print("Enter text: ");

        try {
            String nextLine = scanner.nextLine();
            return nextLine.trim().isEmpty()
                    ? Optional.empty()
                    : Optional.of(nextLine);
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }
}
