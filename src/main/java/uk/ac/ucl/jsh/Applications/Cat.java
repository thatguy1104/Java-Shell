package uk.ac.ucl.jsh.Applications;

import uk.ac.ucl.jsh.Jsh;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Cat implements Application {

    @Override
    public String mainExec(ArrayList<String> args, String currentDirectory, InputStream input, OutputStream output) throws IOException {
        String message = argCheck(args);

        if (input != null && args.size() == 1) {
            message = "nothing";
        }

        if (!message.equals("nothing")) {
            throwError(message, output);
        } else {
            String appResult;
            appResult = exec(args, currentDirectory, input, output);
            if (appResult.startsWith("ERROR")) {
                throwError(appResult.substring(6), output);
            }
            return appResult;
        }
        return "";
    }

    @Override
    public String exec(ArrayList<String> args, String currentDirectory, InputStream input, OutputStream output) throws IOException {
        if (args.contains("`")) {
            args.remove("`");
        }
        if (args.contains("\"")) {
            while (args.contains("\"")) {
                args.remove("\"");
            }
        }
//        System.out.println("ARGS");
//        System.out.println(args);
        if (args.size() == 1) {
            writeOut(new Scanner(input), output);
        } else {
            args = checker(args);
            for (int i = 1; i < args.size(); i++) {
                Scanner scn;
                File currFile = new File(currentDirectory + File.separator + args.get(i));

                if (currFile.exists()) {
                    Path filePath = Paths.get(currentDirectory + File.separator + args.get(i));
                    try {
                        scn = new Scanner(filePath);
                        writeOut(scn, output);
                    } catch (FileNotFoundException e) {
                        throw new IOException("ERROR cat: " + e.getMessage());
                    }
                } else {
                    throw new IOException("ERROR cat: file does not exist");
                }
            }
        }
        return currentDirectory;
    }

    private ArrayList<String> checker(ArrayList<String> args) {
        ArrayList<String> result = new ArrayList<>(args);
        result.remove("backquote");
        if (result.contains("doublequote")) {
            while (result.contains("doublequote")) result.remove("doublequote");
        }
        return result;
    }

    /* Prints to specified output */
    private void writeOut(Scanner scn, OutputStream output) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(output);
        while (scn.hasNextLine()) {
            writer.write(scn.nextLine() + Jsh.lineSeparator);
            writer.flush();
        }
    }

    /* Validate args input */
    @Override
    public String argCheck(ArrayList<String> args) {
        if (args.isEmpty()) {
            return "cat: missing arguments";
        } else {
            return "nothing";
        }
    }

    @Override
    public void throwError(String message, OutputStream output) {
        throw new RuntimeException(message);
    }

}