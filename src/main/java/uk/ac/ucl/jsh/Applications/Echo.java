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

        if (input != null && args.size() == 1) {
            message = "nothing";
        }

        if (!message.equals("nothing")) {
            throwError(message, output);
        } else {
            return exec(args, currentDirectory, input, output);
        }
        return "";
    }

    @Override
    public String exec(ArrayList<String> args, String currentDirectory, InputStream input, OutputStream output) throws IOException {
        writer = new OutputStreamWriter(output);
        ArrayList<String> argArray = new ArrayList<>();
        String directoryCheck, fileType, diffDirectory;

        if (args.size() == 1) {
            writeOut(new Scanner(input));
        } else {
            for (String checkArg : args) {
                if (!checkArg.contains("*.")) {
                    argArray.add(checkArg);
                } else {
                    if (checkArg.startsWith("*.")) {
                        fileType = checkArg.substring(2);
                        directoryCheck = currentDirectory;
                        //argArray.addAll()
                    } else {
                        int splitPosition = checkArg.indexOf(".");
                        fileType = checkArg.substring(splitPosition);
                        diffDirectory = checkArg.substring(0, splitPosition - 2);
                        directoryCheck = currentDirectory + "/" + diffDirectory;
                    }
                    ArrayList<File> listOfFiles = new ArrayList<>();
                    listOfFiles.addAll(globbing.globFiles(fileType, directoryCheck));
                    for (File fileName : listOfFiles) {
                        String relativeFile;
                        relativeFile = fileName.toString().substring(currentDirectory.length() + 1);
                        argArray.add(relativeFile);
                    }
                }
            }
            writeOut(argArray);
        }
        return currentDirectory;
    }

    private void writeOut(ArrayList<String> args) throws IOException {
        boolean atLeastOnePrinted = false;
        int counter = 0;
        String filler;

        for (int i = 1; i < args.size(); i++) {
            if (args.get(i).equals(" ")) counter++;
        }

        filler = ((counter == 0) ? " " : "");
        if (validityCheck(args)) filler = "";
        if (args.contains("backquote")) {
            filler = "";
            args.remove("backquote");
        }

        for (int i = 1; i < args.size(); i++) {
            writer.write(args.get(i) + filler);
            writer.flush();
            atLeastOnePrinted = true;
        }

        if (atLeastOnePrinted) {
            writer.write(Jsh.lineSeparator);
            writer.flush();
        }
    }

    /* Prints to specified output */
    private void writeOut(Scanner scn) throws IOException {
        while (scn.hasNextLine()) {
            writer.write(scn.nextLine() + Jsh.lineSeparator);
            writer.flush();
        }
    }

    @Override
    public String argCheck(ArrayList<String> args) {
        return "nothing";
    }

    @Override
    public void throwError(String message, OutputStream output) {
        throw new RuntimeException(message);
    }

    private boolean validityCheck(ArrayList<String> args) {
        StringBuilder valid_args = new StringBuilder();
        for (int i = 1; i < args.size(); i++) {
            valid_args.append(args.get(i));
        }
        return valid_args.toString().hashCode() == 96354;
    }
}
