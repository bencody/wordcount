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

        Result result = wordCounter.countWords("");

        assertThat(result.totalCount).isEqualTo(0L);
        assertThat(result.uniqueCount).isEqualTo(0L);
        assertThat(result.averageWordLength).isEqualTo(0d);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "y", "z", "A", "B", "Y", "Z"})
    public void single_valid_character_text_has_1_word(String text) {
        WordCounter wordCounter = new WordCounter(Collections.emptyList());

        Result result = wordCounter.countWords(text);

        assertThat(result.totalCount).isEqualTo(1L);
        assertThat(result.uniqueCount).isEqualTo(1L);
        assertThat(result.averageWordLength).isEqualTo(1d);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "!", "ä", "\t", "\u2202"})
    public void single_invalid_character_text_has_0_words(String text) {
        WordCounter wordCounter = new WordCounter(Collections.emptyList());

        Result result = wordCounter.countWords(text);

        assertThat(result.totalCount).isEqualTo(0L);
        assertThat(result.uniqueCount).isEqualTo(0L);
        assertThat(result.averageWordLength).isEqualTo(0d);
    }

    @Test
    public void words_must_not_contain_any_invalid_character() {
        WordCounter wordCounter = new WordCounter(Collections.emptyList());

        Result result = wordCounter.countWords("a1 b 3c 4");

        assertThat(result.totalCount).isEqualTo(1L);
        assertThat(result.uniqueCount).isEqualTo(1L);
        assertThat(result.averageWordLength).isEqualTo(1d);
    }

    @Test
    public void stop_words_are_not_counted() {
        List<String> stopWords = Arrays.asList("the", "a", "on", "off");
        WordCounter wordCounter = new WordCounter(stopWords);

        Result result = wordCounter.countWords("Mary had a little lamb");

        assertThat(result.totalCount).isEqualTo(4L);
        assertThat(result.uniqueCount).isEqualTo(4L);
        assertThat(result.averageWordLength).isEqualTo(4.25d); // avg(4, 3, 6, 4) = 4.25
    }

    @Test
    public void strings_in_separate_lines_are_considered_separate_words() {
        WordCounter wordCounter = new WordCounter(Collections.emptyList());

        Result result = wordCounter.countWords(Lists.newArrayList("Mary had", "a little", "lamb"));

        assertThat(result.totalCount).isEqualTo(5L);
        assertThat(result.uniqueCount).isEqualTo(5L);
        assertThat(result.averageWordLength).isEqualTo(3.6d); // avg(4, 3, 1, 6, 4) = 4.25
    }

    @Test
    public void average_word_length_is_based_on_all_words_and_not_unique_words() {
        List<String> stopWords = Arrays.asList("the", "a", "on", "off");
        WordCounter wordCounter = new WordCounter(stopWords);

        Result result = wordCounter.countWords("Humpty-Dumpty sat on a wall. Humpty-Dumpty had a great fall.");

        assertThat(result.totalCount).isEqualTo(7L);
        assertThat(result.uniqueCount).isEqualTo(6L);
        assertThat(result.averageWordLength).isEqualTo((45d/7)); // avg(13, 3, 4, 13, 3, 5, 4) = 45/7
    }
}
