package uk.ac.ucl.jsh.Applications;

import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.Parser.Globbing;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Echo implements Application {

    private Globbing globbing = new Globbing();
    private OutputStreamWriter writer;

    @Override
    public String mainExec(ArrayList<String> args, String currentDirectory, InputStream input, OutputStream output) throws IOException {
        String message = argCheck(args);
        if (!message.equals("nothing")) {
            throwError(message, output);
        } else {
            return exec(args, currentDirectory, input, output);
        }
        return "";
    }

    @Override
    public String exec(ArrayList<String> args, String currentDirectory, InputStream input, OutputStream output) throws IOException {
        args.remove("");
        writer = new OutputStreamWriter(output);
        ArrayList<String> argArray = new ArrayList<>();
        if (args.size() == 1) {
            writeOut(new Scanner(input));
        } else {
            for (String checkArg : args) {
                if (!checkArg.contains("*.")) argArray.add(checkArg);
                else argArray.addAll(process(checkArg, currentDirectory));
            }
            writeOut(argArray);
        }
        return currentDirectory;
    }

    @Override
    public String argCheck(ArrayList<String> args) {
        return "nothing";
    }

    @Override
    public void throwError(String message, OutputStream output) {
        throw new RuntimeException(message);
    }

    /**
     * Function to perform check for globbing or multiple file arguments
     * @return - ArrayList of files
     */
    private ArrayList<String> process(String checkArg, String currentDirectory) {
        String directoryCheck, fileType, diffDirectory;

        if (checkArg.startsWith("*.")) {
            fileType = checkArg.substring(2);
            directoryCheck = currentDirectory;
        } else {
            int splitPosition = checkArg.indexOf(".");
            fileType = checkArg.substring(splitPosition);
            diffDirectory = checkArg.substring(0, splitPosition - 2);
            directoryCheck = currentDirectory + File.separator + diffDirectory;
        }
        return supportProcess(fileType, directoryCheck, currentDirectory);
    }

    /**
     * Support function for the process method, iterates through files
     * @return - ArrayList of file names
     */
    private ArrayList<String> supportProcess(String fileType, String directoryCheck, String currDir) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<File> listOfFiles = new ArrayList<>(globbing.globFiles(fileType, directoryCheck));
        for (File fileName : listOfFiles) {
            String relativeFile = fileName.toString().substring(currDir.length() + 1);
            result.add(relativeFile);
        }
        return result;
    }

    /**
     * Function to check for proper formatting and print to a specified output stream using ArrayList
     * @return - void
     */
    private void writeOut(ArrayList<String> args) throws IOException {
        int counter = countEmptySpaces(args);

        /* Choose appropriate number of spaces between string arguments */
        String filler = counter == 0 ? " " : "";
        if (args.contains("`")) {
            filler = "";
            args.remove("`");
        }
        if (args.contains("\"")) {
            filler = "";
            while (args.contains("\"")) args.remove("\"");
        }

        for (int i = 1; i < args.size(); i++) {
            writer.write(args.get(i) + filler);
        }

        /* Print final new-line character onto the output stream */
        writer.write(Jsh.lineSeparator);
        writer.flush();
    }

    /**
     * Function to count elements representing an empty space
     * @return - integer
     */
    private int countEmptySpaces(ArrayList<String> args) {
        int result = 0;
        for (int i = 1; i < args.size(); i++) {
            if (args.get(i).equals(" ")) result++;
        }
        return result;
    }

    /**
     * Function to print to a specified output stream using Scanner contents
     * @return - void
     */
    private void writeOut(Scanner scn) throws IOException {
        while (scn.hasNextLine()) {
            writer.write(scn.nextLine() + Jsh.lineSeparator);
            writer.flush();
        }
    }
}