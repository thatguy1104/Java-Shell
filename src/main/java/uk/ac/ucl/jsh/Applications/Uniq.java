package uk.ac.ucl.jsh.Applications;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Uniq implements Application {
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
        String uniqFilename;

        if (args.size() == 2) {
            uniqFilename = args.get(1);
        } else {
            uniqFilename = args.get(0);
        }

        /* String input = appArgs(*) */
        File uniqFile = new File(currDir + File.separator + uniqFilename);
        if (uniqFile.exists()) {
            // Charset encoding = StandardCharsets.UTF_8;
            Path sortPath = Paths.get(currDir + File.separator + uniqFilename);
            try (BufferedReader reader = Files.newBufferedReader(sortPath /* , encoding */)) {
                String line = reader.readLine();
                ArrayList<String> lines = new ArrayList<>();
                ArrayList<String> uniqLines;

                /* Populate array with lines of the file */
                while (line != null) {
                    lines.add(line);
                    line = reader.readLine();
                }

                /* Check if the -i and if exists make comparision case insensitive */
                if (args.size() == 2) {
                    uniqLines = new ArrayList<>();

                    for (int i = 0; i < lines.size(); i++) {
                        boolean equals = false;
                        String row = lines.get(i);
                        for (int j = i + 1; j < lines.size(); j++) {
                            String row2 = lines.get(j);
                            if (row.equalsIgnoreCase(row2)) {
                                equals = true;
                            }
                        }
                        if (!equals && !uniqLines.contains(row)) {
                            uniqLines.add(row);
                        }
                    }
                } else { /* Case sensitive */
                    LinkedHashSet<String> uniqSet = new LinkedHashSet<>(lines);
                    uniqLines = new ArrayList<>(uniqSet);
                }

                /* Display array of uniq lines */
                writeOut(uniqLines, writer);

            } catch (IOException e) {
                return "ERROR uniq: cannot open " + uniqFilename;
            }
        } else {
            return "ERROR uniq: " + uniqFilename + " does not exist";
        }

        return currDir;
    }

    /* Prints to specified output */
    private void writeOut(ArrayList<String> uniqLines, OutputStreamWriter writer) throws IOException {
        for (String uniqLine : uniqLines) {
            writer.write(uniqLine);
            writer.write(System.getProperty("line.separator"));
            writer.flush();
        }
    }

    /* Validates arguments input */
    @Override
    public String argCheck(ArrayList<String> args) {
        if (args.isEmpty()) {
            return "uniq: missing argument";
        } else if (args.size() > 2) {
            return "uniq: too many arguments";
        } else if (args.size() == 2 && !args.get(0).equals("-i")) {
            return "uniq: wrong argument" + args.get(0);
        } else {
            return "nothing";
        }
    }

    @Override
    public void throwError(String message, OutputStream output) {
        throw new RuntimeException(message);
    }
}
