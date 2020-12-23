package uk.ac.ucl.jsh.Applications;

import uk.ac.ucl.jsh.Jsh;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Tail implements Application {

    @Override
    public String mainExec(ArrayList<String> args, String currentDirectory, InputStream input, OutputStream output) {
        String message = argCheck(args);
        String appResult;
        if (!message.equals("nothing")) {
            throwError(message, output);
        } else {
            appResult = exec(args, currentDirectory, input, output);
            if (appResult.startsWith("ERROR")) {
                throwError(appResult.substring(6), output);
            }
            return appResult;
        }
        return "";
    }

    @Override
    public String exec(ArrayList<String> args, String currentDirectory, InputStream input, OutputStream output) {
        OutputStreamWriter writer = new OutputStreamWriter(output);
        int tailLines = 10;
        String tailArg;

        if (args.size() == 3) {
            try {
                tailLines = Integer.parseInt(args.get(1));
            } catch (Exception e) {
                return "ERROR tail: wrong argument " + args.get(1);
            }
            tailArg = args.get(2);
        } else {
            tailArg = args.get(0);
        }

        File tailFile = new File(currentDirectory + File.separator + tailArg);
        if (tailFile.exists()) {
            Charset encoding = StandardCharsets.UTF_8;
            Path filePath = Paths.get(currentDirectory + File.separator + tailArg);
            ArrayList<String> storage = new ArrayList<>();
            try (BufferedReader reader = Files.newBufferedReader(filePath, encoding)) {
                String line = null;
                while ((line = reader.readLine()) != null) {
                    storage.add(line);
                }
                int index = 0;

                if (tailLines <= storage.size()) {
                    index = storage.size() - tailLines;
                }

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
        } else if (args.size() != 1 && args.size() != 3) {
            return "tail: wrong arguments";
        } else if (args.size() == 3 && !args.get(0).equals("-n")) {
            return "tail: wrong argument " + args.get(0);
        } else {
            return "nothing";
        }
    }

    @Override
    public void throwError(String message, OutputStream output) {
        throw new RuntimeException(message);
    }
}
