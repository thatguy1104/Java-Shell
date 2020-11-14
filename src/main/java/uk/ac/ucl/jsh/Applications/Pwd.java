package uk.ac.ucl.jsh.Applications;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Pwd implements Application {

    @Override
    public String exec(ArrayList<String> args, String currDir, OutputStream output) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(output);

        writer.write(currDir);
        writer.write(System.getProperty("line.separator"));
        writer.flush();

        return currDir;
    }

    public String argCheck(ArrayList<String> args) {return "nothing";}

    public void throwError(String message, OutputStream output) throws IOException {
        throw new RuntimeException(message);
    }
}