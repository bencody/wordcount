package at.sitsolutions.wordcount.io.cli;

import at.sitsolutions.wordcount.test.MockSystemUtils;
import org.junit.jupiter.api.Test;

import java.io.OutputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class MainTest {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    public void reads_text_input_and_outputs_word_count_excluding_stop_words_from_sysin() throws Exception {
        OutputStream outputStream = MockSystemUtils.setOut();
        MockSystemUtils.setIn("Mary had a little lamb");

        Main.main(new String[]{});

        assertThat(outputStream.toString()).isEqualTo("Enter text: Number of words: 4, unique: 4; average word length: 4.25 characters" + LINE_SEPARATOR);
    }

    @Test
    public void reads_text_input_and_outputs_word_count_excluding_stop_words_from_file() throws Exception {
        OutputStream outputStream = MockSystemUtils.setOut();

        Main.main(new String[]{"src/test/resources/mytext.txt"});

        assertThat(outputStream.toString()).isEqualTo("Enter text: Number of words: 4, unique: 4; average word length: 4.25 characters" + LINE_SEPARATOR);
    }

    @Test
    public void index_is_printed_with_line_separators() throws Exception {
        OutputStream outputStream = MockSystemUtils.setOut();
        MockSystemUtils.setIn("Mary had a little lamb");

        Main.main(new String[]{"-index"});

        assertThat(outputStream.toString()).isEqualTo("Enter text: Number of words: 4, unique: 4; average word length: 4.25 characters" + LINE_SEPARATOR +
                "Index:" + LINE_SEPARATOR +
                "Mary" + LINE_SEPARATOR +
                "had" + LINE_SEPARATOR +
                "little" + LINE_SEPARATOR +
                "lamb" + LINE_SEPARATOR
        );
    }
}
