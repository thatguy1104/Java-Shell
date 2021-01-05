package uk.ac.ucl.jsh.Applications;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public interface Application {

    /**
     * Main executing method for an application
     * @param args Commandline arguments
     * @param currDir Current directory
     * @param input Used instead of args when input redirection present
     * @param output Output
     */

    String mainExec(ArrayList<String> args, String currDir, InputStream input, OutputStream output) throws IOException;

    /**
     * Secondary main method for running an application, does main processing of argument checks and writing out to an output stream
     * @param args Commandline arguments
     * @param currentDirectory Current directory
     * @param input Used instead of args when input redirection present
     * @param output ...
     */
    String exec(ArrayList<String> args, String currentDirectory, InputStream input, OutputStream output) throws IOException;

    /**
     * Ensure valid present arguments are valid
     * @param args Commandline arguments
     */
    String argCheck(ArrayList<String> args);

    void throwError(String message, OutputStream output) throws IOException;
}
