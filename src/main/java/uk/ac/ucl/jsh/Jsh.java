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

import uk.ac.ucl.jsh.Applications.Application;
import uk.ac.ucl.jsh.Parser.antlr2.JshGrammarLexer;
import uk.ac.ucl.jsh.Parser.antlr2.JshGrammarParser;
import uk.ac.ucl.jsh.Visitor.AppVisitor;
import uk.ac.ucl.jsh.Visitor.Visitable;
import uk.ac.ucl.jsh.Parser.Parser;

public class Jsh {

    private static String currentDirectory = System.getProperty("user.dir");
    public static String lineSeparator = System.getProperty("line.separator");

    public final static String lineSeparator = System.getProperty("line.separator");

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

    public static void eval(String cmdline, OutputStream output) {
        Visitable parseTree = Parser.parseCMD(cmdline);

        try {
            parseTree.accept(new AppVisitor(),null, output, currentDirectory);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

            try {
                currentDirectory = app.mainExec(appArgs, currentDirectory, null, output);
            } catch (IOException e) {
                throw new RuntimeException(appName + ": unknown application");
            }

//            try {
//                currentDirectory = app.mainExec(appArgs, currentDirectory, is, output);
//            } catch (IOException e) {
//                throw new RuntimeException(appName + ": unknown application");
//            }
//        }
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
