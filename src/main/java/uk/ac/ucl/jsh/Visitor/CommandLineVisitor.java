package uk.ac.ucl.jsh.Visitor;

import uk.ac.ucl.jsh.Parser.CommandLine.CommandLineGrammarBaseVisitor;
import uk.ac.ucl.jsh.Parser.CommandLine.CommandLineGrammarParser;

public class CommandLineVisitor extends CommandLineGrammarBaseVisitor<Visitable> {

    @Override
    /**
     Visits the start rule from CommandLineGrammar
     @param ctx The current context from ANTLR being parsed
     */
    public Visitable visitStart(CommandLineGrammarParser.StartContext ctx) {
        if (ctx.command() != null) {
            return visit(ctx.command());
        } else {
            return visit(ctx.seq());
        }
    }

    @Override
    /**
     Visits the command rule from CommandLineGrammar
     @param ctx The current context from ANTLR being parsed
     */
    public Visitable visitCommand(CommandLineGrammarParser.CommandContext ctx) {
        if (ctx.pipe() != null) {
            return visit(ctx.pipe());
        } else {
            return visit(ctx.call());
        }
    }

    @Override
    /**
     Visits the base case for the pipe rule from CommandLineGrammar
     @param ctx The current context from ANTLR being parsed
     */
    public Visitable visitPipeBaseCase(CommandLineGrammarParser.PipeBaseCaseContext ctx) {
        return new Pipe(visit(ctx.call1), visit(ctx.call2));
    }

    @Override
    /**
     Visits the recursive case for the pipe rule from CommandLineGrammar
     @param ctx The current context from ANTLR being parsed
     */
    public Visitable visitPipeRecursiveCase(CommandLineGrammarParser.PipeRecursiveCaseContext ctx) {
        return new Pipe(visit(ctx.pipe()), visit(ctx.call()));
    }

    @Override
    /**
     Visits the base case for the sequence rule from CommandLineGrammar
     @param ctx The current context from ANTLR being parsed
     */
    public Visitable visitSeqBaseCase(CommandLineGrammarParser.SeqBaseCaseContext ctx) {
        return new Seq(visit(ctx.command1), visit(ctx.command2));
    }

    @Override
    /**
     Visits the recursive case for the sequence rule from CommandLineGrammar
     @param ctx The current context from ANTLR being parsed
     */
    public Visitable visitSeqRecursiveCase(CommandLineGrammarParser.SeqRecursiveCaseContext ctx) {
        return new Seq(visit(ctx.seq()), visit(ctx.command()));
    }

    @Override
    /**
     Visits the call rule from CommandLineGrammar
     @param ctx The current context from ANTLR being parsed
     */
    public Visitable visitCall(CommandLineGrammarParser.CallContext ctx) {
        return new Call(ctx.getText());
    }
}
