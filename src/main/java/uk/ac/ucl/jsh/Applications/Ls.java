package uk.ac.ucl.jsh.Applications;

import uk.ac.ucl.jsh.Jsh;

import java.io.*;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;

public class Ls implements Application {

    @Override
    public String mainExec(ArrayList<String> args, String currentDirectory, InputStream input, OutputStream output) throws IOException {
        String message = argCheck(args);
        if (!message.equals("nothing")) {
            throwError(message, output);
        } else {
            String appResult = exec(args, currentDirectory, input, output);
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
        File currDir = (args.size() == 1) ? new File(currentDirectory) : new File(args.get(1));
        if (!currDir.exists()) return "ERROR ls: directory does not exist";
        try {
            File[] listOfFiles = currDir.listFiles();
            writeOut(listOfFiles, writer);

        } catch (NoSuchFileException e) {
            return "ERROR ls: " + e.getMessage();
        }
        return currentDirectory;
    }

    @Override
    public String argCheck(ArrayList<String> args) {
        if (args.size()!=1 && args.size() > 2) return "ls: too many arguments";
        else return "nothing";
    }

    @Override
    public void throwError(String message, OutputStream output) {
        throw new RuntimeException(message);
    }

    /**
     * Function to print the list of files to a specified output stream
     * @exception IOException throws exception in case of OutputStreamWriter
     */
    private void writeOut(File[] listOfFiles, OutputStreamWriter writer) throws IOException {
        for (File file : listOfFiles) {
            if (!file.getName().startsWith(".")) {
                writer.write(file.getName() + "\t");
                writer.flush();
            }
        }
        writer.write(Jsh.lineSeparator);
        writer.flush();
    }
}
