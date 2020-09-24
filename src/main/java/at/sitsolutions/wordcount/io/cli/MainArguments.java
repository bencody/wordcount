package at.sitsolutions.wordcount.io.cli;

public class MainArguments {

    public final String textInputFilePath;
    public final boolean printIndex;

    private MainArguments(String textInputFilePath, boolean printIndex) {
        this.textInputFilePath = textInputFilePath;
        this.printIndex = printIndex;
    }

    public static MainArguments create(String[] args) {
        boolean printIndex = false;
        String textInputFilePath = null;
        for (String arg : args) {
            if (arg.equals("-index")) {
                printIndex = true;
            } else {
                textInputFilePath = arg;
            }

        }
        return new MainArguments(textInputFilePath, printIndex);
    }
}
