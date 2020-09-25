package at.sitsolutions.wordcount.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WordTest {

    @Test
    public void to_string_should_be_readable_in_case_test_assertions_fail() {
        Word word = new Word("v", true);

        String wordString = word.toString();

        assertThat(wordString).isEqualTo("Word{value='v', known=true}");
    }
}
