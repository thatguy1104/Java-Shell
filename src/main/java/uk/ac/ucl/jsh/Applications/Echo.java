package uk.ac.ucl.jsh.Applications;

import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.Parser.Globbing;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Echo implements Application {

    private Globbing globbing = new Globbing();
    private OutputStreamWriter writer;
    private String filler;

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
                    } else {
                        int splitPosition = checkArg.indexOf(".");
                        fileType = checkArg.substring(splitPosition);
                        diffDirectory = checkArg.substring(0, splitPosition - 2);
                        directoryCheck = currentDirectory + "/" + diffDirectory;
                    }
                    ArrayList<File> listOfFiles = new ArrayList<>(globbing.globFiles(fileType, directoryCheck));
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

    private ArrayList<String> checker(ArrayList<String> args) {
        ArrayList<String> result = new ArrayList<>(args);

        if (result.contains("backquote")) {
            filler = "";
            result.remove("backquote");
        }
        if (result.contains("doublequote")) {
            filler = "";
            while (result.contains("doublequote")) result.remove("doublequote");
        }
        return result;
    }

    private void writeOut(ArrayList<String> args) throws IOException {
        boolean atLeastOnePrinted = false;
        int counter = 0;

        for (int i = 1; i < args.size(); i++) {
            if (args.get(i).equals(" ")) counter++;
        }

        filler = ((counter == 0) ? " " : "");
        args = checker(args);

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
}
