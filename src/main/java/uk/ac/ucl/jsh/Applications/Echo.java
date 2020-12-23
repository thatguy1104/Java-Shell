package uk.ac.ucl.jsh.Applications;

import uk.ac.ucl.jsh.Jsh;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Echo implements Application {

    @Override
    public String mainExec(ArrayList<String> args, String currentDirectory, InputStream input, OutputStream output) throws IOException {
        String message = argCheck(args);
        if (!message.equals("nothing")) {
            throwError(message, output);
        } else {
            return exec(args, currentDirectory, input, output);
        }
        return "";
    }

    @Override
    public String exec(ArrayList<String> args, String currentDirectory, InputStream input, OutputStream output) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(output);
        writeOut(args, writer);
        return currentDirectory;
    }

    private void writeOut(ArrayList<String> args, OutputStreamWriter writer) throws IOException {
        boolean atLeastOnePrinted = false;
        for (String arg : args) {
            writer.write(arg + " ");
            writer.flush();
            atLeastOnePrinted = true;
        }
        if (atLeastOnePrinted) {
            writer.write(Jsh.lineSeparator);
            writer.flush();
        }
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
