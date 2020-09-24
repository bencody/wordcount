package at.sitsolutions.wordcount.io.cli;

import at.sitsolutions.wordcount.domain.WordCounter;

public class Main {

    public static void main(String[] args) {
        CliService cliService = createService();

        cliService.run();
    }

    private static CliService createService() {
        WordCounter wordCounter = new WordCounter();
        System system = new StdSystem();
        return new CliService(system, wordCounter);
    }
}
