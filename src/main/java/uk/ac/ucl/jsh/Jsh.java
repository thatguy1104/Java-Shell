package uk.ac.ucl.jsh;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import uk.ac.ucl.jsh.Visitor.AppVisitor;
import uk.ac.ucl.jsh.Visitor.Visitable;
import uk.ac.ucl.jsh.Parser.Parser;

public class Jsh {

    private static String currentDirectory = System.getProperty("user.dir");
    public static String lineSeparator = System.getProperty("line.separator");

    private static ArrayList<String> supplementary(String cmdline) {
        CharStream parserInput = CharStreams.fromString(cmdline);
        JshGrammarLexer lexer = new JshGrammarLexer(parserInput);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        JshGrammarParser parser = new JshGrammarParser(tokenStream);
        ParseTree tree = parser.command();
        ArrayList<String> rawCommands = new ArrayList<>();
        StringBuilder lastSubcommand = new StringBuilder();
        for (int i = 0; i < tree.getChildCount(); i++) {
            if (!tree.getChild(i).getText().equals(";")) {
                lastSubcommand.append(tree.getChild(i).getText());
            } else {
                rawCommands.add(lastSubcommand.toString());
                lastSubcommand = new StringBuilder();
            }
        }
        rawCommands.add(lastSubcommand.toString());
        return rawCommands;
    }

    public void eval(String cmdline, OutputStream output) throws IOException {
        Visitable parseTree = Parser.parseCMD(cmdline);

        try {
            parseTree.accept(new AppVisitor<>(), null, output, currentDirectory);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        ArrayList<String> rawCommands = supplementary(cmdline);
//
//        for (String rawCommand : rawCommands) {
//            String spaceRegex = "[^\\s\"']+|\"([^\"]*)\"|'([^']*)'";
//            ArrayList<String> tokens = new ArrayList<String>();
//            Pattern regex = Pattern.compile(spaceRegex);
//            Matcher regexMatcher = regex.matcher(rawCommand);
//            String nonQuote;
//
//            while (regexMatcher.find()) {
//                if (regexMatcher.group(1) != null || regexMatcher.group(2) != null) {
//                    String quoted = regexMatcher.group(0).trim();
//                    tokens.add(quoted.substring(1, quoted.length() - 1));
//                } else {
//                    nonQuote = regexMatcher.group().trim();
//                    ArrayList<String> globbingResult = new ArrayList<>();
//                    Path dir = Paths.get(currentDirectory);
//                    DirectoryStream<Path> stream = Files.newDirectoryStream(dir, nonQuote);
//                    for (Path entry : stream) {
//                        globbingResult.add(entry.getFileName().toString());
//                    }
//                    if (globbingResult.isEmpty()) {
//                        globbingResult.add(nonQuote);
//                    }
//                    tokens.addAll(globbingResult);
//                }
//            }
//
//            String appName = tokens.get(0);
//            ArrayList<String> appArgs = new ArrayList<>(tokens.subList(1, tokens.size()));
//
//            Factory factory = new Factory();
//            Application app = factory.getApp(appName);
//
//            try {
//                currentDirectory = app.mainExec(appArgs, currentDirectory, null, output);
//            } catch (IOException e) {
//                throw new RuntimeException(appName + ": unknown application");
//            }

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
