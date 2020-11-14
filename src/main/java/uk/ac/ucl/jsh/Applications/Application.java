package uk.ac.ucl.jsh.Applications;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public interface Application {

    // Function signature definition for Application
    String exec(ArrayList<String> args, String currDir, OutputStream output) throws IOException;

    //public String argCheck(ArrayList<String> args);
    public void throwError(String message, OutputStream output) throws IOException;
}
