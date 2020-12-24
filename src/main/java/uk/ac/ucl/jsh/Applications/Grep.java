package uk.ac.ucl.jsh.Applications;

import uk.ac.ucl.jsh.Jsh;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep implements Application {

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
        Pattern grepPattern = Pattern.compile(args.get(1));

        Path[] filePathArray = getFilePaths(currentDirectory, args, args.size() - 1);
        if (filePathArray == null) {
            return "ERROR grep: wrong file argument";
        }

        if (args.size() > 1) {
            for (int i = 2; i < args.size(); i++) {
                Scanner scn;
                try {
                    Path filePath = Paths.get(currentDirectory + File.separator + args.get(i));
                    scn = new Scanner(filePath);
                } catch (FileNotFoundException e) {
                    throw new IOException("ERROR cat: " + e.getMessage());
                }
                writeOut(scn, writer, grepPattern);
            }
        } else {
            writeOut(new Scanner(input), writer, grepPattern);
        }
        return currentDirectory;
    }

    private void writeOut(Scanner scn, OutputStreamWriter writer, Pattern pattern) throws IOException {
        while (scn.hasNextLine()) {
            String line = scn.nextLine();
            Matcher match = pattern.matcher(line);
            if (match.find()) {
                writer.write(line + Jsh.lineSeparator);
                writer.flush();
            }
            writer.flush();
        }
    }

    /* Returns directory pathway for the specified file name arguments */
    private Path[] getFilePaths(String currentDirectory, ArrayList<String> args, int numOfFiles) {
        Path[] filePathArray = new Path[numOfFiles];
        Path currentDir = Paths.get(currentDirectory);

        for (int i = 1; i < numOfFiles; i++) {
            Path filePath = currentDir.resolve(args.get(i + 1));
            if (Files.notExists(filePath) || Files.isDirectory(filePath) || !Files.exists(filePath) || !Files.isReadable(filePath)) {
                return null;
                //throw new RuntimeException("grep: wrong file argument");
            }
            filePathArray[i] = filePath;
        }

        return filePathArray;
    }

    /* Validates arguments input */
    @Override
    public String argCheck(ArrayList<String> args) {
        if (args.size() < 3) {
            return "grep: wrong number of arguments";
        } else {
            return "nothing";
        }
    }

    @Override
    public void throwError(String message, OutputStream output) {
        throw new RuntimeException(message);
    }
}
