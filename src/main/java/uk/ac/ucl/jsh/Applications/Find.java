package uk.ac.ucl.jsh.Applications;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Find implements Application {

    @Override
    public String exec(ArrayList<String> args, String currentDirectory, OutputStream output) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(output);
        File cur = new File(currentDirectory);
        
        String message = argCheck(args);
        if (message != "nothing") {
            throwError(message, output);
        }
        
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
            throw new RuntimeException("find: no such directory");
        }

        return currentDirectory;
    }

    /* Prints to specified output */
    private void writeOut(Set<String> result_set, OutputStreamWriter writer) throws IOException {
        for (String item : result_set) {
            writer.write(item);
            writer.write("\n");
            writer.flush();
        }
    }

    /* Validates arguments input */
    public String argCheck(ArrayList<String> args) {
        if (args.isEmpty()) {
            return "find: missing arguemnts";
            //throw new RuntimeException("find: missing arguemnts");
        } else {
            return "nothing";
        }
    }

    public void throwError(String message, OutputStream output) throws IOException {
        throw new RuntimeException(message);
    }
}