package at.sitsolutions.wordcount.domain;

import java.util.Objects;

public class Word {

    public final String value;
    public final boolean known;

    public Word(String value, boolean known) {
        this.value = value;
        this.known = known;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return known == word.known &&
                Objects.equals(value, word.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, known);
    }

    @Override
    public String toString() {
        return "Word{" +
                "value='" + value + '\'' +
                ", known=" + known +
                '}';
    }
}
