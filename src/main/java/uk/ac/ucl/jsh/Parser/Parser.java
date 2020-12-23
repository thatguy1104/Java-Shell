package uk.ac.ucl.jsh.Parser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import uk.ac.ucl.jsh.Parser.antlr2.JshGrammarLexer;
import uk.ac.ucl.jsh.Parser.antlr2.JshGrammarParser;
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
        JshGrammarLexer lexer = new JshGrammarLexer(CharStreams.fromString(callCommand));
        JshGrammarParser parser = new JshGrammarParser(new CommonTokenStream(lexer));
        JshGrammarParser.ArgumentContext compile_unit = parser.argument();

        return new JshCallVisitor().visitArgument(compile_unit);
    }
}