package at.sitsolutions.wordcount.io.cli;

import at.sitsolutions.wordcount.domain.WordCounter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CliServiceTest {

    @Test
    public void reads_text_input_and_outputs_word_count() throws Exception {
        InputReader inputReader = new MockInputReader(Collections.singletonList("Mary had a little lamb"));
        MockOutputPrinter outputPrinter = new MockOutputPrinter();
        CliService cliService = new CliService(inputReader, outputPrinter, new WordCounter(Arrays.asList("the", "a", "on", "off")));

        cliService.call();

        assertThat(outputPrinter.printLineCalls).containsExactly(
                "Enter text: ",
                "Number of words: 4"
        );
    }

    private static final class MockInputReader implements InputReader {
        private final List<String> readLine;

        public MockInputReader(List<String> readLine) {
            this.readLine = readLine;
        }

        @Override
        public List<String> readLines() {
            return readLine;
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
    }
}
