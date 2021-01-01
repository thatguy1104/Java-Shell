package uk.ac.ucl.jsh.Applications;

import uk.ac.ucl.jsh.Jsh;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class Find implements Application {

    private String stableDirectory;

    @Override
    public String mainExec(ArrayList<String> args, String currentDirectory, InputStream input, OutputStream output) throws IOException {
        stableDirectory = currentDirectory;
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
        File cur;
        OutputStreamWriter writer = new OutputStreamWriter(output);
        int argSizeCheck = 3;
        String directoryCheck = currentDirectory;
        boolean directorySpecified = false;
        boolean globbing = false;

        if (!args.get(1).equals("-name")) {
            argSizeCheck += 1;
            directoryCheck = directoryCheck + "/" + args.get(1);
            directorySpecified = true;
        }

        if (args.get(args.size() - 1).contains("*.")) {
            globbing = true;
        }

        cur = new File(directoryCheck);

        try {
            File[] listOfFiles = cur.listFiles();
            assert listOfFiles != null;

            Set<String> result_set = new HashSet<>();

            for (File file : listOfFiles) {
                if (!file.getName().startsWith(".")) {
                    HashMap<String, String> all_files = walkFileDirs(file);
                    result_set.addAll(getCorrectFiles(args, all_files, globbing, directorySpecified, argSizeCheck));
                }
            }
            writeOut(result_set, writer);
        } catch (NullPointerException e) {
            return "ERROR find: no such directory";
        }
        return currentDirectory;
    }

    /* Calls the relevant method to find the correct files in the list of all files */
    private Set<String> getCorrectFiles(ArrayList<String> args, HashMap<String, String> all_files, Boolean globbing, Boolean directorySpecified, int argSizeCheck){
        Set<String> result_set = new HashSet<>();
        for (Map.Entry<String, String> entry : all_files.entrySet()) {
            if (!globbing) {
                result_set.addAll(caseSingleName(args, entry, directorySpecified, argSizeCheck));
            } else {
                result_set.addAll(caseGlobbing(args, entry, directorySpecified));
            }
        }
        return result_set;
    }

    /* Finds the files with a matching name */
    private Set<String> caseSingleName(ArrayList<String> args, Map.Entry<String, String> entry, Boolean directorySpecified, int argSizeCheck) {
        Set<String> result_set = new HashSet<>();
        for (String currentArgument : args.subList(argSizeCheck - 1, args.size())) {
            if (currentArgument.equals(entry.getValue())) {
                if (entry.getKey().equals(System.lineSeparator() + entry.getValue())) {
                    result_set.add(entry.getValue());
                } else {
                    if (!directorySpecified) {
                        result_set.add("." + entry.getKey());
                    } else {
                        result_set.add(entry.getKey().substring(1));
                    }
                }
            }
        }
        return result_set;
    }

    /* Finds all the files with the file extension */
    private Set<String> caseGlobbing(ArrayList<String> args, Map.Entry<String, String> entry, Boolean directorySpecified){
        Set<String> result_set = new HashSet<>();
        if (args.get(args.size() - 1).substring(2).equals(entry.getValue().substring(entry.getValue().length() - args.get(args.size() - 1).substring(2).length()))) {
            if (!directorySpecified) {
                result_set.add( "." + entry.getKey());
            } else {
                if (entry.getKey().equals(System.lineSeparator() + entry.getValue())) {
                    result_set.add(entry.getValue());
                } else {
                    result_set.add(entry.getKey().substring(1));
                }
            }
        }
        return result_set;
    }

    /* Gets all files in a directory an it's subdirectories */
    private HashMap<String, String> walkFileDirs(File fileDirectory) throws IOException {
        HashMap<String, String> walk_result = new HashMap<>();

        Files.walk(fileDirectory.toPath())
                .filter(path -> !Files.isDirectory(path))
                .forEach(path -> walk_result.put(path.toString().substring(stableDirectory.length()), path.getFileName().toString()));
        return walk_result;
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