package uk.ac.ucl.jsh.Applications;

import uk.ac.ucl.jsh.Jsh;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public abstract class Unsafe implements Application {
    private OutputStreamWriter writer;
    private Application application;

    @Override
    public String mainExec(ArrayList<String> args, String currentDirectory, InputStream input, OutputStream output) throws IOException {
        String message = argCheck(args);
        String appResult;
        if (!message.equals("nothing")) {
            throwError(message, output);
        } else {
            appResult = exec(args, currentDirectory, input, output);
            if (appResult.startsWith("ERROR")) {
                throwError(appResult.substring(6), output);
            } else {
                return appResult;
            }
        }
        return currentDirectory;
    }

    @Override
    public String exec(ArrayList<String> args, String currDir, InputStream input, OutputStream output) throws IOException {
        return application.exec(args, currDir, input, output);
    }

    @Override
    public String argCheck(ArrayList<String> args) {
        return application.argCheck(args);
    }

    @Override
    public void throwError(String message, OutputStream output) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(output);
        writer.write("_" + message);
        writer.write(Jsh.lineSeparator);
        writer.flush();
    }

    public Unsafe(Application application) {
        this.application = application;
    }

}