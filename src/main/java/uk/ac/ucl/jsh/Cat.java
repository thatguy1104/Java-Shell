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

public class Cat implements Application {

    @Override
    public String exec(ArrayList<String> args, String currentDirectory, OutputStream output) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(output);

        argCheck(args);

        for (String arg : args) {
            Charset encoding = StandardCharsets.UTF_8;
            File currFile = new File(currentDirectory + File.separator + arg);
            if (currFile.exists()) {
                Path filePath = Paths.get(currentDirectory + File.separator + arg);
                try (BufferedReader reader = Files.newBufferedReader(filePath, encoding)) {
                    writeOut(reader, writer);
                } catch (IOException e) {
                    throw new RuntimeException("cat: cannot open " + arg);
                }
            } else {
                throw new RuntimeException("cat: file does not exist");
            }
        }
        
        return currentDirectory;
    }

    /* Validate args input */
    private void argCheck(ArrayList<String> args) throws IOException {
        if (args.isEmpty()) {
            throw new RuntimeException("cat: missing arguments");
        }
    }

    /* Prints to specified output */
    private void writeOut(BufferedReader reader, OutputStreamWriter writer) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            writer.write(String.valueOf(line));
            writer.write(System.getProperty("line.separator"));
            writer.flush();
        }
    }
}
