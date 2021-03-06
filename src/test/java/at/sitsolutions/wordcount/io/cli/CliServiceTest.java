package at.sitsolutions.wordcount.io.cli;

import at.sitsolutions.wordcount.domain.Options;
import at.sitsolutions.wordcount.domain.WordCounter;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class CliServiceTest {

    private static final List<String> STOP_WORDS = Arrays.asList("the", "a", "on", "off");
    private static final Optional<List<String>> DICTIONARY = Optional.of(Arrays.asList("had", "a", "little"));

    @Test
    public void reads_text_input_and_outputs_word_count() throws Exception {
        InputReader inputReader = new MockInputReader("Mary had a little lamb");
        MockOutputPrinter outputPrinter = new MockOutputPrinter();
        WordCounter wordCounter = new WordCounter(new Options(STOP_WORDS, Optional.empty(), false));
        CliService cliService = new CliService(inputReader, outputPrinter, wordCounter);

        cliService.call();

        assertThat(outputPrinter.printLineCalls).containsExactly(
                "Number of words: 4, unique: 4; average word length: 4.25 characters",
                "" // Last empty line
        );
    }

    @Test
    public void index_is_only_printed_if_requested() throws Exception {
        InputReader inputReader = new MockInputReader("Mary had a little lamb");
        MockOutputPrinter outputPrinter = new MockOutputPrinter();
        WordCounter wordCounter = new WordCounter(new Options(STOP_WORDS, Optional.empty(), true));
        CliService cliService = new CliService(inputReader, outputPrinter, wordCounter);

        cliService.call();

        assertThat(outputPrinter.printLineCalls).containsExactly(
                "Number of words: 4, unique: 4; average word length: 4.25 characters",
                "Index:",
                "Mary",
                "had",
                "little",
                "lamb",
                "" // Last empty line
        );
    }

    @Test
    public void known_words_are_only_printed_if_dictionary_is_defined() throws Exception {
        InputReader inputReader = new MockInputReader("Mary had a little lamb");
        MockOutputPrinter outputPrinter = new MockOutputPrinter();
        WordCounter wordCounter = new WordCounter(new Options(STOP_WORDS, DICTIONARY, true));
        CliService cliService = new CliService(inputReader, outputPrinter, wordCounter);

        cliService.call();

        assertThat(outputPrinter.printLineCalls).containsExactly(
                "Number of words: 4, unique: 4; average word length: 4.25 characters",
                "Index (unknown: 2):",
                "Mary*",
                "had",
                "little",
                "lamb*",
                "" // Last empty line
        );
    }

    private static final class MockInputReader implements InputReader {
        private final Stack<String> textStack = new Stack<>();

        public MockInputReader(String... texts) {
            for (String text : texts) {
                textStack.push(text);
            }
        }

        @Override
        public Optional<String> readNext() {
            return textStack.empty() ? Optional.empty() : Optional.of(textStack.pop());
        }
    }

    private static final class MockOutputPrinter implements OutputPrinter {

        private final List<String> printLineCalls = new ArrayList<>();

        public MockOutputPrinter() {
        }

        @Override
        public void print(String text) {
            printLineCalls.add(text);
        }

        @Override
        public void println(String text) {
            printLineCalls.add(text);
        }
    }
}
