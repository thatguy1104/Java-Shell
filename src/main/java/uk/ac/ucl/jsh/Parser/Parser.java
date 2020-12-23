package uk.ac.ucl.jsh.Parser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import uk.ac.ucl.jsh.Parser.Call.CallGrammarLexer;
import uk.ac.ucl.jsh.Parser.Call.CallGrammarParser;
import uk.ac.ucl.jsh.Parser.antlr2.JshGrammarLexer;
import uk.ac.ucl.jsh.Parser.antlr2.JshGrammarParser;
import uk.ac.ucl.jsh.Visitor.CallVisitor;
import uk.ac.ucl.jsh.Visitor.JshCallVisitor;
import uk.ac.ucl.jsh.Visitor.JshCommandVisitor;
import uk.ac.ucl.jsh.Visitor.Visitable;

import java.util.ArrayList;

public class Parser {

    public static Visitable parseCMD(String cmdline) {
        JshGrammarLexer lexer = new JshGrammarLexer(CharStreams.fromString(cmdline));
        JshGrammarParser parser = new JshGrammarParser(new CommonTokenStream(lexer));
        JshGrammarParser.StartContext compile_unit = parser.start();
        return new JshCommandVisitor().visitStart(compile_unit);
    }

    public static ArrayList<String> parseCallCommand(String callCommand) {
        CallGrammarLexer lexer = new CallGrammarLexer(CharStreams.fromString(callCommand));
        CallGrammarParser parser = new CallGrammarParser(new CommonTokenStream(lexer));
        CallGrammarParser.ArgumentContext compile_unit = parser.argument();

        return new CallVisitor().visitArgument(compile_unit);
    }
}