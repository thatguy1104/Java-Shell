package uk.ac.ucl.jsh.Applications;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public interface Application {

    // Function signature definition for Application
    String mainExec(ArrayList<String> args, String currDir, InputStream input, OutputStream output) throws IOException;

    String exec(ArrayList<String> args, String currentDirectory, InputStream input, OutputStream output) throws IOException;

    String argCheck(ArrayList<String> args);

    void throwError(String message, OutputStream output) throws IOException;

//    ArrayList<File> globFiles(String input);
}
