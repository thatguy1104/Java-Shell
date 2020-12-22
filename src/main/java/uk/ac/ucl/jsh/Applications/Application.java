package uk.ac.ucl.jsh.Applications;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public interface Application {

    // Function signature definition for Application
    String mainExec(ArrayList<String> args, String currDir, InputStream is, OutputStream output) throws IOException;

    String exec(ArrayList<String> args, String currentDirectory, OutputStream output) throws IOException;

    String argCheck(ArrayList<String> args);

    void throwError(String message, OutputStream output) throws IOException;
}
