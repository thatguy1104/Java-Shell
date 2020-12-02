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

public class Cat implements Application {

    private OutputStreamWriter writer;

    @Override
    public String mainExec(ArrayList<String> args, String currentDirectory, OutputStream output) throws IOException {
        String message = argCheck(args);
        String appResult;
        if (!message.equals("nothing")) {
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
    public String exec(ArrayList<String> args, String currentDirectory, OutputStream output) throws IOException {
        writer = new OutputStreamWriter(output);
        // writer.write("first\n");
        // writer.write(System.getProperty("line.separator"));
        // writer.flush();

        for (String arg : args) {
            Charset encoding = StandardCharsets.UTF_8;
            File currFile = new File(currentDirectory + File.separator + arg);
            // writer.write("currFile: " + currFile + "\n");
            // writer.write(System.getProperty("line.separator"));
            // writer.flush();
            if (currFile.exists()) {
                Path filePath = Paths.get(currentDirectory + File.separator + arg);
                try (BufferedReader reader = Files.newBufferedReader(filePath, encoding)) {
                    writeOut(reader);
                } catch (IOException e) {
                    return "ERROR cat: cannot open " + arg;
                }
            } else {
                return "ERROR cat: file does not exist";
            }
        }
        // writer.write("returning current directory\n");
        // writer.write(System.getProperty("line.separator"));
        // writer.flush();
        return currentDirectory;
    }

    /* Prints to specified output */
    private void writeOut(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            writer.write(line);
            writer.write(System.getProperty("line.separator"));
            writer.flush();
        }
    }

    /* Validate args input */
    @Override
    public String argCheck(ArrayList<String> args) {
        if (args.isEmpty()) {
            return "cat: missing arguments";
        } else {
            return "nothing";
        }
    }

    @Override
    public void throwError(String message, OutputStream output) {
        throw new RuntimeException(message);
    }
}
