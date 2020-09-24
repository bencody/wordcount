package at.sitsolutions.wordcount.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class MockSystemUtils {

    public static OutputStream setOut() {
        OutputStream systemOutStream = new ByteArrayOutputStream();
        java.lang.System.setOut(new PrintStream(systemOutStream));
        return systemOutStream;
    }

    public static void setIn(String input) {
        ByteArrayInputStream systemInStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        java.lang.System.setIn(systemInStream);
    }
}