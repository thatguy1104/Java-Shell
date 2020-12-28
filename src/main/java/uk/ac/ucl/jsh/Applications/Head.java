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
        OutputStreamWriter writer = new OutputStreamWriter(output);

        int headLines = 10;
        if (args.size() == 4) {
            try {
                headLines = Integer.parseInt(args.get(2));
            } catch (Exception e) {
                return "ERROR head: wrong argument " + args.get(2);
            }
        }

        if (args.size() == 2 || args.size() == 4) {
            Path filePath = Paths.get(currentDirectory + File.separator + args.get(args.size() - 1));
            Scanner scn = new Scanner(filePath);
            writeOut(scn, writer, headLines);

        } else {
            writeOut(new Scanner(input), writer, headLines);
        }
        return currentDirectory;
    }

    /* Prints to specified output */
    private void writeOut(Scanner scn, OutputStreamWriter writer, int headLines) throws IOException {
        int counter = 0;
        while (counter < headLines && scn.hasNextLine()) {
            writer.write(scn.nextLine() + Jsh.lineSeparator);
            writer.flush();
            counter++;
        }
    }

    /* Validates arguments input */
    @Override
    public String argCheck(ArrayList<String> args) {
        if (args.isEmpty()) {
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
}
