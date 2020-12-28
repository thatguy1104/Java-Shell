package uk.ac.ucl.jsh.Applications;

import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.Parser.Globbing;

import java.io.*;
import java.util.ArrayList;

public class Echo implements Application {

    private Globbing globbing = new Globbing();

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
        OutputStreamWriter writer = new OutputStreamWriter(output);
        ArrayList<String> argArray = new ArrayList<>();
        String directoryCheck;
        String fileType;
        String diffDirectory;
        for (String checkArg : args) {
            if (!checkArg.contains("*.")) {
                argArray.add(checkArg);
            } else {
                if (checkArg.startsWith("*.")) {
                    fileType = checkArg.substring(2);
                    //System.out.println(fileType);
                    directoryCheck = currentDirectory;
                    //argArray.addAll()
                } else {
                    Integer splitPosition = checkArg.indexOf(".");
                    fileType = checkArg.substring(splitPosition);
                    diffDirectory = checkArg.substring(0, splitPosition - 2);
                    //System.out.println(fileType);
                    //System.out.println(directory);
                    //System.out.println(currentDirectory + directory);
                    directoryCheck = currentDirectory + "\\" + diffDirectory;
                }
                ArrayList<File> listOfFiles = new ArrayList<>();
                listOfFiles.addAll(globbing.globFiles(fileType, directoryCheck));
                for (File fileName : listOfFiles) {
                    String relativeFile;
                    relativeFile = fileName.toString().substring(currentDirectory.length() + 1);
                    //relativeFile = fileName.toString().substring(fileName.toString().indexOf(directoryCheck));
                    argArray.add(relativeFile);
                }
            }
        }
        writeOut(argArray, writer);
        return currentDirectory;
    }

    private void writeOut(ArrayList<String> args, OutputStreamWriter writer) throws IOException {
        boolean atLeastOnePrinted = false;

        for (int i = 1; i < args.size(); i++) {
            writer.write(args.get(i) + " ");
            writer.flush();
            atLeastOnePrinted = true;
        }
        if (atLeastOnePrinted) {
            writer.write(Jsh.lineSeparator);
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
