package at.sitsolutions.wordcount.util;

import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class RegexpUtils {

    private RegexpUtils() {
    }

    public static Stream<String> streamMatches(Pattern pattern, String text) {
        if (pattern == null) {
            throw new IllegalArgumentException("pattern must not be null");
        }
        if (text == null) {
            throw new IllegalArgumentException("text must not be null");
        }
        Matcher matcher = pattern.matcher(text);
        return StreamSupport.stream(new MatcherSpliterator(matcher), false);
    }

    private static final class MatcherSpliterator extends Spliterators.AbstractSpliterator<String> {
        private final Matcher matcher;

        private MatcherSpliterator(Matcher matcher) {
            super(Long.MAX_VALUE, ORDERED | NONNULL);
            this.matcher = matcher;
        }

        public boolean tryAdvance(Consumer<? super String> action) {
            if (!matcher.find()) return false;
            action.accept(matcher.group());
            return true;
        }
    }
}
