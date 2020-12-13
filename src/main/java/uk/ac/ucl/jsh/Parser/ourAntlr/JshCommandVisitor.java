package uk.ac.ucl.jsh.Parser.ourAntlr;
//import JshGrammarParser;
import uk.ac.ucl.jsh.Parser.antlr2.*;
import uk.ac.ucl.jsh.Visitor.Visitable;

public class JshCommandVisitor extends JshGrammarBaseVisitor<Visitable>{
    @Override
    public Visitable visitStart(JshGrammarParser.StartContext ctx) {
        if (!(ctx.seq() == null)) {
            return visit(ctx.seq());
        } else {
            return visit(ctx.command());
        }
        //return super.visitStart(ctx);
    }

    @Override
    public Visitable visitCommand(JshGrammarParser.CommandContext ctx) {
        return super.visitCommand(ctx);
    }

    @Override
    public Visitable visitPipe(JshGrammarParser.PipeContext ctx) {
        return super.visitPipe(ctx);
    }

    @Override
    public Visitable visitSeq(JshGrammarParser.SeqContext ctx) {
        return super.visitSeq(ctx);
    }

    @Override
    public Visitable visitCall(JshGrammarParser.CallContext ctx) {
        return super.visitCall(ctx);
    }

    @Override
    public Visitable visitAtom(JshGrammarParser.AtomContext ctx) {
        return super.visitAtom(ctx);
    }

    @Override
    public Visitable visitArgument(JshGrammarParser.ArgumentContext ctx) {
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
