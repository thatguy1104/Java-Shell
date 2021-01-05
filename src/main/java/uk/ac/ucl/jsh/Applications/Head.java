package uk.ac.ucl.jsh.Applications;

import uk.ac.ucl.jsh.Jsh;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Head implements Application {

    @Override
    public String mainExec(ArrayList<String> args, String currentDirectory, InputStream input, OutputStream output) throws IOException {
        String message = argCheck(args);
        if (input != null && args.size() == 1) {
            message = "nothing";
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
        int headLines = changeHeadLines(args);
        if (headLines == -1) {
            return "ERROR head: wrong argument " + args.get(2);
        }
        getCorrectLines(args, currentDirectory, input, output, headLines);
        return currentDirectory;
    }

    @Override
    public String argCheck(ArrayList<String> args) {
        if (args.size() == 1) {
            return "head: missing arguments";
        } else if (args.size() != 2 && args.size() != 4) {
            return "head: wrong arguments";
        } else if (args.size() == 4 && !args.get(1).equals("-n")) {
            return "head: wrong argument " + args.get(1);
        } else {
            return "nothing";
        }
    }

    @Override
    public void throwError(String message, OutputStream output) {
        throw new RuntimeException(message);
    }

    /**
     * Function to print the correct number of lines to a specified output stream
     * @return - void
     */
    private void writeOut(Scanner scn, OutputStreamWriter writer, int headLines) throws IOException {
        int counter = 0;
        while (counter < headLines && scn.hasNextLine()) {
            writer.write(scn.nextLine() + Jsh.lineSeparator);
            writer.flush();
            counter++;
        }
    }

    /**
     * Checks if there is a number of lines specified
     * @return - int
     */
    private int changeHeadLines(ArrayList<String> args) {
        int headLines = 10;
        if (args.size() == 4) {
            try {
                headLines = Integer.parseInt(args.get(2));
            } catch (Exception e) {
                headLines = -1;
            }
        }
        return headLines;
    }

    /**
     * Gets the correct number of lines and calls the WriteOut method with relevant parameters
     * @return - void
     */
    private void getCorrectLines(ArrayList<String> args, String currentDirectory, InputStream input, OutputStream output, int headLines) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(output);
        if (args.size() == 2 || args.size() == 4) {
            Path filePath = Paths.get(currentDirectory + File.separator + args.get(args.size() - 1));
            Scanner scn = new Scanner(filePath);
            writeOut(scn, writer, headLines);
        } else {
            writeOut(new Scanner(input), writer, headLines);
        }
    }
}
