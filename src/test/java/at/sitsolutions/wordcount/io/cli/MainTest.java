package at.sitsolutions.wordcount.io.cli;

import at.sitsolutions.wordcount.test.MockSystemUtils;
import org.junit.jupiter.api.Test;

import java.io.OutputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class MainTest {

    @Test
    public void reads_text_input_and_outputs_word_count() {
        OutputStream outputStream = MockSystemUtils.setOut();
        MockSystemUtils.setIn("Mary had a little lamb");

        Main.main(new String[]{});

        assertThat(outputStream.toString()).isEqualTo("Enter text: Number of words: 5");
    }
}
