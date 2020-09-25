package at.sitsolutions.wordcount.io.cli;

import java.io.IOException;
import java.util.Optional;

/**
 * Reads inputs for the word counter. This reader supports single-input sources and multi-input sources.
 */
public interface InputReader {

    /**
     * @return an {@link Optional} that will either contain the next input or be empty if no more inputs exist.
     */
    Optional<String> readNext() throws IOException;
}
