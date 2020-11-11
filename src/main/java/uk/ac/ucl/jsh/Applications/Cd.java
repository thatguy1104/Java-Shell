package uk.ac.ucl.jsh.Applications;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;


public class Cd implements Application {

    @Override
    public String exec(ArrayList<String> args, String currDir, OutputStream output) throws IOException {
        argCheck(args);
        
        String dirString = args.get(0);
        File dir = new File(currDir, dirString);
        if (!dir.exists() || !dir.isDirectory()) {
            throw new RuntimeException("cd: " + dirString + " is not an existing directory");
        }
        currDir = dir.getCanonicalPath();

        return currDir;
    }

    /* Validates arguments input */
    private void argCheck(ArrayList<String> args) throws IOException {
        if (args.isEmpty()) {
            throw new RuntimeException("cd: missing argument");
        } else if (args.size() > 1) {
            throw new RuntimeException("cd: too many arguments");
        }
    }
}
