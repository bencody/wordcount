package at.sitsolutions.wordcount.domain;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Options {

    public final List<String> stopWords;
    public final Optional<List<String>> dictionary;
    public final boolean printIndex;

    public Options(List<String> stopWords, Optional<List<String>> dictionary, boolean printIndex) {
        this.stopWords = Collections.unmodifiableList(stopWords);
        this.dictionary = dictionary.map(Collections::unmodifiableList);
        this.printIndex = printIndex;
    }
}
