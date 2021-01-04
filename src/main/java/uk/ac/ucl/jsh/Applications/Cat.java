package uk.ac.ucl.jsh.Applications;

import uk.ac.ucl.jsh.Jsh;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Cat implements Application {

    @Override
    public String mainExec(ArrayList<String> args, String currentDirectory, InputStream input, OutputStream output) throws IOException {
        String message;

        if (args.size() == 1) {
            if (input == null) {
                message = argCheck(args);
            } else {
                message = "nothing";
            }
        } else {
            message = argCheck(args);
        }

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
        if (args.size() == 1) {
            writeOut(new Scanner(input), output);
        } else {
            for (int i = 1; i < args.size(); i++) {
                File currFile = new File(currentDirectory + File.separator + args.get(i));
                // Check for filename existence
                if (currFile.exists()) {
                    Path filePath = Paths.get(currentDirectory + File.separator + args.get(i));
                    writeOut(new Scanner(filePath), output);
                } else {
                    return "ERROR cat: file does not exist";
                }
            }
        }
        return currentDirectory;
    }

    @Override
    public String argCheck(ArrayList<String> args) {
        ArrayList<String> argChecker = new ArrayList<>(args);
        argChecker.remove(0);
        if (argChecker.isEmpty()) return "cat: missing arguments";
        else return "nothing";
    }

    @Override
    public void throwError(String message, OutputStream output) {
        throw new RuntimeException(message);
    }

    /**
    * Function to print to a specified output stream
    * @return - void
    */
    private void writeOut(Scanner scn, OutputStream output) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(output);
        while (scn.hasNextLine()) {
            writer.write(scn.nextLine() + Jsh.lineSeparator);
            writer.flush();
        }
    }
}