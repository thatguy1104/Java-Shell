package uk.ac.ucl.jsh;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
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

    // cat text1.txt | cut -b 1,2 text2.txt
    public static ArrayList<String> supplementary(String cmdline, OutputStream output) throws IOException{
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
            } else if (!tree.getChild(i).getText().equals("|")) {
                lastSubcommand += tree.getChild(i).getText();
            } else {
                rawCommands.add(lastSubcommand);
                lastSubcommand = "";
            }
        }
        rawCommands.add(lastSubcommand);
        return rawCommands;
    }

    public static void eval(String cmdline, OutputStream output) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(output);
        ArrayList<String> rawCommands = supplementary(cmdline, output);
        
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
            

            Factory factory = new Factory();
            Application app = factory.getApp(appName);


            switch (appName) {
                case "cd":
                    currentDirectory = app.exec(appArgs, currentDirectory, output);
                    break;
                case "pwd":
                    currentDirectory = app.exec(appArgs, currentDirectory, output);
                    break;
                case "ls":
                    currentDirectory = app.exec(appArgs, currentDirectory, output);
                    break;
                case "cat":
                    currentDirectory = app.exec(appArgs, currentDirectory, output);
                    break;
                case "echo":
                    currentDirectory = app.exec(appArgs, currentDirectory, output);
                    break;
                case "head":
                    currentDirectory = app.exec(appArgs, currentDirectory, output);
                    break;
                case "tail":
                    currentDirectory = app.exec(appArgs, currentDirectory, output);
                    break;
                case "grep":
                    currentDirectory = app.exec(appArgs, currentDirectory, output);
                    break;
                case "find":
                    currentDirectory = app.exec(appArgs, currentDirectory, output);
                    break;
                case "uniq":
                    String uniqFilename;

                    if (appArgs.isEmpty()) {
                        throw new RuntimeException("uniq: missing argument");
                    } else if (appArgs.size() > 2) {
                        throw new RuntimeException("uniq: too many arguments");
                    }
                    if (appArgs.size() == 2 && !appArgs.get(0).equals("-i")){
                        throw new RuntimeException("uniq: wrong argument" + appArgs.get(0));
                    }
                    if (appArgs.size() == 2){
                        uniqFilename = appArgs.get(1);
                    }
                    else {
                        uniqFilename = appArgs.get(0);
                    }

                    // String input = appArgs(*)
                    File uniqFile = new File(currentDirectory + File.separator + uniqFilename);
                    if(uniqFile.exists()) 
                    {
                        //Charset encoding = StandardCharsets.UTF_8;
                        Path sortPath = Paths.get((String) currentDirectory + File.separator + uniqFilename);
                        try(BufferedReader reader = Files.newBufferedReader(sortPath /*, encoding*/))
                        {
                            String line = reader.readLine();
                            ArrayList<String> lines = new ArrayList<String>();
                            ArrayList<String> uniqLines;

                            //Populate array with lines of the file
                            while(line != null)
                            {
                                lines.add(line);
                                line = reader.readLine();
                            }

                            //Check if the -i and if exists make comparision case insensitive 
                            if (appArgs.size() == 2) {
                                uniqLines = new ArrayList<String>();
                                
                                for(int i = 0; i < lines.size(); i++) {
                                    boolean equals = false;
                                    String row = lines.get(i);
                                    for(int j = i+1; j < lines.size(); j++){
                                        String row2 = lines.get(j);
                                        if (row.equalsIgnoreCase(row2)){
                                            equals = true;
                                        }
                                    }
                                    if (!equals && !uniqLines.contains(row)) {
                                        uniqLines.add(row);
                                    }
                                }
                            }
                            else { // case sensitive
                            LinkedHashSet<String> uniqSet = new LinkedHashSet<>(lines);
                            uniqLines = new ArrayList<>(uniqSet);
                            }
                            

                            // display array of uniq lines
                            for(int i = 0; i < uniqLines.size(); i++) 
                            {
                                writer.write(uniqLines.get(i));
                                writer.write(System.getProperty("line.separator"));
                                writer.flush();
                            }

                        } catch (IOException e) 
                            {
                                throw new RuntimeException("uniq: cannot open " + uniqFilename);
                            } 
                    } else 
                        {
                            throw new RuntimeException("uniq: " + uniqFilename + " does not exist");
                        }
                    break;
                case "cut":
                    currentDirectory = app.exec(appArgs, currentDirectory, output);
                    break;
                case "sort":
                    String sortArg;
                    if (unsafeMode) {
                        if (appArgs.isEmpty()){
                            writer.write("_sort: missing arguments");
                            writer.write(System.getProperty("line.separator"));
                            writer.flush();
                        } else if (appArgs.size() != 1 && appArgs.size() != 2){
                            writer.write("_sort: wrong number of arguments");
                            writer.write(System.getProperty("line.separator"));
                            writer.flush();
                        } else if (appArgs.size() == 2 && !appArgs.get(0).equals("-r")){
                            writer.write("_sort: wrong argument " + appArgs.get(0));
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
                                    writer.write("_sort: cannot open " + sortArg);
                                    writer.write(System.getProperty("line.separator"));
                                    writer.flush();
                                }
                            } else {
                                writer.write("_sort: " + sortArg + " does not exist");
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
