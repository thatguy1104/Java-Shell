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

    private OutputStreamWriter writer;

    @Override
    public String mainExec(ArrayList<String> args, String currentDirectory, InputStream input, OutputStream output) throws IOException {
        String message = input != null && args.size() == 2 ? "nothing" : argCheck(args);
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
        Pattern grepPattern = Pattern.compile(args.get(1));

        Path[] filePathArray = getFilePaths(currentDirectory, args, args.size() - 1);
        if (filePathArray == null) return "ERROR grep: wrong file argument";

        // TODO what is this boolean, add commetn explaining what it represents
        boolean mutlArgFiles = args.size() > 3;

        if (args.size() > 2) {
            for (int i = 2; i < args.size(); i++) {
                Scanner scn;
                try {
                    Path filePath = Paths.get(currentDirectory + File.separator + args.get(i));
                    scn = new Scanner(filePath);
                } catch (FileNotFoundException e) {
                    throw new IOException("ERROR grep: " + e.getMessage());
                }
                if (mutlArgFiles) writeOut(scn, grepPattern, args.get(i));
                else writeOut(scn, grepPattern, null);
            }
        } else {
            /* if args are empty and input stream is present */
            writeOut(new Scanner(input), grepPattern, null);
        }
        return currentDirectory;
    }

    @Override
    public String argCheck(ArrayList<String> args) {
        if (args.size() < 3) return "grep: wrong number of arguments";
        else return "nothing";
    }

    @Override
    public void throwError(String message, OutputStream output) {
        throw new RuntimeException(message);
    }

    /**
     * Function to return number of dots present in a grep pattern (wildcards)
     * @return - number of dots in a pattern in an integer data type
     */
    private int isAllDots(Pattern pattern) {
        int result = 0;
        String s = String.valueOf(pattern);
        for (char c : s.toCharArray()) {
            if (c != '.') return 0;
            result++;
        }
        return result;
    }

    /**
     * Function to match lines and write them to an output stream
     * @return - void
     */
    private void writeOut(Scanner scn, Pattern pattern, String filePath) throws IOException {
        int dots = isAllDots(pattern);
        String s = (filePath != null) ? filePath + ":" : "";

        while (scn.hasNextLine()) {
            String line = scn.nextLine();
            Matcher match = pattern.matcher(line);
            /* Check for wildcard dots, then perform matching with the pattern */
            if (dots != 0 && match.find()) {
                writer.write(s + line.substring(0, dots) + Jsh.lineSeparator);
                writer.flush();
            } else if (dots == 0 && match.find()) {
                writer.write(s + line + Jsh.lineSeparator);
                writer.flush();
            }
        }
    }

    /**
     * Function to get directory pathway for the specified file name arguments
     * @return - path type array
     */
    private Path[] getFilePaths(String currentDirectory, ArrayList<String> args, int numOfFiles) {
        Path[] filePathArray = new Path[numOfFiles];
        Path currentDir = Paths.get(currentDirectory);
        for (int i = 1; i < numOfFiles; i++) {
            Path filePath = currentDir.resolve(args.get(i + 1));
            boolean invalidDirectory = Files.notExists(filePath) || Files.isDirectory(filePath);
            if (invalidDirectory) return null;
            filePathArray[i] = filePath;
        }
        return filePathArray;
    }
}
