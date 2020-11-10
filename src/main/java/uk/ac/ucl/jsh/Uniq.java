package uk.ac.ucl.jsh;

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

        if (args.isEmpty()) {
            throw new RuntimeException("uniq: missing argument");
        } else if (args.size() > 2) {
            throw new RuntimeException("uniq: too many arguments");
        }
        if (args.size() == 2 && !args.get(0).equals("-i")) {
            throw new RuntimeException("uniq: wrong argument" + args.get(0));
        }
        if (args.size() == 2) {
            uniqFilename = args.get(1);
        } else {
            uniqFilename = args.get(0);
        }

        // String input = appArgs(*)
        File uniqFile = new File(currDir + File.separator + uniqFilename);
        if (uniqFile.exists()) {
            // Charset encoding = StandardCharsets.UTF_8;
            Path sortPath = Paths.get((String) currDir + File.separator + uniqFilename);
            try (BufferedReader reader = Files.newBufferedReader(sortPath /* , encoding */)) {
                String line = reader.readLine();
                ArrayList<String> lines = new ArrayList<String>();
                ArrayList<String> uniqLines;

                // Populate array with lines of the file
                while (line != null) {
                    lines.add(line);
                    line = reader.readLine();
                }

                // Check if the -i and if exists make comparision case insensitive
                if (args.size() == 2) {
                    uniqLines = new ArrayList<String>();

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
                } else { // case sensitive
                    LinkedHashSet<String> uniqSet = new LinkedHashSet<>(lines);
                    uniqLines = new ArrayList<>(uniqSet);
                }

                // display array of uniq lines
                for (int i = 0; i < uniqLines.size(); i++) {
                    writer.write(uniqLines.get(i));
                    writer.write(System.getProperty("line.separator"));
                    writer.flush();
                }

            } catch (IOException e) {
                throw new RuntimeException("uniq: cannot open " + uniqFilename);
            }
        } else {
            throw new RuntimeException("uniq: " + uniqFilename + " does not exist");
        }

        return currDir;
    }
}
