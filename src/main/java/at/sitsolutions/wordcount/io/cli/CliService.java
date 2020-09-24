package at.sitsolutions.wordcount.io.cli;

import at.sitsolutions.wordcount.domain.WordCounter;

import java.util.List;
import java.util.concurrent.Callable;

public class CliService implements Callable<Void> {

    private final InputReader inputReader;
    private final OutputPrinter outputPrinter;
    private final WordCounter wordCounter;

    public CliService(InputReader inputReader, OutputPrinter outputPrinter, WordCounter wordCounter) {
        this.inputReader = inputReader;
        this.outputPrinter = outputPrinter;
        this.wordCounter = wordCounter;
    }

    @Override
    public Void call() throws Exception {
        outputPrinter.print("Enter text: ");

        List<String> text = inputReader.readLines();
        long wordCount = wordCounter.countWords(text);

        outputPrinter.print("Number of words: " + wordCount);
        return null;
    }
}
