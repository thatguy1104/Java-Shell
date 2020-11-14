package uk.ac.ucl.jsh.Applications;

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

public class Head implements Application {

    @Override
    public String exec(ArrayList<String> args, String currentDirectory, OutputStream output) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(output);

        String message = argCheck(args);
        if (message != "nothing") {
            throwError(message, output);
        }

        int headLines = 10;
        String headArg;
        if (args.size() == 3) {
            try {
                headLines = Integer.parseInt(args.get(1));
            } catch (Exception e) {
                throw new RuntimeException("head: wrong argument " + args.get(1));
            }
            headArg = args.get(2);
        } else {
            headArg = args.get(0);
        }
        File headFile = new File(currentDirectory + File.separator + headArg);
        if (headFile.exists()) {
            Charset encoding = StandardCharsets.UTF_8;
            Path filePath = Paths.get(currentDirectory + File.separator + headArg);
            try (BufferedReader reader = Files.newBufferedReader(filePath, encoding)) {
                writeOut(reader, writer, headLines);
            } catch (IOException e) {
                throw new RuntimeException("head: cannot open " + headArg);
            }
        } else {
            throw new RuntimeException("head: " + headArg + " does not exist");
        }

        return currentDirectory;
    }

    /* Prints to specified output */
    private void writeOut(BufferedReader reader, OutputStreamWriter writer, int headLines) throws IOException {
        for (int i = 0; i < headLines; i++) {
            String line;
            if ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.write(System.getProperty("line.separator"));
                writer.flush();
            }
        }
    }

    /* Validates arguments input */
    public String argCheck(ArrayList<String> args) {
        if (args.isEmpty()) {
            return "head: missing arguments";
            //throw new RuntimeException("head: missing arguments");
        } else if (args.size() != 1 && args.size() != 3) {
            return "head: wrong arguments";
            //throw new RuntimeException("head: wrong arguments");
        } else if (args.size() == 3 && !args.get(0).equals("-n")) {
            return "head: wrong argument " + args.get(0);
            //throw new RuntimeException("head: wrong argument " + args.get(0));
        } else {
            return "nothing";
        }
    }

    public void throwError(String message, OutputStream output) throws IOException {
        throw new RuntimeException(message);
    }
}