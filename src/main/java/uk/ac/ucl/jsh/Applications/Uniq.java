package uk.ac.ucl.jsh.Applications;

import uk.ac.ucl.jsh.Jsh;

import java.io.*;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Uniq implements Application {

    private OutputStreamWriter writer;

    @Override
    public String mainExec(ArrayList<String> args, String currentDirectory, InputStream input, OutputStream output) throws IOException {
        String message = argCheck(args);

        if (input != null && args.size() == 1) {
            message = "nothing";
        }

        // No pipe
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
    public String exec(ArrayList<String> args, String currDir, InputStream input, OutputStream output) throws IOException {
        this.writer = new OutputStreamWriter(output);
        ArrayList<String> new_args = new ArrayList<>();

        if (args.size() == 1 || (args.size() == 2 && args.get(1).equals("-i"))) {
            Scanner scn = new Scanner(input);
            while (scn.hasNextLine()) {
                new_args.add(scn.nextLine());
            }
            ArrayList<String> uniqLines = returnUniqLines(new_args, args);
            writeOut(uniqLines);
        } else {
            String uniqFilename = ((args.size() == 3) ? args.get(2) : args.get(1));
            File uniqFile = new File(currDir + File.separator + uniqFilename);

            if (uniqFile.exists()) {
                try {
                    /* Populate array with lines of the file */
                    ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get(String.valueOf(uniqFile))));
                    ArrayList<String> uniqLines = returnUniqLines(lines, args);

                    /* Display array of uniq lines */
                    writeOut(uniqLines);

                } catch (IOException e) {
                    return "ERROR uniq: cannot open " + uniqFilename;
                }
            } else {
                return "ERROR uniq: " + uniqFilename + " does not exist";
            }
        }
        return currDir;
    }

    private ArrayList<String> returnUniqLines(ArrayList<String> lines, ArrayList<String> args) {
        ArrayList<String> uniqLines = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            boolean equals = false;
            String row = lines.get(i);
            for (int j = i + 1; j < i+2; j++) {
                if (j == lines.size()) {break;}
                /* Check if -i is present */
                boolean ignore_case = row.equalsIgnoreCase(lines.get(j));
                if ((ignore_case && args.get(1).equals("-i")) || (row.equals(lines.get(j)) && !(args.get(1).equals("-i")))) {
                    equals = true;
                    break;
                }
            }
            if (!equals) {
                uniqLines.add(row);
            }
        }
        return uniqLines;
    }

    /* Prints to specified output */
    private void writeOut(ArrayList<String> uniqLines) throws IOException {
        for (String uniqLine : uniqLines) {
            this.writer.write(uniqLine + Jsh.lineSeparator);
            this.writer.flush();
        }
    }

    /* Validates arguments input */
    @Override
    public String argCheck(ArrayList<String> args) {
        if (args.isEmpty()) {
            return "uniq: missing argument";
        } else if (args.size() > 3) {
            return "uniq: too many arguments";
        } else if (args.size() == 3 && !args.get(1).equals("-i")) {
            return "uniq: wrong argument" + args.get(1);
        } else {
            return "nothing";
        }
    }

    @Override
    public void throwError(String message, OutputStream output) {
        throw new RuntimeException(message);
    }
}
