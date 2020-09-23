package at.sitsolutions.wordcount.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class WordCounterTest {

    @Test
    public void requires_non_null_text() {
        WordCounter wordCounter = new WordCounter();

        assertThatThrownBy(() -> {
            wordCounter.countWords(null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void empty_text_has_0_words() {
        WordCounter wordCounter = new WordCounter();

        long wordCount = wordCounter.countWords("");

        assertThat(wordCount).isEqualTo(0L);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "y", "z", "A", "B", "Y", "Z"})
    public void single_valid_character_text_has_1_word(String text) {
        WordCounter wordCounter = new WordCounter();

        long wordCount = wordCounter.countWords(text);

        assertThat(wordCount).isEqualTo(1L);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "!", "ä", "\t", "\u2202"})
    public void single_invalid_character_text_has_0_words(String text) {
        WordCounter wordCounter = new WordCounter();

        long wordCount = wordCounter.countWords(text);

        assertThat(wordCount).isEqualTo(0L);
    }

    @Test
    public void words_must_not_contain_any_invalid_character() {
        WordCounter wordCounter = new WordCounter();

        long wordCount = wordCounter.countWords("a b2 3c 4");

        assertThat(wordCount).isEqualTo(1L);
    }

    @Test
    public void mary_has_a_little_lamb_has_5_words() {
        WordCounter wordCounter = new WordCounter();

        long wordCount = wordCounter.countWords("Mary had a little lamb");

        assertThat(wordCount).isEqualTo(5L);
    }
}
