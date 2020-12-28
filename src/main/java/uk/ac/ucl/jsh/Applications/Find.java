package uk.ac.ucl.jsh.Applications;

import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.Parser.Globbing;

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
        Boolean directorySpecified = false;
        Boolean globbing = false;

        if (!args.get(1).equals("-name")) {
            argSizeCheck += 1;
            directoryCheck = directoryCheck + "\\" + args.get(1);
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

                    for (Map.Entry<String, String> entry : all_files.entrySet()) {
                        if (!globbing) {
                            for (String currentArgument : args.subList(argSizeCheck - 1, args.size())) {
                                if (currentArgument.equals(entry.getValue())) {
                                    if (entry.getKey().equals("/" + entry.getValue())) {
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
                        } else {
                            //System.out.println(args.get(args.size() - 1).substring(2));
                            //System.out.print(entry.getValue().substring(entry.getValue().length() - args.get(args.size() - 1).substring(2).length()));
                            if (args.get(args.size() - 1).substring(2).equals(entry.getValue().substring(entry.getValue().length() - args.get(args.size() - 1).substring(2).length()))) {
                                //System.out.println("HOYAH");
                                if (entry.getKey().equals("/" + entry.getValue())) {
                                    if (!directorySpecified) {
                                        result_set.add( "." + entry.getKey());
                                    } else {
                                        result_set.add(entry.getValue());
                                    }
                                } else {
                                    if (!directorySpecified) {
                                        result_set.add("." + entry.getKey());
                                    } else {
                                        result_set.add(entry.getKey().substring(1));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            //System.out.println(result_set);
            writeOut(result_set, writer);
        } catch (NullPointerException e) {
            return "ERROR find: no such directory";
            //throw new RuntimeException("find: no such directory");
        }
        return currentDirectory;
    }

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