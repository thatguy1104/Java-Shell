package uk.ac.ucl.jsh.Applications;

import uk.ac.ucl.jsh.Jsh;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Tail implements Application {

    private OutputStreamWriter writer;

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
        writer = new OutputStreamWriter(output);
        ArrayList<String> new_args = new ArrayList<>();
        int tailLines = 10;
        String endResult;

        if (args.size() == 1) {
            Scanner scn = new Scanner(input);
            while (scn.hasNextLine()) {
                new_args.add(scn.nextLine());
            }
            writeOut(tailLines, new_args);
        } else {
            endResult = normalInput(args, tailLines, currentDirectory);
            if (!endResult.equals("SUCCESS")) {
                return endResult;
            }
        }
        return currentDirectory;
    }


    @Override
    public String argCheck(ArrayList<String> args) {
        if (args.isEmpty()) {
            return "tail: missing arguments";
        } else if (args.size() != 2 && args.size() != 4) {
            return "tail: wrong arguments";
        } else if (args.size() == 4 && !args.get(1).equals("-n")) {
            return "tail: wrong argument " + args.get(1);
        } else {
            return "nothing";
        }
    }

    @Override
    public void throwError(String message, OutputStream output) {
        throw new RuntimeException(message);
    }

    /**
     * Function to print the correct number of lines from an ArrayList
     * @return - void
     */
    private void writeOut(int tailLines, ArrayList<String> storage) throws IOException {
        int index = ((tailLines <= storage.size()) ? storage.size() - tailLines : 0);
        for (int i = index; i < storage.size(); i++) {
            writer.write(storage.get(i) + Jsh.lineSeparator);
            writer.flush();
        }
    }

    /**
     * Handles the case for a normal input: calls the methods to get the correct lines
     * @return - String
     */
    private String normalInput(ArrayList<String> args, int tailLines, String currentDirectory) throws IOException {
        String tailArg;
        if (args.size() == 4) {
            tailLines = changeTailLines(args);
            tailArg = args.get(3);
        } else {
            tailArg = args.get(1);
        }

        if (tailLines == -1) return "ERROR tail: wrong argument " + args.get(2);

        String correctLines = getCorrectLines(tailLines, tailArg, currentDirectory);
        if (!correctLines.equals("SUCCESS")) {
            return correctLines;
        }
        return "SUCCESS";
    }

    /**
     * Checks if there is a number of lines specified
     * @return - int
     */
    private int changeTailLines(ArrayList<String> args) {
        int tailLines;
        try {
            tailLines = Integer.parseInt(args.get(2));
        } catch (Exception e) {
            tailLines = -1;
        }
        return tailLines;
    }

    /**
     * Gets the correct number of lines and calls the WriteOut method with relevant parameters
     * @return - String
     */
    private String getCorrectLines(int tailLines, String tailArg, String currentDirectory) throws IOException {
        File tailFile = new File(currentDirectory + File.separator + tailArg);
        if (tailFile.exists()) {
            ArrayList<String> storage = new ArrayList<>(Files.readAllLines(Paths.get(String.valueOf(tailFile))));
            try {
                writeOut(tailLines, storage);
            } catch (IOException e) {
                return "ERROR tail: cannot open " + tailArg;
            }
        } else {
            return "ERROR tail: " + tailArg + " does not exist";
        }
        return "SUCCESS";
    }
}