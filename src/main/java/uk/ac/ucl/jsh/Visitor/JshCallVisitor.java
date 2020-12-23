package uk.ac.ucl.jsh.Visitor;

import org.antlr.v4.runtime.tree.ParseTree;
import uk.ac.ucl.jsh.Parser.antlr2.JshGrammarCallBaseVisitor;
import uk.ac.ucl.jsh.Parser.antlr2.JshGrammarParser;

import java.util.ArrayList;

public class JshCallVisitor extends JshGrammarCallBaseVisitor<ArrayList<String>> {

    @Override
    public ArrayList<String> visitAtom(JshGrammarParser.AtomContext ctx) {
        if (ctx.redirection() != null) {
            return visit(ctx.redirection());
        } else {
            return visit(ctx.argument());
        }
        //return super.visitAtom(ctx);
    }

    @Override
    public ArrayList<String> visitArgument(JshGrammarParser.ArgumentContext ctx) {
        ArrayList<String> result = new ArrayList<>();

        if (ctx.quoted() != null) {
            for (int i = 0; i < ctx.quoted().size(); i++) {
                result.addAll(visit(ctx.quoted(i)));
            }
        } else {

        }


        return super.visitArgument(ctx);
    }

    @Override
    public ArrayList<String> visitRedirection(JshGrammarParser.RedirectionContext ctx) {
        return super.visitRedirection(ctx);
    }

    @Override
    public ArrayList<String> visitQuoted(JshGrammarParser.QuotedContext ctx) {
        return visitChildren(ctx);

        //return super.visitQuoted(ctx);
    }

    @Override
    public ArrayList<String> visitSingleQuoted(JshGrammarParser.SingleQuotedContext ctx) {
        return super.visitSingleQuoted(ctx);
    }

    @Override
    public ArrayList<String> visitBackQuoted(JshGrammarParser.BackQuotedContext ctx) {
        return super.visitBackQuoted(ctx);
    }

    @Override
    public ArrayList<String> visitDoubleQuoted(JshGrammarParser.DoubleQuotedContext ctx) {
        return super.visitDoubleQuoted(ctx);
    }
}
