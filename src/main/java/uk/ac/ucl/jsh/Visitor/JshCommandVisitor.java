package uk.ac.ucl.jsh.Visitor;
//import JshGrammarParser;
import uk.ac.ucl.jsh.Parser.antlr2.*;

import java.util.ArrayList;

public class JshCommandVisitor extends JshGrammarBaseVisitor<Visitable> {

    @Override
    public Visitable visitStart(JshGrammarParser.StartContext ctx) {
        if (ctx.seq() != null) {
            return visit(ctx.seq());
        } else {
            return visit(ctx.command());
        }
        //return super.visitStart(ctx);
    }

    @Override
    public Visitable visitCommand(JshGrammarParser.CommandContext ctx) {
        if (ctx.pipe() != null) {
            return visit(ctx.pipe());
        } else {
            return visit(ctx.call());
        }
        //return super.visitCommand(ctx);
    }

    @Override
    public Visitable visitPipeBaseCase(JshGrammarParser.PipeBaseCaseContext ctx) {
        return new Pipe(visit(ctx.call1), visit(ctx.call2));
        //return super.visitPipeBaseCase(ctx);
    }

    @Override
    public Visitable visitPipeRecursiveCase(JshGrammarParser.PipeRecursiveCaseContext ctx) {
        return new Pipe(visit(ctx.pipe()), visit(ctx.call()));
        //return super.visitPipeRecursiveCase(ctx);
    }

    @Override
    public Visitable visitSeqBaseCase(JshGrammarParser.SeqBaseCaseContext ctx) {
        return new Seq(visit(ctx.command1), visit(ctx.command2));
        //return super.visitSeqBaseCase(ctx);
    }

    @Override
    public Visitable visitSeqRecursiveCase(JshGrammarParser.SeqRecursiveCaseContext ctx) {
        return new Seq(visit(ctx.seq()), visit(ctx.command()));
        //return super.visitSeqRecursiveCase(ctx);
    }

    //STUCK ON THIS
    @Override
    public Visitable visitCall(JshGrammarParser.CallContext ctx) {
        if (ctx.redirection() == null) {
            return new Call(ctx.getText());
        }
        return super.visitCall(ctx);
    }

    @Override
    public Visitable visitAtom(JshGrammarParser.AtomContext ctx) {
        if (ctx.redirection() != null) {
            return visit(ctx.redirection());
        } else {
            return visit(ctx.argument());
        }
        //return super.visitAtom(ctx);
    }

    @Override
    public ArrayList<String> visitArgument(JshGrammarParser.ArgumentContext ctx) {
        return super.visitArgument(ctx);
    }

    @Override
    public Visitable visitRedirection(JshGrammarParser.RedirectionContext ctx) {
        return super.visitRedirection(ctx);
    }

    @Override
    public Visitable visitQuoted(JshGrammarParser.QuotedContext ctx) {
        return super.visitQuoted(ctx);
    }

    @Override
    public Visitable visitSingleQuoted(JshGrammarParser.SingleQuotedContext ctx) {
        return super.visitSingleQuoted(ctx);
    }

    @Override
    public Visitable visitBackQuoted(JshGrammarParser.BackQuotedContext ctx) {
        return super.visitBackQuoted(ctx);
    }

    @Override
    public Visitable visitDoubleQuoted(JshGrammarParser.DoubleQuotedContext ctx) {
        return super.visitDoubleQuoted(ctx);
    }
}
