package uk.ac.ucl.jsh;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Sort implements Application {

    @Override
    public String exec(ArrayList<String> args, String currDir, OutputStream output) {
        OutputStreamWriter writer = new OutputStreamWriter(output);
        String sortArg;

        argCheck(args);
        
        if (args.size() == 2) {
            sortArg = args.get(1);
        } else {
            sortArg = args.get(0);
        }
        File sortFile = new File(currDir + File.separator + sortArg);
        Charset encoding = StandardCharsets.UTF_8;
        if (sortFile.exists()) {
            Path sortPath = Paths.get((String) currDir + File.separator + sortArg);
            try (BufferedReader reader = Files.newBufferedReader(sortPath, encoding)) {
                String line = reader.readLine();
                ArrayList<String> lines = new ArrayList<String>();
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
                throw new RuntimeException("sort: cannot open " + sortArg);
            }
        } else {
            throw new RuntimeException("sort: " + sortArg + " does not exist");
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
    private void argCheck(ArrayList<String> args) {
        if (args.isEmpty()) {
            throw new RuntimeException("sort: missing arguments");
        }
        if (args.size() != 1 && args.size() != 2) {
            throw new RuntimeException("sort: wrong number of arguments");
        }
        if (args.size() == 2 && !args.get(0).equals("-r")) {
            throw new RuntimeException("sort: wrong argument " + args.get(0));
        }
    }
}
