package uk.ac.ucl.jsh.Applications;

import uk.ac.ucl.jsh.Jsh;

import java.io.*;
import java.util.ArrayList;

public class Ls implements Application {

    @Override
    public String mainExec(ArrayList<String> args, String currentDirectory, InputStream input, OutputStream output) throws IOException {
        String message = argCheck(args);
        String appResult;
        if (!message.equals("nothing")) {
            throwError(message, output);
        } else {
            appResult = exec(args, currentDirectory, input, output);
            if (appResult.startsWith("ERROR")) {
                throwError(appResult.substring(6), output);
            }
            return appResult;
        }
        return "";
    }

    @Override
    public String exec(ArrayList<String> args, String currentDirectory, InputStream input, OutputStream output) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(output);
        File currDir;

        /* Assign the current directory if no path is specified */
        if (args.isEmpty()) {
            currDir = new File(currentDirectory);
        } else {
            currDir = new File(args.get(0));
        }

        try {
            File[] listOfFiles = currDir.listFiles();
            boolean atLeastOnePrinted = false;
            assert listOfFiles != null;

            for (File file : listOfFiles) {
                if (!file.getName().startsWith(".")) {
                    writer.write(file.getName());
                    writer.write("\t");
                    writer.flush();
                    atLeastOnePrinted = true;
                }
            }
            if (atLeastOnePrinted) {
                writer.write(Jsh.lineSeparator);
                writer.flush();
            }
        } catch (NullPointerException e) {
            return "ERROR ls: no such directory";
        }
        return currentDirectory;
    }

    /* Validates arguments input */
    @Override
    public String argCheck(ArrayList<String> args) {
        if (!args.isEmpty() && args.size() != 1) {
            return "ls: too many arguments";
        } else {
            return "nothing";
        }
    }

    @Override
    public void throwError(String message, OutputStream output) {
        throw new RuntimeException(message);
    }
}
