package uk.ac.ucl.jsh.Visitor;
//import JshGrammarParser;
import uk.ac.ucl.jsh.Parser.antlr2.*;

public class JshCommandVisitor extends JshGrammarBaseVisitor<Visitable> {
    @Override
    public Visitable visitStart(JshGrammarParser.StartContext ctx) {
        if (!(ctx.command() == null)) {
            return visit(ctx.command());
        } else {
            return visit(ctx.seq());
        }
        //return super.visitStart(ctx);
    }

    @Override
    public Visitable visitCommand(JshGrammarParser.CommandContext ctx) {
        if (!(ctx.pipe() == null)) {
            return visit(ctx.pipe());
        } else {
            return visit(ctx.call());
        }
        //return super.visitCommand(ctx);
    }

    @Override
    public Visitable visitPipe(JshGrammarParser.PipeContext ctx) {
        if (!(ctx.call(1) == null) && !(ctx.call(2) == null)) {
            return new Pipe(visit(ctx.call(1)), visit(ctx.call(2)));
        }
        if (!(ctx.pipe() == null) && !(ctx.call(2) == null)){
            return new Pipe(visit(ctx.pipe()), visit(ctx.call(2)));
        }
        return null;
        //return super.visitPipe(ctx);
    }

    @Override
    public Visitable visitSeq(JshGrammarParser.SeqContext ctx) {
        if (!(ctx.command(1) == null) && !(ctx.command(3) == null)) {
            return new Seq(visit(ctx.command(1)), visit(ctx.command(2)));
        }
        if (!(ctx.seq() == null) && !(ctx.command(2) == null)) {
            return new Seq(visit(ctx.seq()), visit(ctx.command(2)));
        }
        return null;
        //return super.visitSeq(ctx);
    }

    @Override
    public Visitable visitCall(JshGrammarParser.CallContext ctx) {
        return super.visitCall(ctx);
    }

    @Override
    public Visitable visitAtom(JshGrammarParser.AtomContext ctx) {
        if (!(ctx.redirection() == null)) {
            return visit(ctx.redirection());
        } else {
            return visit(ctx.argument());
        }
        //return super.visitAtom(ctx);
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
