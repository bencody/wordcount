package at.sitsolutions.wordcount.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RegexpUtilsTest {

    @Nested
    @DisplayName("streamMatchingGroups")
    class StreamMatchingGroups {

        @Test
        public void requires_non_null_pattern() {
            assertThatThrownBy(() -> {
                RegexpUtils.streamMatches(null, "text");
            }).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        public void requires_non_null_text() {
            assertThatThrownBy(() -> {
                RegexpUtils.streamMatches(Pattern.compile("."), null);
            }).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        public void dot_matches_all_characters() {
            Supplier<Stream<String>> matchingGroups = createStreamMatchesSupplier(".", "text");

            long elementCount = matchingGroups.get().count();
            String joinedElements = matchingGroups.get().collect(Collectors.joining());

            assertThat(elementCount).isEqualTo(4L);
            assertThat(joinedElements).isEqualTo("text");
        }

        @Test
        public void range_matches_characters_in_range() {
            Supplier<Stream<String>> matchingGroups = createStreamMatchesSupplier("[abc]", "abcdef");

            long elementCount = matchingGroups.get().count();
            String joinedElements = matchingGroups.get().collect(Collectors.joining());

            assertThat(elementCount).isEqualTo(3L);
            assertThat(joinedElements).isEqualTo("abc");
        }
    }

    private static Supplier<Stream<String>> createStreamMatchesSupplier(String pattern, String text) {
        return () -> RegexpUtils.streamMatches(Pattern.compile(pattern), text);
    }


}
