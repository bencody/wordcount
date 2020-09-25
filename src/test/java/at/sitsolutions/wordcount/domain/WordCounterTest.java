package at.sitsolutions.wordcount.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class WordCounterTest {

    @Test
    public void requires_non_null_text() {
        WordCounter wordCounter = new WordCounter(new Options(Collections.emptyList(), Optional.empty(), false));

        assertThatThrownBy(() ->
                wordCounter.countWords(null)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void empty_text_has_0_words() {
        WordCounter wordCounter = new WordCounter(new Options(Collections.emptyList(), Optional.empty(), false));

        Result result = wordCounter.countWords("");

        assertThat(result.words).isEmpty();
        assertThat(result.totalCount).isEqualTo(0L);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "y", "z", "A", "B", "Y", "Z"})
    public void single_valid_character_text_has_1_word(String text) {
        WordCounter wordCounter = new WordCounter(new Options(Collections.emptyList(), Optional.empty(), false));

        Result result = wordCounter.countWords(text);

        assertThat(result.words).extracting(word -> word.value).containsExactly(text);
        assertThat(result.totalCount).isEqualTo(1L);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "!", "ä", "\t", "\u2202"})
    public void single_invalid_character_text_has_0_words(String text) {
        WordCounter wordCounter = new WordCounter(new Options(Collections.emptyList(), Optional.empty(), false));

        Result result = wordCounter.countWords(text);

        assertThat(result.words).isEmpty();
        assertThat(result.totalCount).isEqualTo(0L);
    }

    @Test
    public void words_must_not_contain_any_invalid_character() {
        WordCounter wordCounter = new WordCounter(new Options(Collections.emptyList(), Optional.empty(), false));

        Result result = wordCounter.countWords("a1 b 3c 4");

        assertThat(result.words).extracting(word -> word.value).containsExactly("b");
        assertThat(result.totalCount).isEqualTo(1L);
    }

    @Test
    public void stop_words_are_not_counted() {
        List<String> stopWords = Arrays.asList("the", "a", "on", "off");
        WordCounter wordCounter = new WordCounter(new Options(stopWords, Optional.empty(), false));

        Result result = wordCounter.countWords("Mary had a little lamb");

        assertThat(result.words).extracting(word -> word.value).containsExactly("Mary", "had", "little", "lamb");
        assertThat(result.totalCount).isEqualTo(4L);
    }

    @Test
    public void average_word_length_is_based_on_all_words_and_not_unique_words() {
        List<String> stopWords = Arrays.asList("the", "a", "on", "off");
        WordCounter wordCounter = new WordCounter(new Options(stopWords, Optional.empty(), false));

        Result result = wordCounter.countWords("Humpty-Dumpty sat on a wall. Humpty-Dumpty had a great fall.");

        assertThat(result.totalCount).isEqualTo(7L);
        assertThat(result.uniqueCount).isEqualTo(6L);
        assertThat(result.averageLength).isEqualTo((45d / 7)); // avg(13, 3, 4, 13, 3, 5, 4) = 45/7
    }

    @Test
    public void without_a_dictionary_all_words_are_unknown() {
        WordCounter wordCounter = new WordCounter(new Options(Collections.emptyList(), Optional.empty(), false));

        Result result = wordCounter.countWords("a b c");

        assertThat(result.words).containsExactly(
                new Word("a", false),
                new Word("b", false),
                new Word("c", false)
        );
        assertThat(result.totalCount).isEqualTo(3L);
        assertThat(result.uniqueCount).isEqualTo(3L);
        assertThat(result.unknownCount).isEqualTo(3);
    }

    @Test
    public void dictionary_is_used_to_mark_words_as_known() {
        List<String> stopWords = Arrays.asList("the", "a", "on", "off");
        Optional<List<String>> dictionary = Optional.of(Arrays.asList("sat", "on", "a", "wall", "had", "great", "fall"));
        WordCounter wordCounter = new WordCounter(new Options(stopWords, dictionary, false));

        Result result = wordCounter.countWords("Humpty-Dumpty sat on a wall. Humpty-Dumpty had a great fall.");

        assertThat(result.words).containsExactly(
                new Word("Humpty-Dumpty", false),
                new Word("sat", true),
                new Word("wall", true),
                new Word("Humpty-Dumpty", false),
                new Word("had", true),
                new Word("great", true),
                new Word("fall", true)
        );
    }

    @Test
    public void unknown_word_count_is_based_on_unique_words() {
        Optional<List<String>> dictionary = Optional.of(Arrays.asList("sat", "on", "a", "wall", "had", "great", "fall"));
        WordCounter wordCounter = new WordCounter(new Options(Collections.emptyList(), dictionary, false));

        Result result = wordCounter.countWords("Humpty-Dumpty sat on a wall. Humpty-Dumpty had a great fall.");

        assertThat(result.totalCount).isEqualTo(10L);
        assertThat(result.uniqueCount).isEqualTo(8L);
        assertThat(result.unknownCount).isEqualTo(1L);
    }
}
