package uk.ac.ucl.jsh.Applications;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Sort implements Application {

    private OutputStreamWriter writer;

    @Override
    public String mainExec(ArrayList<String> args, String currentDirectory, InputStream is, OutputStream output) {
        String message = argCheck(args);
        String appResult;
        if (message != "nothing") {
            throwError(message, output);
        } else {
            appResult = exec(args, currentDirectory, output);
            if (appResult.startsWith("ERROR")) {
                throwError(appResult.substring(6), output);
            }
            return appResult;
        }
        return "";
    }

    @Override
    public String exec(ArrayList<String> args, String currDir, OutputStream output) {
        writer = new OutputStreamWriter(output);
        String sortArg;

        if (args.size() == 2) {
            sortArg = args.get(1);
        } else {
            sortArg = args.get(0);
        }
        File sortFile = new File(currDir + File.separator + sortArg);
        Charset encoding = StandardCharsets.UTF_8;
        if (sortFile.exists()) {
            Path sortPath = Paths.get(currDir + File.separator + sortArg);
            try (BufferedReader reader = Files.newBufferedReader(sortPath, encoding)) {
                String line = reader.readLine();
                ArrayList<String> lines = new ArrayList<>();
                ArrayList<String> sortedLines;

                /* Populate array with lines of the file */
                while (line != null) {
                    lines.add(line);
                    line = reader.readLine();
                }

                /* Use the quicksort on the array list of strings */
                sortedLines = stringQuicksort(lines);

                /* Check if the -r is present then display array in reverse order */
                if (args.size() == 2) {
                    for (int i = sortedLines.size() - 1; i >= 0; i--) {
                        writer.write(sortedLines.get(i));
                        writer.write(System.getProperty("line.separator"));
                        writer.flush();
                    }
                } else {
                    /* If -r is not present display array normally */
                    for (String sortedLine : sortedLines) {
                        writer.write(sortedLine);
                        writer.write(System.getProperty("line.separator"));
                        writer.flush();
                    }
                }
            } catch (IOException e) {
                return "ERROR sort: cannot open " + sortArg;
            }
        } else {
            return "ERROR sort: " + sortArg + " does not exist";
        }

        return currDir;
    }


    /* Recursive quick-sort algorithm for sorting an array list of strings */
    private static ArrayList<String> stringQuicksort(ArrayList<String> lines) {
        /* Base case if the file read is empty */
        if (lines.isEmpty()) {
            return lines;
        }
        ArrayList<String> beforePivot = new ArrayList<>();
        ArrayList<String> afterPivot = new ArrayList<>();

        /* Initialise the pivot to be the first element of the array (line of file) */
        String pivot = lines.get(0);
        String line;

        /* Compare each line to the pivot and add to respective array */
        for (int i = 1; i < lines.size(); i++) {
            line = lines.get(i);
            if (line.compareTo(pivot) < 0) {
                beforePivot.add(line);
            } else {
                afterPivot.add(line);
            }
        }

        /* Recursively sort the arrays */
        beforePivot = stringQuicksort(beforePivot);
        afterPivot = stringQuicksort(afterPivot);

        /* Combine and return the final array */
        beforePivot.add(pivot);
        beforePivot.addAll(afterPivot);
        return beforePivot;
    }

    /* Validates arguments input */
    @Override
    public String argCheck(ArrayList<String> args) {
        if (args.isEmpty()) {
            return "sort: missing arguments";
        } else if (args.size() != 1 && args.size() != 2) {
            return "sort: wrong number of arguments";
        } else if (args.size() == 2 && !args.get(0).equals("-r")) {
            return "sort: wrong argument " + args.get(0);
        } else {
            return "nothing";
        }
    }

    @Override
    public void throwError(String message, OutputStream output) {
        throw new RuntimeException(message);
    }
}
