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
    public String exec(ArrayList<String> args, String currDir, InputStream input, OutputStream output) throws IOException {
        this.writer = new OutputStreamWriter(output);
        ArrayList<String> new_args = new ArrayList<>();

        if (args.size() == 1) {
            Scanner scn = new Scanner(input);
            while (scn.hasNextLine()) {
                new_args.add(scn.nextLine());
            }
            returnSorted(args, new_args);
        } else {
            String sortArg = ((args.size() == 3) ? args.get(2) : args.get(1));
            File filePath = new File(currDir + File.separator + sortArg);

            if (filePath.exists()) {
                try {
                    /* Populate array with lines of the file */
                    ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get(String.valueOf(filePath))));
                    returnSorted(args, lines);

                } catch (IOException e) {
                    return "ERROR sort: cannot open " + sortArg;
                }
            } else {
                return "ERROR sort: " + sortArg + " does not exist";
            }
        }
        return currDir;
    }

    private void returnSorted(ArrayList<String> args, ArrayList<String> lines) throws IOException {
        List<String> sortedLines;
        /* Check if the -r is present then display array in reverse order */
        if (args.size() == 3) {
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
        } else if (args.size() != 2 && args.size() != 3) {
            return "sort: wrong number of arguments";
        } else if (args.size() == 3 && !args.get(1).equals("-r")) {
            return "sort: wrong argument " + args.get(1);
        } else {
            return "nothing";
        }
    }

    @Override
    public void throwError(String message, OutputStream output) {
        throw new RuntimeException(message);
    }
}