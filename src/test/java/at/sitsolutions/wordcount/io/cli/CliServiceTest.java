package at.sitsolutions.wordcount.io.cli;

import at.sitsolutions.wordcount.domain.WordCounter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CliServiceTest {

    @Test
    public void reads_text_input_and_outputs_word_count() {
        MockSystem system = new MockSystem("Mary had a little lamb");
        CliService cliService = new CliService(system, new WordCounter(Arrays.asList("the", "a", "on", "off")));

        cliService.run();

        assertThat(system.printLineCalls).containsExactly(
                "Enter text: ",
                "Number of words: 4"
        );
    }

    private static final class MockSystem implements System {

        private final String readLine;
        private final List<String> printLineCalls = new ArrayList<>();

        public MockSystem(String readLine) {
            this.readLine = readLine;
        }

        @Override
        public String readLine() {
            return readLine;
        }

        @Override
        public void print(String text) {
            printLineCalls.add(text);
        }
    }
}
