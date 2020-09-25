package at.sitsolutions.wordcount.io.cli;

import at.sitsolutions.wordcount.domain.Options;
import at.sitsolutions.wordcount.domain.WordCounter;
import at.sitsolutions.wordcount.util.FileUtils;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) throws Exception {
        CliService cliService = createService(args);

        cliService.call();
    }

    private static CliService createService(String[] args) throws IOException {
        MainArguments arguments = MainArguments.create(args);
        InputReader inputReader = createInputReader(arguments);
        OutputPrinter outputPrinter = new SystemOutputPrinter();
        WordCounter wordCounter = createWordCounter(arguments);
        return new CliService(inputReader, outputPrinter, wordCounter);
    }

    private static WordCounter createWordCounter(MainArguments arguments) throws IOException {
        List<String> stopWords = FileUtils.readLines("src/main/resources/stopwords.txt");
        List<String> dictionary = arguments.dictionaryFilePath == null
                ? null
                : FileUtils.readLines(arguments.dictionaryFilePath);
        Options options = new Options(stopWords, Optional.ofNullable(dictionary), arguments.printIndex);
        return new WordCounter(options);
    }

    private static InputReader createInputReader(MainArguments arguments) {
        return arguments.textInputFilePath != null
                ? new FileInputReader(arguments.textInputFilePath)
                : new SystemInputReader();
    }
}
