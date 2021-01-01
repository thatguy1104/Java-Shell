package uk.ac.ucl.jsh.Applications;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public interface Application {

    /**
     *
     * @param args Commandline arguments
     * @param currDir Current directory
     * @param input Used instead of args when input redirection present
     * @param output Output
     */

    String mainExec(ArrayList<String> args, String currDir, InputStream input, OutputStream output) throws IOException;

    /**
     *
     * @param args Commandline arguments
     * @param currentDirectory Current directory
     * @param input Used instead of args when input redirection present
     * @param output ...
     */
    String exec(ArrayList<String> args, String currentDirectory, InputStream input, OutputStream output) throws IOException;

    /**
     * Ensure correct numbers and text
     * @param args Commandline arguments
     */
    String argCheck(ArrayList<String> args);

    void throwError(String message, OutputStream output) throws IOException;
}
