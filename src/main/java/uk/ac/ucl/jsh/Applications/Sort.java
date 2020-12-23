package uk.ac.ucl.jsh.Applications;

import uk.ac.ucl.jsh.Jsh;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Sort implements Application {

    private OutputStreamWriter writer;

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
    public String exec(ArrayList<String> args, String currDir, InputStream input, OutputStream output) throws IOException {
        this.writer = new OutputStreamWriter(output);

        if (args.isEmpty()) {
            args = new ArrayList<>(Files.readAllLines(Paths.get(String.valueOf(new Scanner(input)))));
        }

        String sortArg = ((args.size() == 2) ? args.get(1) : args.get(0));
        File filePath = new File(currDir + File.separator + sortArg);

        if (filePath.exists()) {
            try {
                /* Populate array with lines of the file */
                ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get(String.valueOf(filePath))));

                /* Use the quick-sort on the array list of strings */
                List<String> sortedLines;

                /* Check if the -r is present then display array in reverse order */
                if (args.size() == 2) {
                    sortedLines = lines.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
                    for (String sortedLine : sortedLines) {
                        writeOut(sortedLine);
                    }
                } else {
                    /* If -r is not present display array normally */
                    sortedLines = lines.stream().sorted().collect(Collectors.toList());
                    for (String sortedLine : sortedLines) {
                        writeOut(sortedLine);
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

    private void writeOut(String s) throws IOException {
        this.writer.write(s + Jsh.lineSeparator);
        this.writer.flush();
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