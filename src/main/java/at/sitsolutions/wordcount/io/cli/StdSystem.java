package at.sitsolutions.wordcount.io.cli;

import java.util.Scanner;

public class StdSystem implements System {

    @Override
    public String readLine() {
        Scanner in = new Scanner(java.lang.System.in);
        return in.nextLine();
    }

    @Override
    public void print(String text) {
        java.lang.System.out.print(text);
    }
}
