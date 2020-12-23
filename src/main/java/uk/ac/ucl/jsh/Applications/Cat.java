package uk.ac.ucl.jsh.Applications;

import uk.ac.ucl.jsh.Jsh;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Cat implements Application {

    @Override
    public String mainExec(ArrayList<String> args, String currentDirectory, InputStream input, OutputStream output) throws IOException {
        String message = argCheck(args);
        if (!message.equals("nothing")) {
            throwError(message, output);
        } else {
            String appResult;
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
        if (args.isEmpty()) {
            writeOut(new Scanner(input), output);
        } else {
            for (String arg : args) {
                Scanner scn;
                try {
                    Path filePath = Paths.get(currentDirectory + File.separator + arg);
                    scn = new Scanner(filePath);
                } catch (FileNotFoundException e) {
                    throw new IOException("ERROR cat: " + e.getMessage());
                }
                writeOut(scn, output);
            }
        }
        return currentDirectory;
    }

    /* Prints to specified output */
    private void writeOut(Scanner reader, OutputStream output) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(output);
        while (reader.hasNextLine()) {
            writer.write(reader.nextLine());
            writer.write(Jsh.lineSeparator);
            writer.flush();
        }
    }

    /* Validate args input */
    @Override
    public String argCheck(ArrayList<String> args) {
        if (args.isEmpty()) {
            return "cat: missing arguments";
        } else {
            return "nothing";
        }
    }

    @Override
    public void throwError(String message, OutputStream output) {
        throw new RuntimeException(message);
    }
}
