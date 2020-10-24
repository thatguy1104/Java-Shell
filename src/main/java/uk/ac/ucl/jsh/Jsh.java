package uk.ac.ucl.jsh;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;
import javax.sound.midi.SysexMessage;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Jsh {

    private static String currentDirectory = System.getProperty("user.dir");

    public static List<Integer> parse_cut_input(String str) throws IOException {
        // Num of char per line:
        int num_of_char_per_line = 1000;

        // SPLIT THE ARGS
        str = str.replaceAll("[^-?0-9]+", " ");
        List<String> line_args = Arrays.asList(str.trim().split(" "));
        List<Integer> total_range = new ArrayList<Integer>();

        // PARSE EACH ELEM TO EXTEND ARGS IF NECESSARY
        for (String elem : line_args) {
            try {
                // 1,2,3
                int int_elem = Integer.parseInt(elem);
                if (int_elem < 0) {
                    for (int i = 1; i <= Math.abs(int_elem); i++) {
                        total_range.add(i - 1);
                    }
                } else {
                    total_range.add(int_elem - 1);
                }
            } catch (Exception e) {
                // Everything that is X-
                elem = elem.replaceAll("[^?0-9]+", " ");
                List<String> inner_range = Arrays.asList(elem.trim().split(" "));

                // 1-3
                if (inner_range.size() == 2) {
                    try {
                        int converted_start = Integer.parseInt(inner_range.get(0));
                        int converted_end = Integer.parseInt(inner_range.get(1));
                        for (int j = converted_start; j <= converted_end; j++) {
                            if (!total_range.contains(j)) {
                                total_range.add(j - 1);
                            }
                        }
                    } catch (Exception f) {
                        throw new IOException("cut: could not convert args");
                    }

                } else if (inner_range.size() == 1) {
                    // 5-, 4-
                    try {
                        int converted_elem = Integer.parseInt(inner_range.get(0));
                        for (int j = converted_elem; j < num_of_char_per_line; j++) {
                            if (!total_range.contains(j)) {
                                total_range.add(j - 1);
                            }
                        }
                    } catch (Exception f) {
                        throw new IOException("cut: could not convert arguments");
                    }
                } else {
                    throw new IOException("cut: incorrect list ranges");
                }
            }
        }
        return total_range;
    }

    //Recursive quicksort algorithm for sorting an array list of strings
    public static ArrayList<String> stringQuicksort(ArrayList<String> lines){
        //Base case if the file read is empty
        if (lines.isEmpty()){
            return lines;
        }
        ArrayList<String> beforePivot = new ArrayList<String>();
        ArrayList<String> afterPivot = new ArrayList<String>();
        
        //Initialise the pivot to be the first element of the array (line of file)
        String pivot = lines.get(0);
        String line;

        //Compare each line to the pivot and add to respective array
        for(int i = 1; i < lines.size(); i++){
            line = lines.get(i);
            if(line.compareTo(pivot) < 0){
                beforePivot.add(line);
            }else{
                afterPivot.add(line);
            }
        }

        //Recursively sort the arrays
        beforePivot = stringQuicksort(beforePivot);
        afterPivot = stringQuicksort(afterPivot);

        //Combine and return the final array
        beforePivot.add(pivot);
        beforePivot.addAll(afterPivot);
        return beforePivot;
    }

    public static void eval(String cmdline, OutputStream output) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(output);

        CharStream parserInput = CharStreams.fromString(cmdline);
        JshGrammarLexer lexer = new JshGrammarLexer(parserInput);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        JshGrammarParser parser = new JshGrammarParser(tokenStream);
        ParseTree tree = parser.command();
        ArrayList<String> rawCommands = new ArrayList<String>();
        String lastSubcommand = "";
        for (int i = 0; i < tree.getChildCount(); i++) {
            if (!tree.getChild(i).getText().equals(";")) {
                lastSubcommand += tree.getChild(i).getText();
            } else {
                rawCommands.add(lastSubcommand);
                lastSubcommand = "";
            }
        }
        rawCommands.add(lastSubcommand);
        for (String rawCommand : rawCommands) {
            String spaceRegex = "[^\\s\"']+|\"([^\"]*)\"|'([^']*)'";
            ArrayList<String> tokens = new ArrayList<String>();
            Pattern regex = Pattern.compile(spaceRegex);
            Matcher regexMatcher = regex.matcher(rawCommand);
            String nonQuote;
            while (regexMatcher.find()) {
                if (regexMatcher.group(1) != null || regexMatcher.group(2) != null) {
                    String quoted = regexMatcher.group(0).trim();
                    tokens.add(quoted.substring(1, quoted.length() - 1));
                } else {
                    nonQuote = regexMatcher.group().trim();
                    ArrayList<String> globbingResult = new ArrayList<String>();
                    Path dir = Paths.get(currentDirectory);
                    DirectoryStream<Path> stream = Files.newDirectoryStream(dir, nonQuote);
                    for (Path entry : stream) {
                        globbingResult.add(entry.getFileName().toString());
                    }
                    if (globbingResult.isEmpty()) {
                        globbingResult.add(nonQuote);
                    }
                    tokens.addAll(globbingResult);
                }
            }
            String appName = tokens.get(0);
            ArrayList<String> appArgs = new ArrayList<String>(tokens.subList(1, tokens.size()));

            Boolean unsafeMode = false;
            if(appName.charAt(0) == '_'){
                appName = appName.substring(1);
                unsafeMode = true;
            }
            
            switch (appName) {
                case "cd":
                    if (unsafeMode) {
                        if (appArgs.isEmpty()) {
                            writer.write("_cd: missing argument");
                            writer.write(System.getProperty("line.separator"));
                            writer.flush();
                        } else if (appArgs.size() > 1) {
                            writer.write("_cd: too many arguments");
                            writer.write(System.getProperty("line.separator"));
                            writer.flush();
                        } else {
                            String dirString = appArgs.get(0);
                            File dir = new File(currentDirectory, dirString);
                            if (!dir.exists() || !dir.isDirectory()) {
                                writer.write("_cd: " + dirString + " is not an existing directory");
                                writer.write(System.getProperty("line.separator"));
                                writer.flush();
                            } else {
                                currentDirectory = dir.getCanonicalPath();
                            }
                        }
                    } else {
                        if (appArgs.isEmpty()) {
                            throw new RuntimeException("cd: missing argument");
                        } else if (appArgs.size() > 1) {
                            throw new RuntimeException("cd: too many arguments");
                        }
                        String dirString = appArgs.get(0);
                        File dir = new File(currentDirectory, dirString);
                        if (!dir.exists() || !dir.isDirectory()) {
                            throw new RuntimeException("cd: " + dirString + " is not an existing directory");
                        }
                        currentDirectory = dir.getCanonicalPath();
                    }
                    break;
                case "pwd":
                    writer.write(currentDirectory);
                    writer.write(System.getProperty("line.separator"));
                    writer.flush();
                    break;
                case "ls":
                    File currDir;
                    if (unsafeMode) {
                        if (appArgs.size() > 1) {
                            writer.write("_ls: too many arguments");
                            writer.write(System.getProperty("line.separator"));
                            writer.flush();
                        } else {
                            if (appArgs.isEmpty()) {
                                currDir = new File(currentDirectory);
                            } else {
                                currDir = new File(appArgs.get(0));
                            }
                            if (!currDir.exists() || !currDir.isDirectory()) {
                                writer.write("_ls: no such directory");
                                writer.write(System.getProperty("line.separator"));
                                writer.flush();
                            } else {
                                File[] listOfFiles = currDir.listFiles();
                                boolean atLeastOnePrinted = false;
                                for (File file : listOfFiles) {
                                    if (!file.getName().startsWith(".")) {
                                        writer.write(file.getName());
                                        writer.write("\t");
                                        writer.flush();
                                        atLeastOnePrinted = true;
                                    }
                                }
                                if (atLeastOnePrinted) {
                                    writer.write(System.getProperty("line.separator"));
                                    writer.flush();
                                }
                            }
                        }
                    } else {
                        if (appArgs.isEmpty()) {
                            currDir = new File(currentDirectory);
                        } else if (appArgs.size() == 1) {
                            currDir = new File(appArgs.get(0));
                        } else {
                            throw new RuntimeException("ls: too many arguments");
                        }
                        try {
                            File[] listOfFiles = currDir.listFiles();
                            boolean atLeastOnePrinted = false;
                            for (File file : listOfFiles) {
                                if (!file.getName().startsWith(".")) {
                                    writer.write(file.getName());
                                    writer.write("\t");
                                    writer.flush();
                                    atLeastOnePrinted = true;
                                }
                            }
                            if (atLeastOnePrinted) {
                                writer.write(System.getProperty("line.separator"));
                                writer.flush();
                            }
                        } catch (NullPointerException e) {
                            throw new RuntimeException("ls: no such directory");
                        }
                    }
                    break;
                case "cat":
                    if (unsafeMode) {
                        if (appArgs.isEmpty()) {
                            writer.write("_cat: missing arguments");
                            writer.write(System.getProperty("line.separator"));
                            writer.flush();
                        } else {
                            for (String arg : appArgs) {
                                Charset encoding = StandardCharsets.UTF_8;
                                File currFile = new File(currentDirectory + File.separator + arg);
                                if (!currFile.exists()) {
                                    writer.write("_cat: file does not exist");
                                    writer.write(System.getProperty("line.separator"));
                                    writer.flush();
                                } else {
                                    Path filePath = Paths.get(currentDirectory + File.separator + arg);
                                    try (BufferedReader reader = Files.newBufferedReader(filePath, encoding)) {
                                        String line = null;
                                        while ((line = reader.readLine()) != null) {
                                            writer.write(String.valueOf(line));
                                            writer.write(System.getProperty("line.separator"));
                                            writer.flush();
                                        }
                                    } catch (IOException e) {
                                        writer.write("_cat: cannot open " + arg);
                                        writer.write(System.getProperty("line.separator"));
                                        writer.flush();
                                    }
                                }
                            }
                        }
                    } else {
                        if (appArgs.isEmpty()) {
                            throw new RuntimeException("cat: missing arguments");
                        } else {
                            for (String arg : appArgs) {
                                Charset encoding = StandardCharsets.UTF_8;
                                File currFile = new File(currentDirectory + File.separator + arg);
                                if (currFile.exists()) {
                                    Path filePath = Paths.get(currentDirectory + File.separator + arg);
                                    try (BufferedReader reader = Files.newBufferedReader(filePath, encoding)) {
                                        String line = null;
                                        while ((line = reader.readLine()) != null) {
                                            writer.write(String.valueOf(line));
                                            writer.write(System.getProperty("line.separator"));
                                            writer.flush();
                                        }
                                    } catch (IOException e) {
                                        throw new RuntimeException("cat: cannot open " + arg);
                                    }
                                } else {
                                    throw new RuntimeException("cat: file does not exist");
                                }
                            }
                        }
                    }
                    break;
                case "echo":
                    boolean atLeastOnePrinted = false;
                    for (String arg : appArgs) {
                        writer.write(arg);
                        writer.write(" ");
                        writer.flush();
                        atLeastOnePrinted = true;
                    }
                    if (atLeastOnePrinted) {
                        writer.write(System.getProperty("line.separator"));
                        writer.flush();
                    }
                    break;
                case "head":
                    if (unsafeMode) {
                        if (appArgs.isEmpty()) {
                            writer.write("_head: missing arguments");
                            writer.write(System.getProperty("line.separator"));
                            writer.flush();
                        } else if (appArgs.size() != 1 && appArgs.size() != 3) {
                            writer.write("_head: wrong arguments");
                            writer.write(System.getProperty("line.separator"));
                            writer.flush();
                        } else if (appArgs.size() == 3 && !appArgs.get(0).equals("-n")) {
                            writer.write("_head: wrong argument " + appArgs.get(0));
                            writer.write(System.getProperty("line.separator"));
                            writer.flush();
                        } else {
                            int headLines = 10;
                            String headArg;
                            Boolean successfulParseInt = true;
                            if (appArgs.size() == 3) {
                                try {
                                    headLines = Integer.parseInt(appArgs.get(1));
                                } catch (Exception e) {
                                    writer.write("_head: wrong argument " + appArgs.get(1));
                                    writer.write(System.getProperty("line.separator"));
                                    writer.flush();
                                    successfulParseInt = false;
                                }
                            }
                            if (successfulParseInt) {
                                if (appArgs.size() == 3) {
                                    headArg = appArgs.get(2);
                                } else {
                                    headArg = appArgs.get(0);
                                }
                                File headFile = new File(currentDirectory + File.separator + headArg);
                                if (headFile.exists()) {
                                    Charset encoding = StandardCharsets.UTF_8;
                                    Path filePath = Paths.get((String) currentDirectory + File.separator + headArg);
                                    try (BufferedReader reader = Files.newBufferedReader(filePath, encoding)) {
                                        for (int i = 0; i < headLines; i++) {
                                            String line = null;
                                            if ((line = reader.readLine()) != null) {
                                                writer.write(line);
                                                writer.write(System.getProperty("line.separator"));
                                                writer.flush();
                                            }
                                        }
                                    } catch (IOException e) {
                                        writer.write("_head: cannot open " + headArg);
                                        writer.write(System.getProperty("line.separator"));
                                        writer.flush();
                                    }
                                } else {
                                    writer.write("_head: " + headArg + " does not exist");
                                    writer.write(System.getProperty("line.separator"));
                                    writer.flush();
                                }
                            }
                        }
                    } else {
                        if (appArgs.isEmpty()) {
                            throw new RuntimeException("head: missing arguments");
                        }
                        if (appArgs.size() != 1 && appArgs.size() != 3) {
                            throw new RuntimeException("head: wrong arguments");
                        }
                        if (appArgs.size() == 3 && !appArgs.get(0).equals("-n")) {
                            throw new RuntimeException("head: wrong argument " + appArgs.get(0));
                        }
                        int headLines = 10;
                        String headArg;
                        if (appArgs.size() == 3) {
                            try {
                                headLines = Integer.parseInt(appArgs.get(1));
                            } catch (Exception e) {
                                throw new RuntimeException("head: wrong argument " + appArgs.get(1));
                            }
                            headArg = appArgs.get(2);
                        } else {
                            headArg = appArgs.get(0);
                        }
                        File headFile = new File(currentDirectory + File.separator + headArg);
                        if (headFile.exists()) {
                            Charset encoding = StandardCharsets.UTF_8;
                            Path filePath = Paths.get((String) currentDirectory + File.separator + headArg);
                            try (BufferedReader reader = Files.newBufferedReader(filePath, encoding)) {
                                for (int i = 0; i < headLines; i++) {
                                    String line = null;
                                    if ((line = reader.readLine()) != null) {
                                        writer.write(line);
                                        writer.write(System.getProperty("line.separator"));
                                        writer.flush();
                                    }
                                }
                            } catch (IOException e) {
                                throw new RuntimeException("head: cannot open " + headArg);
                            }
                        } else {
                            throw new RuntimeException("head: " + headArg + " does not exist");
                        }
                    }
                    break;
                case "tail":
                    if (unsafeMode) {
                        if (appArgs.isEmpty()) {
                            writer.write("_tail: missing arguments");
                            writer.write(System.getProperty("line.separator"));
                            writer.flush();
                        } else if (appArgs.size() != 1 && appArgs.size() != 3) {
                            writer.write("_tail: wrong arguments");
                            writer.write(System.getProperty("line.separator"));
                            writer.flush();
                        } else if (appArgs.size() == 3 && !appArgs.get(0).equals("-n")) {
                            writer.write("_tail: wrong argument " + appArgs.get(0));
                            writer.write(System.getProperty("line.separator"));
                            writer.flush();
                        } else {
                            int tailLines = 10;
                            String tailArg;
                            Boolean successfulParseInt = true;
                            if (appArgs.size() == 3) {
                                try {
                                    tailLines = Integer.parseInt(appArgs.get(1));
                                } catch (Exception e) {
                                    writer.write("_tail: wrong argument " + appArgs.get(1));
                                    writer.write(System.getProperty("line.separator"));
                                    writer.flush();
                                    successfulParseInt = false;
                                }
                            }
                            if (successfulParseInt) {
                                if (appArgs.size() == 3) {
                                    tailArg = appArgs.get(2);
                                } else {
                                    tailArg = appArgs.get(0);
                                }
                                File tailFile = new File(currentDirectory + File.separator + tailArg);
                                if (tailFile.exists()) {
                                    Charset encoding = StandardCharsets.UTF_8;
                                    Path filePath = Paths.get((String) currentDirectory + File.separator + tailArg);
                                    ArrayList<String> storage = new ArrayList<>();
                                    try (BufferedReader reader = Files.newBufferedReader(filePath, encoding)) {
                                        String line = null;
                                        while ((line = reader.readLine()) != null) {
                                            storage.add(line);
                                        }
                                        int index = 0;
                                        if (tailLines > storage.size()) {
                                            index = 0;
                                        } else {
                                            index = storage.size() - tailLines;
                                        }
                                        for (int i = index; i < storage.size(); i++) {
                                            writer.write(storage.get(i) + System.getProperty("line.separator"));
                                            writer.flush();
                                        }
                                    } catch (IOException e) {
                                        writer.write("_tail: cannot open " + tailArg);
                                        writer.write(System.getProperty("line.separator"));
                                        writer.flush();
                                    }
                                } else {
                                    writer.write("_tail: " + tailArg + " does not exist");
                                    writer.write(System.getProperty("line.separator"));
                                    writer.flush();
                                }
                            }
                        }
                    } else {
                        if (appArgs.isEmpty()) {
                            throw new RuntimeException("tail: missing arguments");
                        }
                        if (appArgs.size() != 1 && appArgs.size() != 3) {
                            throw new RuntimeException("tail: wrong arguments");
                        }
                        if (appArgs.size() == 3 && !appArgs.get(0).equals("-n")) {
                            throw new RuntimeException("tail: wrong argument " + appArgs.get(0));
                        }
                        int tailLines = 10;
                        String tailArg;
                        if (appArgs.size() == 3) {
                            try {
                                tailLines = Integer.parseInt(appArgs.get(1));
                            } catch (Exception e) {
                                throw new RuntimeException("tail: wrong argument " + appArgs.get(1));
                            }
                            tailArg = appArgs.get(2);
                        } else {
                            tailArg = appArgs.get(0);
                        }
                        File tailFile = new File(currentDirectory + File.separator + tailArg);
                        if (tailFile.exists()) {
                            Charset encoding = StandardCharsets.UTF_8;
                            Path filePath = Paths.get((String) currentDirectory + File.separator + tailArg);
                            ArrayList<String> storage = new ArrayList<>();
                            try (BufferedReader reader = Files.newBufferedReader(filePath, encoding)) {
                                String line = null;
                                while ((line = reader.readLine()) != null) {
                                    storage.add(line);
                                }
                                int index = 0;
                                if (tailLines > storage.size()) {
                                    index = 0;
                                } else {
                                    index = storage.size() - tailLines;
                                }
                                for (int i = index; i < storage.size(); i++) {
                                    writer.write(storage.get(i) + System.getProperty("line.separator"));
                                    writer.flush();
                                }
                            } catch (IOException e) {
                                throw new RuntimeException("tail: cannot open " + tailArg);
                            }
                        } else {
                            throw new RuntimeException("tail: " + tailArg + " does not exist");
                        }
                    }
                    break;
                case "grep":
                    if (unsafeMode) {
                        if (appArgs.size() < 2) {
                            writer.write("_grep: wrong number of arguments");
                            writer.write(System.getProperty("line.separator"));
                            writer.flush();
                        } else {
                            Pattern grepPattern = Pattern.compile(appArgs.get(0));
                            int numOfFiles = appArgs.size() - 1;
                            Path filePath;
                            Path[] filePathArray = new Path[numOfFiles];
                            Path currentDir = Paths.get(currentDirectory);
                            Boolean existingFile = true;
                            for (int i = 0; i < numOfFiles; i++) {
                                filePath = currentDir.resolve(appArgs.get(i + 1));
                                if (Files.notExists(filePath) || Files.isDirectory(filePath) || !Files.exists(filePath)
                                        || !Files.isReadable(filePath)) {
                                    existingFile = false;
                                    writer.write("_grep: wrong file argument");
                                    writer.write(System.getProperty("line.separator"));
                                    writer.flush();
                                    break;
                                }
                                filePathArray[i] = filePath;
                            }
                            if (existingFile) {
                                for (int j = 0; j < filePathArray.length; j++) {
                                    Charset encoding = StandardCharsets.UTF_8;
                                    try (BufferedReader reader = Files.newBufferedReader(filePathArray[j], encoding)) {
                                        String line = null;
                                        while ((line = reader.readLine()) != null) {
                                            Matcher matcher = grepPattern.matcher(line);
                                            if (matcher.find()) {
                                                if (numOfFiles > 1) {
                                                    writer.write(appArgs.get(j + 1));
                                                    writer.write(":");
                                                }
                                                writer.write(line);
                                                writer.write(System.getProperty("line.separator"));
                                                writer.flush();
                                            }
                                        }
                                    } catch (IOException e) {
                                        writer.write("grep: cannot open " + appArgs.get(j + 1));
                                        writer.write(System.getProperty("line.separator"));
                                        writer.flush();
                                        break;
                                    }
                                }
                            }
                        }
                    } else {
                        if (appArgs.size() < 2) {
                            throw new RuntimeException("grep: wrong number of arguments");
                        }
                        Pattern grepPattern = Pattern.compile(appArgs.get(0));
                        int numOfFiles = appArgs.size() - 1;
                        Path filePath;
                        Path[] filePathArray = new Path[numOfFiles];
                        Path currentDir = Paths.get(currentDirectory);
                        for (int i = 0; i < numOfFiles; i++) {
                            filePath = currentDir.resolve(appArgs.get(i + 1));
                            if (Files.notExists(filePath) || Files.isDirectory(filePath) || !Files.exists(filePath)
                                    || !Files.isReadable(filePath)) {
                                throw new RuntimeException("grep: wrong file argument");
                            }
                            filePathArray[i] = filePath;
                        }
                        for (int j = 0; j < filePathArray.length; j++) {
                            Charset encoding = StandardCharsets.UTF_8;
                            try (BufferedReader reader = Files.newBufferedReader(filePathArray[j], encoding)) {
                                String line = null;
                                while ((line = reader.readLine()) != null) {
                                    Matcher matcher = grepPattern.matcher(line);
                                    if (matcher.find()) {
                                        if (numOfFiles > 1) {
                                            writer.write(appArgs.get(j + 1));
                                            writer.write(":");
                                        }
                                        writer.write(line);
                                        writer.write(System.getProperty("line.separator"));
                                        writer.flush();
                                    }
                                }
                            } catch (IOException e) {
                                throw new RuntimeException("grep: cannot open " + appArgs.get(j + 1));
                            }
                        }
                    }
                    break;
                case "find":
                    File cur = new File(currentDirectory);
                    if (unsafeMode) {
                        if (appArgs.isEmpty()) {
                            writer.write("_find: missing arguments");
                            writer.write(System.getProperty("line.separator"));
                            writer.flush();
                        } else {
                            try {
                                File[] listOfFiles = cur.listFiles();
                                Set<String> result_set = new HashSet<String>();
                                for (File file : listOfFiles) {
                                    if (!file.getName().startsWith(".")) {
                                        if (appArgs.size() == 1 && appArgs.get(0).equals(file.getName())) {
                                            result_set.add(file.getName());
                                        } else if (appArgs.size() > 1) {
                                            for (String ar : appArgs) {
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
                                writer.write("_find: no such directory");
                                writer.write(System.getProperty("line.separator"));
                                writer.flush();
                            }
                        }
                    } else {
                        if (appArgs.isEmpty()) {
                            throw new RuntimeException("find: missing arguemnts");
                        }
                        try {
                            File[] listOfFiles = cur.listFiles();
                            Set<String> result_set = new HashSet<String>();
                            for (File file : listOfFiles) {
                                if (!file.getName().startsWith(".")) {
                                    if (appArgs.size() == 1 && appArgs.get(0).equals(file.getName())) {
                                        result_set.add(file.getName());
                                    } else if (appArgs.size() > 1) {
                                        for (String ar : appArgs) {
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
                    }
                    break;
                case "uniq":
                    writer.write(appArgs.toString());
                    writer.write(System.getProperty("line.separator"));
                    writer.flush();
                    break;
                case "cut":
                    if (unsafeMode) {
                        if (appArgs.isEmpty()) {
                            throw new RuntimeException("cut: missing arguments");
                        } else if (appArgs.size() != 1 && appArgs.size() != 3) {
                            throw new RuntimeException("cut: wrong arguments");
                        } else if (appArgs.size() == 3 && !appArgs.get(0).equals("-b")) {
                            throw new RuntimeException("cut: wrong argument " + appArgs.get(0));
                        } else {
                            String start_end = appArgs.get(1);
                            start_end = start_end.replaceAll("[^-?0-9]+", " ");
                            List<String> line_args = Arrays.asList(start_end.trim().split(" "));

                            // Concatenate all args into a single string
                            String concat_args = String.join(", ", line_args);
                            List<Integer> clean_args = parse_cut_input(concat_args);
                            
                            String file_name = appArgs.get(2);

                            Charset encoding = StandardCharsets.UTF_8;
                            File curr_File = new File(currentDirectory + File.separator + file_name);
                            if (curr_File.exists()) {
                                Path file_Path = Paths.get(currentDirectory + File.separator + file_name);
                                try (BufferedReader reader = Files.newBufferedReader(file_Path, encoding)) {
                                    String line = null;
    
                                    ArrayList<Character> seperated_bytes = new ArrayList<Character>();
                                    Boolean correctByteIndex = true;
                                    while ((line = reader.readLine()) != null) {
                                        String line_from_textfile = String.valueOf(line);
                                        seperated_bytes.clear();
                                        for (int i : clean_args) {
                                            if (i < 0) {
                                                correctByteIndex = false;
                                                writer.write("_cut: byte index specified does not exist");
                                                writer.write(System.getProperty("line.separator"));
                                                writer.flush();
                                                break;
                                            } else if (i < line_from_textfile.length()) {
                                                seperated_bytes.add(line_from_textfile.charAt(i));
                                            }
                                        }
                                        if (correctByteIndex) {
                                            String resulting_line = seperated_bytes.stream().map(String::valueOf)
                                                .collect(Collectors.joining());
    
                                            writer.write(resulting_line);
                                            writer.write(System.getProperty("line.separator"));
                                            writer.flush();
                                        } else {
                                            break;
                                        }
                                    }
                                } catch (IOException e) {
                                    writer.write("_cut: cannot open " + file_name);
                                    writer.write(System.getProperty("line.separator"));
                                    writer.flush();
                                }
                            } else {
                                writer.write("_cut: file does not exist");
                                writer.write(System.getProperty("line.separator"));
                                writer.flush();
                            }
                        }
                    } else {
                        if (appArgs.isEmpty()) {
                            throw new RuntimeException("cut: missing arguments");
                        } else if (appArgs.size() != 1 && appArgs.size() != 3) {
                            throw new RuntimeException("cut: wrong arguments");
                        } else if (appArgs.size() == 3 && !appArgs.get(0).equals("-b")) {
                            throw new RuntimeException("cut: wrong argument " + appArgs.get(0));
                        } else {
                            String start_end = appArgs.get(1);
                            start_end = start_end.replaceAll("[^-?0-9]+", " ");
                            List<String> line_args = Arrays.asList(start_end.trim().split(" "));
    
                            // Concatenate all args into a single string
                            String concat_args = String.join(", ", line_args);
                            List<Integer> clean_args = parse_cut_input(concat_args);
                            
                            String file_name = appArgs.get(2);
    
                            Charset encoding = StandardCharsets.UTF_8;
                            File curr_File = new File(currentDirectory + File.separator + file_name);
                            if (curr_File.exists()) {
                                Path file_Path = Paths.get(currentDirectory + File.separator + file_name);
                                try (BufferedReader reader = Files.newBufferedReader(file_Path, encoding)) {
                                    String line = null;
    
                                    ArrayList<Character> seperated_bytes = new ArrayList<Character>();
                                    while ((line = reader.readLine()) != null) {
                                        String line_from_textfile = String.valueOf(line);
                                        seperated_bytes.clear();
                                        for (int i : clean_args) {
                                            if (i < 0) {
                                                throw new RuntimeException("cut: byte index specified does not exist");
                                            } else if (i < line_from_textfile.length()) {
                                                seperated_bytes.add(line_from_textfile.charAt(i));
                                            }
                                        }
                                        String resulting_line = seperated_bytes.stream().map(String::valueOf)
                                                .collect(Collectors.joining());
    
                                        writer.write(resulting_line);
                                        writer.write(System.getProperty("line.separator"));
                                        writer.flush();
                                    }
                                } catch (IOException e) {
                                    throw new RuntimeException("cut: cannot open " + file_name);
                                }
                            } else {
                                throw new RuntimeException("cut: file does not exist");
                            }
                        }
                    }
                    break;
                case "sort":
                    String sortArg;
                    if (unsafeMode) {
                        if (appArgs.isEmpty()){
                            writer.write("_sort: missing arguments");
                            writer.write(System.getProperty("line.separator"));
                            writer.flush();
                        } else if (appArgs.size() != 1 && appArgs.size() != 2){
                            writer.write("sort: wrong number of arguments");
                            writer.write(System.getProperty("line.separator"));
                            writer.flush();
                        } else if (appArgs.size() == 2 && !appArgs.get(0).equals("-r")){
                            writer.write("sort: wrong argument " + appArgs.get(0));
                            writer.write(System.getProperty("line.separator"));
                            writer.flush();
                        } else {
                            if (appArgs.size() == 2){
                                sortArg = appArgs.get(1);
                            } else {
                                sortArg = appArgs.get(0);
                            }
                            File sortFile = new File(currentDirectory + File.separator + sortArg);
                            Charset encoding = StandardCharsets.UTF_8;
                            if(sortFile.exists()){
                                Path sortPath = Paths.get((String) currentDirectory + File.separator + sortArg);
                                try(BufferedReader reader = Files.newBufferedReader(sortPath, encoding)){
                                    String line = reader.readLine();
                                    ArrayList<String> lines = new ArrayList<String>();
                                    ArrayList<String> sortedLines;
        
                                    //Populate array with lines of the file
                                    while(line != null){
                                        lines.add(line);
                                        line = reader.readLine();
                                    }
        
                                    //Use the quicksort on the array list of strings
                                    sortedLines = stringQuicksort(lines);
        
                                    //Check if the -r is present then display array in reverse order
                                    if (appArgs.size() == 2) {
                                        for(int i = sortedLines.size() - 1; i >= 0; i--) {
                                            writer.write(sortedLines.get(i));
                                            writer.write(System.getProperty("line.separator"));
                                            writer.flush();
                                        }
                                    } else {
                                        //If -r is not present display array normally
                                        for(int i = 0; i < sortedLines.size(); i++) {
                                            writer.write(sortedLines.get(i));
                                            writer.write(System.getProperty("line.separator"));
                                            writer.flush();
                                        }
                                    }
                                } catch (IOException e) {
                                    writer.write("sort: cannot open " + sortArg);
                                    writer.write(System.getProperty("line.separator"));
                                    writer.flush();
                                }
                            } else {
                                writer.write("sort: " + sortArg + " does not exist");
                                writer.write(System.getProperty("line.separator"));
                                writer.flush();
                            }
                        }
                    } else {
                        if (appArgs.isEmpty()) {
                            throw new RuntimeException("sort: missing arguments");
                        }
                        if (appArgs.size() != 1 && appArgs.size() != 2) {
                            throw new RuntimeException("sort: wrong number of arguments");
                        }
                        if (appArgs.size() == 2 && !appArgs.get(0).equals("-r")) {
                            throw new RuntimeException("sort: wrong argument " + appArgs.get(0));
                        }
                        if (appArgs.size() == 2) {
                            sortArg = appArgs.get(1);
                        } else {
                            sortArg = appArgs.get(0);
                        }
                        File sortFile = new File(currentDirectory + File.separator + sortArg);
                        Charset encoding = StandardCharsets.UTF_8;
                        if(sortFile.exists()) {
                            Path sortPath = Paths.get((String) currentDirectory + File.separator + sortArg);
                            try(BufferedReader reader = Files.newBufferedReader(sortPath, encoding)) {
                                String line = reader.readLine();
                                ArrayList<String> lines = new ArrayList<String>();
                                ArrayList<String> sortedLines;
    
                                //Populate array with lines of the file
                                while(line != null) {
                                    lines.add(line);
                                    line = reader.readLine();
                                }
    
                                //Use the quicksort on the array list of strings
                                sortedLines = stringQuicksort(lines);
    
                                //Check if the -r is present then display array in reverse order
                                if (appArgs.size() == 2) {
                                    for(int i = sortedLines.size() - 1; i >= 0; i--) {
                                        writer.write(sortedLines.get(i));
                                        writer.write(System.getProperty("line.separator"));
                                        writer.flush();
                                    }
                                } else {
                                    //If -r is not present display array normally
                                    for(int i = 0; i < sortedLines.size(); i++) {
                                        writer.write(sortedLines.get(i));
                                        writer.write(System.getProperty("line.separator"));
                                        writer.flush();
                                    }
                                }
                            } catch (IOException e) {
                                throw new RuntimeException("sort: cannot open " + sortArg);
                            }
                        } else {
                            throw new RuntimeException("sort: " + sortArg + " does not exist");
                        }
                    }
                    break;
                default:
                    throw new RuntimeException(appName + ": unknown application");
            }
        }
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            if (args.length != 2) {
                System.out.println("jsh: wrong number of arguments");
                return;
            }
            if (!args[0].equals("-c")) {
                System.out.println("jsh: " + args[0] + ": unexpected argument");
            }
            try {
                eval(args[1], System.out);
            } catch (Exception e) {
                System.out.println("jsh: " + e.getMessage());
            }
        } else {
            Scanner input = new Scanner(System.in);
            try {
                while (true) {
                    String prompt = currentDirectory + "> ";
                    System.out.print(prompt);
                    try {
                        String cmdline = input.nextLine();

                        // Check for empty string input
                        if (!(cmdline.equals("\n") || cmdline.equals("") || cmdline.equals(" "))) {
                            eval(cmdline, System.out);
                        }
                    } catch (Exception e) {
                        System.out.println("jsh: " + e.getMessage());
                    }
                }
            } finally {
                input.close();
            }
        }
    }
}
