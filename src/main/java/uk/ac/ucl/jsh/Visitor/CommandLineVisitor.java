package uk.ac.ucl.jsh.Visitor;

import uk.ac.ucl.jsh.Parser.CommandLine.CommandLineGrammarBaseVisitor;
import uk.ac.ucl.jsh.Parser.CommandLine.CommandLineGrammarParser;

public class CommandLineVisitor extends CommandLineGrammarBaseVisitor<Visitable> {

    @Override
    public Visitable visitStart(CommandLineGrammarParser.StartContext ctx) {
        if (ctx.command() != null) {
            return visit(ctx.command());
        } else {
            return visit(ctx.seq());
        }
        //return super.visitStart(ctx);
    }

    @Override
    public Visitable visitCommand(CommandLineGrammarParser.CommandContext ctx) {
        if (ctx.pipe() != null) {
            return visit(ctx.pipe());
        } else {
            return visit(ctx.call());
        }
        //return super.visitCommand(ctx);
    }

    @Override
    public Visitable visitPipeBaseCase(CommandLineGrammarParser.PipeBaseCaseContext ctx) {
        return new Pipe(visit(ctx.call1), visit(ctx.call2));
        //return super.visitPipeBaseCase(ctx);
    }

    @Override
    public Visitable visitPipeRecursiveCase(CommandLineGrammarParser.PipeRecursiveCaseContext ctx) {
        return new Pipe(visit(ctx.pipe()), visit(ctx.call()));
        //return super.visitPipeRecursiveCase(ctx);
    }

    @Override
    public Visitable visitSeqBaseCase(CommandLineGrammarParser.SeqBaseCaseContext ctx) {
        return new Seq(visit(ctx.command1), visit(ctx.command2));
        //return super.visitSeqBaseCase(ctx);
    }

    @Override
    public Visitable visitSeqRecursiveCase(CommandLineGrammarParser.SeqRecursiveCaseContext ctx) {
        return new Seq(visit(ctx.seq()), visit(ctx.command()));
        //return super.visitSeqRecursiveCase(ctx);
    }

    @Override
    public Visitable visitCall(CommandLineGrammarParser.CallContext ctx) {
        return new Call(ctx.getText());
        //return super.visitCall(ctx);
    }

//    @Override
//    public Visitable visitQuoted(CommandLineGrammarParser.QuotedContext ctx) {
//        return super.visitQuoted(ctx);
//    }
//
//    @Override
//    public Visitable visitSingle_quote(CommandLineGrammarParser.Single_quoteContext ctx) {
//        return super.visitSingle_quote(ctx);
//    }
//
//    @Override
//    public Visitable visitDouble_quote(CommandLineGrammarParser.Double_quoteContext ctx) {
//        return super.visitDouble_quote(ctx);
//    }
//
//    @Override
//    public Visitable visitBack_quote(CommandLineGrammarParser.Back_quoteContext ctx) {
//        return super.visitBack_quote(ctx);
//    }
}
