package at.sitsolutions.wordcount.domain;

public class Result {
    public final long totalCount;
    public final long uniqueCount;

    public Result(long totalCount, long uniqueCount) {
        this.totalCount = totalCount;
        this.uniqueCount = uniqueCount;
    }
}
