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

    private OutputStreamWriter writer;

    @Override
    public String mainExec(ArrayList<String> args, String currentDirectory, OutputStream output) {
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
    public String exec(ArrayList<String> args, String currentDirectory, OutputStream output) {
        writer = new OutputStreamWriter(output);
        int headLines = 10;
        String headArg;
        if (args.size() == 3) {
            try {
                headLines = Integer.parseInt(args.get(1));
            } catch (Exception e) {
                return "ERROR head: wrong argument " + args.get(1);
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
                writeOut(reader, headLines);
            } catch (IOException e) {
                return "ERROR head: cannot open " + headArg;
            }
        } else {
            return "ERROR head: " + headArg + " does not exist";
        }

        return currentDirectory;
    }

    /* Prints to specified output */
    private void writeOut(BufferedReader reader, int headLines) throws IOException {
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
    @Override
    public String argCheck(ArrayList<String> args) {
        if (args.isEmpty()) {
            return "head: missing arguments";
        } else if (args.size() != 1 && args.size() != 3) {
            return "head: wrong arguments";
        } else if (args.size() == 3 && !args.get(0).equals("-n")) {
            return "head: wrong argument " + args.get(0);
        } else {
            return "nothing";
        }
    }

    @Override
    public void throwError(String message, OutputStream output) {
        throw new RuntimeException(message);
    }
}
