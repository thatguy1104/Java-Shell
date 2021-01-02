package uk.ac.ucl.jsh.Parser;

import java.io.File;
import java.util.ArrayList;

public class Globbing {

    public ArrayList<File> globFiles(String input, String currentDir) {
        boolean all = false;
        if (input.startsWith("*")) all = true;

        ArrayList<File> result = new ArrayList<>();
        File[] f_list = new File(currentDir).listFiles();
        assert f_list != null;

        for (File file : f_list) {
            if (file.getName().contains(input.substring(1))) {
                result.add(file);
            }
            if (all) {
                result.remove(file);
                result.add(new File(System.getProperty("user.dir") + File.separator + file));
            }
        }
        return result;
    }

    public ArrayList<File> determineType(String input, String currentDirectory) {
        ArrayList<File> results = new ArrayList<>();
        if (input.startsWith("*")) {
            results = globFiles(input.substring(1), currentDirectory);
        }

        if (input.endsWith("*")) {
            results = globFiles("*", input.substring(0, input.length() - 2));
        }

        return results;
    }
}