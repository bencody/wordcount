package at.sitsolutions.wordcount.io.cli;

import at.sitsolutions.wordcount.domain.Result;
import at.sitsolutions.wordcount.domain.WordCounter;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;

public class CliService implements Callable<Void> {

    private final InputReader inputReader;
    private final OutputPrinter outputPrinter;
    private final WordCounter wordCounter;
    private final boolean printIndex;

    public CliService(InputReader inputReader, OutputPrinter outputPrinter, WordCounter wordCounter, boolean printIndex) {
        this.inputReader = inputReader;
        this.outputPrinter = outputPrinter;
        this.wordCounter = wordCounter;
        this.printIndex = printIndex;
    }

    @Override
    public Void call() throws Exception {
        outputPrinter.print("Enter text: ");

        List<String> text = inputReader.readLines();
        Result result = wordCounter.countWords(text);

        String resultMessage = String.format(Locale.US, "Number of words: %s, unique: %s; average word length: %.2f characters",
                result.totalCount, result.uniqueCount, result.averageWordLength);
        outputPrinter.println(resultMessage);

        if (this.printIndex) {
            outputPrinter.println("Index:");
            for (String word : result.words) {
                outputPrinter.println(word);
            }
        }

        return null;
    }
}
