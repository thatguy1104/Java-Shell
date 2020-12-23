package uk.ac.ucl.jsh.Applications;

import uk.ac.ucl.jsh.Jsh;

import java.io.*;
import java.nio.file.NoSuchFileException;
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

        /* Assign the current directory if no path is specified */
        File currDir = ((args.size() == 1) ? new File(currentDirectory) : new File(args.get(0)));

        try {
            File[] listOfFiles = currDir.listFiles();
            assert listOfFiles != null;
            writeOut(listOfFiles, writer);

        } catch (NoSuchFileException e) {
            return "ERROR ls: " + e.getMessage();
        }
        return currentDirectory;
    }

    private void writeOut(File[] listOfFiles, OutputStreamWriter writer) throws IOException {
        boolean atLeastOnePrinted = false;
        for (File file : listOfFiles) {
            if (!file.getName().startsWith(".")) {
                writer.write(file.getName() + "\t");
                writer.flush();
                atLeastOnePrinted = true;
            }
        }
        if (atLeastOnePrinted) {
            writer.write(Jsh.lineSeparator);
            writer.flush();
        }
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
