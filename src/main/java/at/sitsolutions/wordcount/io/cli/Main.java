package at.sitsolutions.wordcount.io.cli;

import at.sitsolutions.wordcount.domain.WordCounter;
import at.sitsolutions.wordcount.util.FileUtils;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        CliService cliService = createService(args);

        cliService.call();
    }

    private static CliService createService(String[] args) throws IOException {
        MainArguments arguments = MainArguments.create(args);
        List<String> stopWords = FileUtils.readLines("src/main/resources/stopwords.txt");
        WordCounter wordCounter = new WordCounter(stopWords);
        InputReader inputReader = createInputReader(arguments);
        OutputPrinter outputPrinter = new SystemOutputPrinter();
        return new CliService(inputReader, outputPrinter, wordCounter);
    }

    private static InputReader createInputReader(MainArguments arguments) {
        return arguments.textInputFilePath != null
                ? new FileInputReader(arguments.textInputFilePath)
                : new SystemInputReader();
    }
}
