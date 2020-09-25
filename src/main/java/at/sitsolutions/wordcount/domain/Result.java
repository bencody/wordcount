package at.sitsolutions.wordcount.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Result {

    public final List<Word> words;
    public final long totalCount;
    public final long uniqueCount;
    public final long unknownWordCount;
    public final double averageWordLength;

    public Result(List<Word> words) {
        this.words = Collections.unmodifiableList(words);
        List<Word> distinctWords = words.stream().distinct().collect(Collectors.toList());
        this.totalCount = words.size();
        this.uniqueCount = distinctWords.size();
        this.averageWordLength = words.stream().mapToInt(word -> word.value.length()).average().orElse(0d);
        this.unknownWordCount = distinctWords.stream().filter(word -> !word.known).count();
    }
}
