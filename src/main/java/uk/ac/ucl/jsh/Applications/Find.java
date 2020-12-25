package uk.ac.ucl.jsh.Applications;

import uk.ac.ucl.jsh.Jsh;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Find implements Application {

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
        File cur = new File(currentDirectory);
        OutputStreamWriter writer = new OutputStreamWriter(output);
        args.remove(0);

        try {
            File[] listOfFiles = cur.listFiles();
            Set<String> result_set = new HashSet<>();
            assert listOfFiles != null;
            for (File file : listOfFiles) {
                if (!file.getName().startsWith(".")) {
                    if (args.size() == 1 && args.get(0).equals(file.getName())) {
                        result_set.add(file.getName());
                    } else if (args.size() > 1) {
                        result_set.addAll(args);
                    }
                }
            }
            writeOut(result_set, writer);
        } catch (NullPointerException e) {
            return "ERROR find: no such directory";
            //throw new RuntimeException("find: no such directory");
        }

        return currentDirectory;
    }

    /* Prints to specified output */
    private void writeOut(Set<String> result_set, OutputStreamWriter writer) throws IOException {
        for (String item : result_set) {
            writer.write(item + Jsh.lineSeparator);
            writer.flush();
        }
    }

    /* Validates arguments input */
    @Override
    public String argCheck(ArrayList<String> args) {
        if (args.isEmpty()) {
            return "find: missing arguemnts";
        } else {
            return "nothing";
        }
    }

    @Override
    public void throwError(String message, OutputStream output) {
        throw new RuntimeException(message);
    }
}