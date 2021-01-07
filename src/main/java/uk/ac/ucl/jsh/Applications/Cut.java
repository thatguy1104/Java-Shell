package uk.ac.ucl.jsh.Applications;

import uk.ac.ucl.jsh.Jsh;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cut implements Application {

    private OutputStreamWriter writer;
    private boolean pipe;

    @Override
    public String mainExec(ArrayList<String> args, String currentDirectory, InputStream input, OutputStream output) throws IOException {
        String message = argCheck(args);
        pipe = false;

        /* If input stream is present */
        ArrayList<String> temp = preProcess(input, args);
        if (args.size() == 3 && temp.size() != args.size()) {
            message = argCheck(temp);
            args = temp;
            pipe = true;
        }

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
        writer = new OutputStreamWriter(output);
        String concat_args = Stream.of(args.get(2)
                .replaceAll("[^-?0-9]+", " ")
                .split(" "))
                .map (String::new)
                .collect(Collectors.joining(", "));
        List<Integer> cleanArgs = parseCutInput(concat_args);
        if (cleanArgs.get(0) == -1) return "ERROR cut: could not convert arguments";
        String file_name = args.get(3);
        return process(currentDirectory, cleanArgs, file_name);
    }

    @Override
    public String argCheck(ArrayList<String> args) {
        if (args.size() == 1) return "cut: missing arguments";
        else if (args.size() != 2 && args.size() != 4) return "cut: wrong arguments";
        else if (!args.get(1).equals("-b")) return "cut: wrong argument " + args.get(1);
        else return "nothing";
    }

    @Override
    public void throwError(String message, OutputStream output) {
        throw new RuntimeException(message);
    }

    /**
     * Function to merge contents of the input stream with existing arguments
     * @return - ArrayList of strings
     */
    private ArrayList<String> preProcess(InputStream input, ArrayList<String> args) {
        ArrayList<String> result = new ArrayList<>(args);
        if (input != null) {
            Scanner scn = new Scanner(input);
            while (scn.hasNextLine()) {
                result.add(scn.nextLine());
            }
            scn.close();
        }
        return result;
    }

    /**
     * Function to process existence of input stream contents
     * @exception IOException throws exception in case of scanner error
     * @return - either an error in case of invalid file argument or current directory
     */
    private String process(String currentDirectory, List<Integer> cleanArgs, String fileName) throws IOException {
        File currFile = new File(currentDirectory + File.separator + fileName);
        Scanner scn;
        if (pipe) {
            scn = new Scanner(fileName);
        } else if (currFile.exists()) {
            scn = new Scanner(Paths.get(currentDirectory + File.separator + fileName));
        } else {
            return "ERROR cut: file does not exist";
        }
        if (!writeOut(scn, cleanArgs)) {
            return "ERROR cut: byte index specified does not exist";
        }
        return currentDirectory;
    }

    /**
     * Function to print to a specified output stream
     * @exception IOException throws exception in case of OutputStreamWriter failure
     * @return - boolean value representing whether or not the operation has been successful
     */
    private boolean writeOut(Scanner scn, List<Integer> validRange) throws IOException {
        while (scn.hasNextLine()) {
            String line = scn.nextLine();
            ArrayList<Character> separatedBytes = new ArrayList<>();
            for (int i : validRange) {
                if (i < 0) return false; /* invalid character index */
                else if (i < line.length()) separatedBytes.add(line.charAt(i));
            }
            String resultingLine = separatedBytes.stream().map(String::valueOf).collect(Collectors.joining());
            writer.write(resultingLine + Jsh.lineSeparator);
            writer.flush();
        }
        return true;
    }

    /**
     * Function to process cases of argument of type: e.g. 1- or 5-
     * @return - list of valid integer ranges
     */
    private List<Integer> parseCaseOne(List<String> innerRange) {
        List<Integer> finalList = new ArrayList<>();
        int numOfCharPerLine = 1000;

        try {
            int convertedElem = Integer.parseInt(innerRange.get(0));
            for (int j = convertedElem + 1; j < numOfCharPerLine; j++) {
                finalList.add(j - 2);
            }
        } catch (Exception f) {
            return null;
        }
        return finalList;
    }

    /**
     * Function to process cases of argument of type: e.g. 1-3 or 4-9
     * @return - list of valid integer ranges
     */
    private List<Integer> parseCaseTwo(List<String> innerRange) {
        List<Integer> finalList = new ArrayList<>();
        int convertedStart = Integer.parseInt(innerRange.get(0));
        int convertedEnd = Integer.parseInt(innerRange.get(1));
        for (int j = convertedStart; j <= convertedEnd; j++) {
            finalList.add(j - 1);
        }
        return finalList;
    }

    /**
     * Function to process cases of argument of type: e.g. 1,2 or 3,7,10
     * @return - list of valid integer ranges
     */
    private List<Integer> parseCaseZero(String elem) {
        List<Integer> finalList = new ArrayList<>();
        int intElem = Integer.parseInt(elem);
        if (intElem < 0) {
            for (int i = 1; i <= Math.abs(intElem); i++) {
                finalList.add(i - 1);
            }
        } else {
            finalList.add(intElem - 1);
        }
        return finalList;
    }

    /**
     * Function to clean argument list
     * @return - list of valid String arguments
     */
    private List<String> lineArgument(String elem) {
        return Stream.of(elem
                .replaceAll("[^-?0-9]+", " ")
                .split(" "))
                .map (String::new)
                .collect(Collectors.toList());
    }

    /**
     * Function parse cut case input
     * @return - final list of valid integer ranges
     */
    private List<Integer> parseCutInput(String str) {
        List<String> lineArgs = lineArgument(str);
        List<Integer> totalRange = new ArrayList<>();

        /* Parse each element to extend arguments where necessary */
        for (String elem : lineArgs) {
            try {
                totalRange = Stream.of(totalRange, parseCaseZero(elem)).flatMap(Collection::stream).collect(Collectors.toList());
            } catch (Exception e) {
                elem = elem.replaceAll("[^?0-9]+", " ");
                List<String> innerRange = Arrays.asList(elem.trim().split(" "));
                totalRange = parseCutSupplementary(innerRange, totalRange);
                return totalRange;
            }
        }
        return totalRange;
    }

    /**
     * Supporting method for parse_cut, uses logic cases to set an appropriate case range
     * @return - appropriate valid integer ranges
     */
    private List<Integer> parseCutSupplementary(List<String> innerRange, List<Integer> totalRange) {
        if (innerRange.size() == 1) {
            List<Integer> caseOne = parseCaseOne(innerRange);
            totalRange = Stream.of(totalRange, caseOne).filter(Objects::nonNull).flatMap(Collection::stream).collect(Collectors.toList());
        } else {
            List<Integer> caseTwo = parseCaseTwo(innerRange);
            totalRange = Stream.of(totalRange, caseTwo).filter(Objects::nonNull).flatMap(Collection::stream).collect(Collectors.toList());
        }
        return totalRange;
    }
}