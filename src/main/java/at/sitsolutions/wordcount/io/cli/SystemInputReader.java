package at.sitsolutions.wordcount.io.cli;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SystemInputReader implements InputReader {

    @Override
    public List<String> readLines() {
        Scanner in = new Scanner(java.lang.System.in);
        return Collections.singletonList(in.nextLine());
    }
}
