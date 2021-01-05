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

        /* If conditions not true pipe is present and scanner will be used*/
        if (input != null && args.size() == 1) {
            message = "nothing";
        }

        /* No pipe */
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

        /* Safety check to limit args going out of bound */
        if (args.size() == 1 || args.size() == 2 && args.get(1).equals("-i")) {
            Scanner scn = new Scanner(input);
            while (scn.hasNextLine()) {
                new_args.add(scn.nextLine());
            }
            ArrayList<String> uniqLines = returnUniqLines(new_args, args);
            writeOut(uniqLines);
        } else {
            /* Filename position in args changes if '-i' is present */
            String uniqFilename = (args.size() == 3) ? args.get(2) : args.get(1);
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

    @Override
    public String argCheck(ArrayList<String> args) {
        if (args.size() == 1) {
            return "uniq: missing argument";
        } else if (args.size() > 3) {
            return "uniq: too many arguments";
        } else if (args.size() == 3 && !args.get(1).equals("-i")) {
            return "uniq: wrong argument " + args.get(1);
        } else {
            return "nothing";
        }
    }

    @Override
    public void throwError(String message, OutputStream output) {
        throw new RuntimeException(message);
    }

    /**
     * Find the unique lines and store them into an ArrayList to return
     * @return - ArrayList
     */
    private ArrayList<String> returnUniqLines(ArrayList<String> lines, ArrayList<String> args) {

        /* Eliminate leading and trailing spaces */
        lines.replaceAll(String::strip);

        ArrayList<String> uniqLines = new ArrayList<>();

        /* First line is always considered unique*/
        uniqLines.add(lines.get(0));

        /* Iterate through each line and ensure unique adjacent lines */
        for (int i = 1; i < lines.size(); i++) {
            String row = lines.get(i);
            String previousRow = uniqLines.get(uniqLines.size()-1);

                /* If -i exists make case insensitive */
                boolean caseIgnore = args.size() >= 2 && args.get(1).equals("-i");

                /* compare lines and only save unique adjacent lines*/
                if (!(row.equalsIgnoreCase(previousRow) && caseIgnore || row.equals(previousRow))) {
                    uniqLines.add(row);
                }
        }
        return uniqLines;
    }

    /**
     * Function to print the Array of unique lines
     * @return - void
     */
    private void writeOut(ArrayList<String> uniqLines) throws IOException {
        for (String uniqLine : uniqLines) {
            this.writer.write(uniqLine + Jsh.lineSeparator);
            this.writer.flush();
        }
    }
}
