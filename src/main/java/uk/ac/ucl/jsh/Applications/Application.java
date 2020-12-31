package uk.ac.ucl.jsh.Applications;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public interface Application {

    /**
     * Function signature definition for application
     * @param args Command line arguments
     * @param currDir Current directory
     * @param input Used instead of args when input redirection present
     * @param output Output
     */

    String mainExec(ArrayList<String> args, String currDir, InputStream input, OutputStream output) throws IOException;

    /**
     * @param args
     * @param currentDirectory
     * @param input
     * @param output
     */
    String exec(ArrayList<String> args, String currentDirectory, InputStream input, OutputStream output) throws IOException;

    String argCheck(ArrayList<String> args);

    void throwError(String message, OutputStream output) throws IOException;
}
