package uk.ac.ucl.jsh.Applications;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Uniq implements Application {

    @Override
    public String exec(ArrayList<String> args, String currDir, OutputStream output) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(output);
        String uniqFilename;

        String message = argCheck(args);
        if (message != "nothing") {
            throwError(message, output);
        }

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
                writeOut(reader, uniqLines, writer);

            } catch (IOException e) {
                throw new RuntimeException("uniq: cannot open " + uniqFilename);
            }
        } else {
            throw new RuntimeException("uniq: " + uniqFilename + " does not exist");
        }

        return currDir;
    }

    /* Prints to specified output */
    private void writeOut(BufferedReader reader, ArrayList<String> uniqLines, OutputStreamWriter writer) throws IOException {
        for (String uniqLine : uniqLines) {
            writer.write(uniqLine);
            writer.write(System.getProperty("line.separator"));
            writer.flush();
        }
    }

    /* Validates arguments input */
    public String argCheck(ArrayList<String> args) {
        if (args.isEmpty()) {
            return "uniq: missing argument";
            //throw new RuntimeException("uniq: missing argument");
        } else if (args.size() > 2) {
            return "uniq: too many arguments";
            //throw new RuntimeException("uniq: too many arguments");
        } else if (args.size() == 2 && !args.get(0).equals("-i")) {
            return "uniq: wrong argument" + args.get(0);
            //throw new RuntimeException("uniq: wrong argument" + args.get(0));
        } else {
            return "nothing";
        }
    }

    public void throwError(String message, OutputStream output) throws IOException {
        throw new RuntimeException(message);
    }
}
