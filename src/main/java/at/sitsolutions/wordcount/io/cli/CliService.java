package at.sitsolutions.wordcount.io.cli;

import at.sitsolutions.wordcount.domain.Result;
import at.sitsolutions.wordcount.domain.Word;
import at.sitsolutions.wordcount.domain.WordCounter;

import java.util.Locale;
import java.util.Optional;
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
        for (Optional<String> text = inputReader.readNext(); text.isPresent(); text = inputReader.readNext()) {
            Result result = wordCounter.countWords(text.get());
            printMainResults(result);
            printIndexResult(result);
            outputPrinter.println("");
        }
        return null;
    }

    private void printMainResults(Result result) {
        String resultMessage = String.format(Locale.US, "Number of words: %s, unique: %s; average word length: %.2f characters",
                result.totalCount, result.uniqueCount, result.averageWordLength);
        outputPrinter.println(resultMessage);
    }

    private void printIndexResult(Result result) {
        if (!wordCounter.options.printIndex) {
            return;
        }

        if (wordCounter.options.dictionary.isPresent()) {
            outputPrinter.println(String.format("Index (unknown: %s):", result.unknownWordCount));
            for (Word word : result.words) {
                outputPrinter.println(word.value + (word.known ? "" : "*"));
            }

        } else {
            outputPrinter.println("Index:");
            for (Word word : result.words) {
                outputPrinter.println(word.value);
            }
        }
    }
}
