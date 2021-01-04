package uk.ac.ucl.jsh;

import java.io.OutputStream;
import java.util.Scanner;

import uk.ac.ucl.jsh.Visitor.AppVisitor;
import uk.ac.ucl.jsh.Visitor.Visitable;
import uk.ac.ucl.jsh.Parser.Parser;

public class Jsh {

    public static String currentDirectory = System.getProperty("user.dir");
    public static String lineSeparator = System.getProperty("line.separator");

    public static void eval(String cmdline, OutputStream output) {
        Parser parser = new Parser();
        Visitable parseTree = parser.parseCMD(cmdline);

        try {
            parseTree.accept(new AppVisitor(), null, output, currentDirectory);
        } catch (Exception e) {
            System.err.println(e.getMessage());
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
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
