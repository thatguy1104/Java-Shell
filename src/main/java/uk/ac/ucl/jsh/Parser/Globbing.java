package uk.ac.ucl.jsh.Parser;

import java.io.File;
import java.util.ArrayList;

public class Globbing {

    public ArrayList<File> globFiles(String input, String currentDir) {
        ArrayList<File> result = new ArrayList<>();
        File[] f_list = new File(currentDir).listFiles();
        assert f_list != null;

        for (File file : f_list) {
            if (file.getName().contains(input.substring(1))) {
                result.add(file);
            }
        }
        return result;
    }
}
