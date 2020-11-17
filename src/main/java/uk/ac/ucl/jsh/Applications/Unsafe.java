package uk.ac.ucl.jsh.Applications;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Unsafe implements Application {

    private Application application;
    
    @Override
    public void throwError(String message, OutputStream output) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(output);
        writer.write("_" + message);
        writer.write(System.getProperty("line.separator"));
        writer.flush();
    }

    @Override
    public String exec(ArrayList<String> args, String currDir, OutputStream output) throws IOException {
        return application.exec(args, currDir, output);
    }
    
    public Unsafe(Application application) {
        this.application = application;
    }

}