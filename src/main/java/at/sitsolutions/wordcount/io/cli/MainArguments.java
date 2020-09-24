package at.sitsolutions.wordcount.io.cli;

public class MainArguments {

    public final String textInputFilePath;

    private MainArguments(String textInputFilePath) {
        this.textInputFilePath = textInputFilePath;
    }

    public static MainArguments create(String[] args) {
        String textInputFilePath = args.length >= 1 ? args[0] : null;
        return new MainArguments(textInputFilePath);
    }
}
