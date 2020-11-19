package uk.ac.ucl.jsh.Applications;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;


public class Cd implements Application {

    @Override
    public String mainExec(ArrayList<String> args, String currentDirectory, OutputStream output) throws IOException {
        String message = argCheck(args);
        if (message != "nothing"){
            throwError(message, output);
        } else {
            return exec(args, currentDirectory, output);
        }
        return "";
    }

    @Override
    public String exec(ArrayList<String> args, String currDir, OutputStream output) throws IOException {

        String dirString = args.get(0);
        File dir = new File(currDir, dirString);
        if (!dir.exists() || !dir.isDirectory()) {
            throw new RuntimeException("cd: " + dirString + " is not an existing directory");
        }
        currDir = dir.getCanonicalPath();

        return currDir;
    }

    /* Validates arguments input */
    @Override
    public String argCheck(ArrayList<String> args) {
        if (args.isEmpty()) {
            return "cd: missing argument";
        } else if (args.size() > 1) {
            return "cd: too many arguments";
        } else {
            return "nothing";
        }
    }

    @Override
    public void throwError(String message, OutputStream output) {
        throw new RuntimeException(message);
    }
}
