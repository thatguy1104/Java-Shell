package uk.ac.ucl.jsh.Applications;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Echo implements Application {

    private OutputStreamWriter writer;

    @Override
    public String mainExec(ArrayList<String> args, String currentDirectory, OutputStream output) throws IOException {
        writer = new OutputStreamWriter(output);
        String message = argCheck(args);
        if (message != "nothing") {
            throwError(message, output);
        } else {
            return exec(args, currentDirectory);
        }
        return "";
    }

    @Override
    public String exec(ArrayList<String> args, String currentDirectory) throws IOException {
        boolean atLeastOnePrinted = false;
        for (String arg : args) {
            writer.write(arg);
            writer.write(" ");
            writer.flush();
            atLeastOnePrinted = true;
        }
        if (atLeastOnePrinted) {
            writer.write(System.getProperty("line.separator"));
            writer.flush();
        }

        return currentDirectory;
    }

    @Override
    public String argCheck(ArrayList<String> args) {
        return "nothing";
    }

    @Override
    public void throwError(String message, OutputStream output) {
        throw new RuntimeException(message);
    }
}
