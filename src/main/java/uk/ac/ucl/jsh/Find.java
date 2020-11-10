package uk.ac.ucl.jsh;

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
        
        if (args.isEmpty()) {
            throw new RuntimeException("find: missing arguemnts");
        }
        try {
            File[] listOfFiles = cur.listFiles();
            Set<String> result_set = new HashSet<String>();
            for (File file : listOfFiles) {
                if (!file.getName().startsWith(".")) {
                    if (args.size() == 1 && args.get(0).equals(file.getName())) {
                        result_set.add(file.getName());
                    } else if (args.size() > 1) {
                        for (String ar : args) {
                            result_set.add(ar);
                        }
                    }
                }
            }
            for (String item : result_set) {
                writer.write(item);
                writer.write("\n");
                writer.flush();
            }
        } catch (NullPointerException e) {
            throw new RuntimeException("find: no such directory");
        }

        return currentDirectory;
    }
}
