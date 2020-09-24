package at.sitsolutions.wordcount.domain;

import java.util.List;

public class Result {

    public final List<String> words;
    public final long totalCount;
    public final long uniqueCount;
    public final double averageWordLength;

    public Result(List<String> words, long totalCount, long uniqueCount, double averageWordLength) {
        this.words = words;
        this.totalCount = totalCount;
        this.uniqueCount = uniqueCount;
        this.averageWordLength = averageWordLength;
    }
}
