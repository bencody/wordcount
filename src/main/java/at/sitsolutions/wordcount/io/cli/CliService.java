package at.sitsolutions.wordcount.io.cli;

import at.sitsolutions.wordcount.domain.WordCounter;

public class CliService implements Runnable {

    private final System system;
    private final WordCounter wordCounter;

    public CliService(System system, WordCounter wordCounter) {
        this.system = system;
        this.wordCounter = wordCounter;
    }

    @Override
    public void run() {
        system.print("Enter text: ");

        String text = system.readLine();
        long wordCount = wordCounter.countWords(text);

        system.print("Number of words: " + wordCount);
    }
}
