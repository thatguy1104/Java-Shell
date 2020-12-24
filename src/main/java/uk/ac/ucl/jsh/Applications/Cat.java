package uk.ac.ucl.jsh.Applications;

import uk.ac.ucl.jsh.Jsh;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Cat implements Application {

    @Override
    public String mainExec(ArrayList<String> args, String currentDirectory, InputStream input, OutputStream output) throws IOException {
        String message = argCheck(args);
        // Has pipe
        if (args.size() == 1 && new ArrayList<>(Files.readAllLines(Paths.get(String.valueOf(new Scanner(input))))).size() != 0) {
            ArrayList<String> new_args = new ArrayList<>(Files.readAllLines(Paths.get(String.valueOf(new Scanner(input)))));
            new_args.add(0, "cat");
            message = argCheck(new_args);
        }
        // No pipe
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
            for (int i = 1; i < args.size(); i++) {
                Scanner scn;
                File currFile = new File(currentDirectory + File.separator + args.get(i));
                if (currFile.exists()) {
                    Path filePath = Paths.get(currentDirectory + File.separator + args.get(i));
                    try {
                        scn = new Scanner(filePath);
                        writeOut(scn, output);
                    } catch (FileNotFoundException e) {
                        throw new IOException("ERROR cat: " + e.getMessage());
                    }
                } else {
                    throw new IOException("ERROR cat: file does not exist");
                }
            }
        }
        return currentDirectory;
    }

    /* Prints to specified output */
    private void writeOut(Scanner scn, OutputStream output) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(output);
        while (scn.hasNextLine()) {
            writer.write(scn.nextLine() + Jsh.lineSeparator);
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
