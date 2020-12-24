package uk.ac.ucl.jsh.Applications;

import uk.ac.ucl.jsh.Jsh;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Tail implements Application {

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
    public String exec(ArrayList<String> args, String currentDirectory, InputStream input, OutputStream output) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(output);
        int tailLines = 10;
        String tailArg;

        if (args.size() == 4) {
            try {
                tailLines = Integer.parseInt(args.get(2));
            } catch (Exception e) {
                return "ERROR tail: wrong argument " + args.get(2);
            }
            tailArg = args.get(3);
        } else {
            tailArg = args.get(1);
        }

        File tailFile = new File(currentDirectory + File.separator + tailArg);
        if (tailFile.exists()) {
            ArrayList<String> storage = new ArrayList<>(Files.readAllLines(Paths.get(String.valueOf(tailFile))));
            try {
                int index = ((tailLines <= storage.size()) ? storage.size() - tailLines : 0);
                for (int i = index; i < storage.size(); i++) {
                    writer.write(storage.get(i) + Jsh.lineSeparator);
                    writer.flush();
                }
            } catch (IOException e) {
                return "ERROR tail: cannot open " + tailArg;
            }
        } else {
            return "tail: " + tailArg + " does not exist";
        }
        return currentDirectory;
    }

    /* Validates arguments input */
    @Override
    public String argCheck(ArrayList<String> args) {
        if (args.isEmpty()) {
            return "tail: missing arguments";
        } else if (args.size() != 2 && args.size() != 4) {
            return "tail: wrong arguments";
        } else if (args.size() == 4 && !args.get(1).equals("-n")) {
            return "tail: wrong argument " + args.get(1);
        } else {
            return "nothing";
        }
    }

    @Override
    public void throwError(String message, OutputStream output) {
        throw new RuntimeException(message);
    }
}