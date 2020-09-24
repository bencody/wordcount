package at.sitsolutions.wordcount.io.cli;

import at.sitsolutions.wordcount.domain.WordCounter;
import at.sitsolutions.wordcount.util.FileUtils;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        CliService cliService = createService();

        cliService.run();
    }

    private static CliService createService() throws IOException {
        List<String> stopWords = FileUtils.readLines("src/main/resources/stopwords.txt");
        WordCounter wordCounter = new WordCounter(stopWords);
        System system = new StdSystem();
        return new CliService(system, wordCounter);
    }
}
