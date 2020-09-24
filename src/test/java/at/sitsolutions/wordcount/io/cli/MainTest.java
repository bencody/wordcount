package at.sitsolutions.wordcount.io.cli;

import at.sitsolutions.wordcount.test.MockSystemUtils;
import org.junit.jupiter.api.Test;

import java.io.OutputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class MainTest {

    @Test
    public void reads_text_input_and_outputs_word_count_excluding_stop_words_from_sysin() throws Exception {
        OutputStream outputStream = MockSystemUtils.setOut();
        MockSystemUtils.setIn("Mary had a little lamb");

        Main.main(new String[]{});

        assertThat(outputStream.toString()).isEqualTo("Enter text: Number of words: 4, unique: 4");
    }

    @Test
    public void reads_text_input_and_outputs_word_count_excluding_stop_words_from_file() throws Exception {
        OutputStream outputStream = MockSystemUtils.setOut();

        Main.main(new String[]{"src/test/resources/mytext.txt"});

        assertThat(outputStream.toString()).isEqualTo("Enter text: Number of words: 4, unique: 4");
    }
}
