package uk.ac.ucl.jsh.Visitor;
import uk.ac.ucl.jsh.Parser.antlr2.JshGrammarBaseVisitor;
import uk.ac.ucl.jsh.Parser.antlr2.JshGrammarParser;

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
            return new Call(ctx.getText());
    }
}
