package uk.ac.ucl.jsh.Applications;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;


public class Cd implements Application {

    @Override
    public String exec(ArrayList<String> args, String currDir, OutputStream output) throws IOException {
        
        String message = argCheck(args);
        if (message != "nothing") {
            throwError(message, output);
        }
        
        String dirString = args.get(0);
        File dir = new File(currDir, dirString);
        if (!dir.exists() || !dir.isDirectory()) {
            throw new RuntimeException("cd: " + dirString + " is not an existing directory");
        }
        currDir = dir.getCanonicalPath();

        return currDir;
    }

    /* Validates arguments input */
    public String argCheck(ArrayList<String> args) {
        if (args.isEmpty()) {
            return "cd: missing argument";
            //throw new RuntimeException("cd: missing argument");
        } else if (args.size() > 1) {
            //throw new RuntimeException("cd: too many arguments");
            return "cd: too many arguments";
        } else {
            return "nothing";
        }
    }

    public void throwError(String message, OutputStream output) throws IOException {
        throw new RuntimeException(message);
    }
}
