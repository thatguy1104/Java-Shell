package uk.ac.ucl.jsh;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class Cd implements Application {

    @Override
    public String exec(ArrayList<String> args, InputStream input, OutputStream output, String currDir) throws IOException {
        
        if (args.isEmpty()) {
            throw new RuntimeException("cd: missing argument");
        } else if (args.size() > 1) {
            throw new RuntimeException("cd: too many arguments");
        }
        String dirString = args.get(0);
        File dir = new File(currDir, dirString);
        if (!dir.exists() || !dir.isDirectory()) {
            throw new RuntimeException("cd: " + dirString + " is not an existing directory");
        }
        currDir = dir.getCanonicalPath();

        return currDir;
    }
}
