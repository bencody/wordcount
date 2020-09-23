package at.sitsolutions.wordcount.domain;

import at.sitsolutions.wordcount.util.RegexpUtils;

import java.util.regex.Pattern;

public class WordCounter {

    private static final Pattern WORD_REGEXP = Pattern.compile("(\\b[a-zA-Z]+\\b)", Pattern.UNICODE_CHARACTER_CLASS);

    public long countWords(String text) {
        if (text == null) {
            throw new IllegalArgumentException();
        }

        RegexpUtils.streamMatches(WORD_REGEXP, text).forEach((w) -> {
            System.out.println("word: " + w);
        });

        return RegexpUtils.streamMatches(WORD_REGEXP, text).count();
    }
}
