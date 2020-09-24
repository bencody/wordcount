package at.sitsolutions.wordcount.domain;

import at.sitsolutions.wordcount.util.RegexpUtils;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WordCounter {

    private static final Pattern WORD_REGEXP = Pattern.compile("(\\b[a-zA-Z]+\\b)", Pattern.UNICODE_CHARACTER_CLASS);

    private final List<String> stopWords;

    public WordCounter(List<String> stopWords) {
        this.stopWords = stopWords;
    }

    public Result countWords(String text) {
        if (text == null) {
            throw new IllegalArgumentException();
        }

        List<String> words = RegexpUtils.streamMatches(WORD_REGEXP, text)
                .filter((word) -> !stopWords.contains(word))
                .collect(Collectors.toList());

        long totalCount = words.size();
        long uniqueCount = words.stream().distinct().count();

        return new Result(totalCount, uniqueCount);
    }

    public Result countWords(List<String> textLines) {
        if (textLines == null) {
            throw new IllegalArgumentException();
        }
        String text = String.join("\n", textLines);
        return countWords(text);
    }
}
