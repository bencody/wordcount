package at.sitsolutions.wordcount.io.cli;

public class SystemOutputPrinter implements OutputPrinter {

    @Override
    public void print(String text) {
        java.lang.System.out.print(text);
    }
}
