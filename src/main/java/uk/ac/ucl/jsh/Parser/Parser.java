package uk.ac.ucl.jsh.Parser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import uk.ac.ucl.jsh.Parser.Call.*;
import uk.ac.ucl.jsh.Parser.CommandLine.*;
import uk.ac.ucl.jsh.Visitor.CallVisitor;
import uk.ac.ucl.jsh.Visitor.CommandLineVisitor;
import uk.ac.ucl.jsh.Visitor.Visitable;

import java.util.ArrayList;

public class Parser {

    public static Visitable parseCMD(String cmdline) {
        CommandLineGrammarLexer lexer = new CommandLineGrammarLexer(CharStreams.fromString(cmdline));
        CommandLineGrammarParser parser = new CommandLineGrammarParser(new CommonTokenStream(lexer));
        CommandLineGrammarParser.StartContext compile_unit = parser.start();
        return new CommandLineVisitor().visitStart(compile_unit);
    }

    public static ArrayList<String> parseCallCommand (String callCommand) {
        CallGrammarLexer lexer = new CallGrammarLexer(CharStreams.fromString(callCommand));
        CallGrammarParser parser = new CallGrammarParser(new CommonTokenStream(lexer));
        CallGrammarParser.StartContext compile_unit = parser.start();

        return new CallVisitor().visitStart(compile_unit);
    }
}