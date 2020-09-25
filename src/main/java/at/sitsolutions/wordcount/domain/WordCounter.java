package at.sitsolutions.wordcount.domain;

import at.sitsolutions.wordcount.util.RegexpUtils;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WordCounter {

    private static final Pattern WORD_REGEXP = Pattern.compile("(\\b[a-zA-Z-]+\\b)", Pattern.UNICODE_CHARACTER_CLASS);

    public final Options options;

    public WordCounter(Options options) {
        this.options = options;
    }

    public Result countWords(String text) {
        if (text == null) {
            throw new IllegalArgumentException();
        }

        List<Word> words = RegexpUtils.streamMatches(WORD_REGEXP, text)
                .filter((word) -> !options.stopWords.contains(word))
                .map(word -> {
                    boolean known = options.dictionary.isPresent() && options.dictionary.get().contains(word);
                    return new Word(word, known);
                })
                .collect(Collectors.toList());
        return new Result(words);
    }
}
