package uk.ac.ucl.jsh;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Jsh {

    private static String currentDirectory = System.getProperty("user.dir");

    private static ArrayList<String> supplementary(String cmdline) {
        CharStream parserInput = CharStreams.fromString(cmdline);
        JshGrammarLexer lexer = new JshGrammarLexer(parserInput);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        JshGrammarParser parser = new JshGrammarParser(tokenStream);
        ParseTree tree = parser.command();
        ArrayList<String> rawCommands = new ArrayList<>();
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
        ArrayList<String> rawCommands = supplementary(cmdline);

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
                    ArrayList<String> globbingResult = new ArrayList<>();
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
            ArrayList<String> appArgs = new ArrayList<>(tokens.subList(1, tokens.size()));

            // Boolean unsafeMode = false;
            // if (appName.charAt(0) == '_') {
            //     appName = appName.substring(1);
            //     unsafeMode = true;
            // }


            Factory factory = new Factory();
            Application app = factory.getApp(appName);

            try {
                currentDirectory = app.exec(appArgs, currentDirectory, output);
            } catch (IOException e) {
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
            try (Scanner input = new Scanner(System.in)) {
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
            }
        }
    }
}
