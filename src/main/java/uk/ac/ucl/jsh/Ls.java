package uk.ac.ucl.jsh;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Ls implements Application {

    private void argCheck(ArrayList<String> args) {
        if (!args.isEmpty() && args.size() != 1) {
            throw new RuntimeException("ls: too many arguments");
        }
    }

    @Override
    public String exec(ArrayList<String> args, String currentDirectory, OutputStream output) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(output);

        File currDir;

        argCheck(args);

        if (args.isEmpty()) {
            currDir = new File(currentDirectory);
        } else {
            currDir = new File(args.get(0));
        }
        
        try {
            File[] listOfFiles = currDir.listFiles();
            boolean atLeastOnePrinted = false;
            assert listOfFiles != null;
            for (File file : listOfFiles) {
                if (!file.getName().startsWith(".")) {
                    writer.write(file.getName());
                    writer.write("\t");
                    writer.flush();
                    atLeastOnePrinted = true;
                }
            }
            if (atLeastOnePrinted) {
                writer.write(System.getProperty("line.separator"));
                writer.flush();
            }
        } catch (NullPointerException e) {
            throw new RuntimeException("ls: no such directory");
        }
        return currentDirectory;
    }
}
