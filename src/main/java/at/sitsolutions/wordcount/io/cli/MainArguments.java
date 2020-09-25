package at.sitsolutions.wordcount.io.cli;

public class MainArguments {

    public final String textInputFilePath;
    public final String dictionaryFilePath;
    public final boolean printIndex;

    private MainArguments(String textInputFilePath, String dictionaryFilePath, boolean printIndex) {
        this.textInputFilePath = textInputFilePath;
        this.dictionaryFilePath = dictionaryFilePath;
        this.printIndex = printIndex;
    }

    public static MainArguments create(String[] args) {
        boolean printIndex = false;
        String textInputFilePath = null;
        String dictionaryFilePath = null;
        for (String arg : args) {
            boolean isKeyValue = arg.matches("^-(\\w+)=(.+)$");
            boolean isFlag = arg.matches("^-(\\w+)$");

            if (isFlag) {
                if (arg.equals("-index")) {
                    printIndex = true;
                }
            }
            else if (isKeyValue) {
                String[] keyValue = arg.split("=", 2);
                if (keyValue[0].equals("-dictionary")) {
                    dictionaryFilePath = keyValue[1];
                }
            } else {
                textInputFilePath = arg;
            }

        }
        return new MainArguments(textInputFilePath, dictionaryFilePath, printIndex);
    }
}
