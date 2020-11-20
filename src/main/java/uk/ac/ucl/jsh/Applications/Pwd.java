package uk.ac.ucl.jsh.Applications;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Pwd implements Application {

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
    public String exec(ArrayList<String> args, String currDir) throws IOException {
        writer.write(currDir);
        writer.write(System.getProperty("line.separator"));
        writer.flush();

        return currDir;
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
