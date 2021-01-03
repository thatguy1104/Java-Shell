package uk.ac.ucl.jsh.Parser;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Globbing {

    public ArrayList<File> globFiles(String input, String currentDir) {
        boolean all = false;
        if (input.startsWith("*")) all = true;

        Set<File> result = new HashSet<>();
        File[] f_list = new File(currentDir).listFiles();
        assert f_list != null;

        for (File file : f_list) {
            if (file.getName().contains(input.substring(1))) {
                result.add(file);
            }
            if (all) {
                result.add(file);
            }
        }
        return new ArrayList<>(result);
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

//    private void process() {
//        String files = "*.txt";
//        String currentDirectory = System.getProperty("user.dir") + "/TESTDIR";
//        ArrayList<File> f = determineType(files, currentDirectory);
//        System.out.println(f);
//        String dir = "TESTDIR/*";
//        String currentDirectory2 = System.getProperty("user.dir");
//        ArrayList<File> f = determineType(dir, currentDirectory2);

//    }

//    public static void main(String[] args) {
//        new Globbing().process();
//    }
}
