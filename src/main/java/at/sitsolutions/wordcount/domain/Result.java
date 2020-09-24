package at.sitsolutions.wordcount.domain;

public class Result {
    public final long totalCount;
    public final long uniqueCount;
    public final double averageWordLength;

    public Result(long totalCount, long uniqueCount, double averageWordLength) {
        this.totalCount = totalCount;
        this.uniqueCount = uniqueCount;
        this.averageWordLength = averageWordLength;
    }
}
