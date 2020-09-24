package at.sitsolutions.wordcount.domain;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class WordCounterTest {

    @Test
    public void requires_non_null_text() {
        WordCounter wordCounter = new WordCounter(Collections.emptyList());

        assertThatThrownBy(() ->
                wordCounter.countWords((String) null)
        ).isInstanceOf(IllegalArgumentException.class);

        //noinspection unchecked,rawtypes
        assertThatThrownBy(() ->
                wordCounter.countWords((List) null)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void empty_text_has_0_words() {
        WordCounter wordCounter = new WordCounter(Collections.emptyList());

        long wordCount = wordCounter.countWords("");

        assertThat(wordCount).isEqualTo(0L);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "y", "z", "A", "B", "Y", "Z"})
    public void single_valid_character_text_has_1_word(String text) {
        WordCounter wordCounter = new WordCounter(Collections.emptyList());

        long wordCount = wordCounter.countWords(text);

        assertThat(wordCount).isEqualTo(1L);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "!", "ä", "\t", "\u2202"})
    public void single_invalid_character_text_has_0_words(String text) {
        WordCounter wordCounter = new WordCounter(Collections.emptyList());

        long wordCount = wordCounter.countWords(text);

        assertThat(wordCount).isEqualTo(0L);
    }

    @Test
    public void words_must_not_contain_any_invalid_character() {
        WordCounter wordCounter = new WordCounter(Collections.emptyList());

        long wordCount = wordCounter.countWords("a1 b 3c 4");

        assertThat(wordCount).isEqualTo(1L);
    }

    @Test
    public void stop_words_are_not_counted() {
        List<String> stopWords = Arrays.asList("the", "a", "on", "off");
        WordCounter wordCounter = new WordCounter(stopWords);

        long wordCount = wordCounter.countWords("Mary had a little lamb");

        assertThat(wordCount).isEqualTo(4L);
    }

    @Test
    public void strings_in_separate_lines_are_considered_separate_words() {
        WordCounter wordCounter = new WordCounter(Collections.emptyList());

        long wordCount = wordCounter.countWords(Lists.newArrayList("Mary had", "a little", "lamb"));

        assertThat(wordCount).isEqualTo(5L);
    }
}
