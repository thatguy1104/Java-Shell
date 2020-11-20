package uk.ac.ucl.jsh.Applications;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Unsafe implements Application {
    private OutputStreamWriter writer;
    private Application application;

    @Override
    public String mainExec(ArrayList<String> args, String currentDirectory, OutputStream output) throws IOException {
        writer = new OutputStreamWriter(output);
        String message = argCheck(args);
        if (!message.equals("nothing")) {
            throwError(message, output);
        } else {
            return exec(args, currentDirectory);
        }
        return currentDirectory;
    }

    @Override
    public String exec(ArrayList<String> args, String currDir) throws IOException {
        writer.write("unsafe exec");
        writer.write(System.getProperty("line.separator"));
        writer.flush();
        return application.exec(args, currDir);
    }

    @Override
    public String argCheck(ArrayList<String> args) {
        return application.argCheck(args);
    }

    @Override
    public void throwError(String message, OutputStream output) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(output);
        writer.write("_" + message);
        writer.write(System.getProperty("line.separator"));
        writer.flush();
    }

    public Unsafe(Application application) {
        this.application = application;
    }

}