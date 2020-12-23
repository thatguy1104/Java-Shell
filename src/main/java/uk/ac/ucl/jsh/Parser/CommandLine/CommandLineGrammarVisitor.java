// Generated from C:/Users/aashv/Documents/GitHub/jsh-team-41/src/main/antlr4/uk/ac/ucl/jsh/.antlr\CommandLineGrammar.g4 by ANTLR 4.9
package uk.ac.ucl.jsh.Parser.CommandLine;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CommandLineGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CommandLineGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CommandLineGrammarParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(CommandLineGrammarParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link CommandLineGrammarParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommand(CommandLineGrammarParser.CommandContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pipeBaseCase}
	 * labeled alternative in {@link CommandLineGrammarParser#pipe}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPipeBaseCase(CommandLineGrammarParser.PipeBaseCaseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pipeRecursiveCase}
	 * labeled alternative in {@link CommandLineGrammarParser#pipe}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPipeRecursiveCase(CommandLineGrammarParser.PipeRecursiveCaseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code seqBaseCase}
	 * labeled alternative in {@link CommandLineGrammarParser#seq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeqBaseCase(CommandLineGrammarParser.SeqBaseCaseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code seqRecursiveCase}
	 * labeled alternative in {@link CommandLineGrammarParser#seq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeqRecursiveCase(CommandLineGrammarParser.SeqRecursiveCaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CommandLineGrammarParser#call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall(CommandLineGrammarParser.CallContext ctx);
	/**
	 * Visit a parse tree produced by {@link CommandLineGrammarParser#quoted}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuoted(CommandLineGrammarParser.QuotedContext ctx);
	/**
	 * Visit a parse tree produced by {@link CommandLineGrammarParser#single_quote}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingle_quote(CommandLineGrammarParser.Single_quoteContext ctx);
	/**
	 * Visit a parse tree produced by {@link CommandLineGrammarParser#double_quote}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDouble_quote(CommandLineGrammarParser.Double_quoteContext ctx);
	/**
	 * Visit a parse tree produced by {@link CommandLineGrammarParser#back_quote}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBack_quote(CommandLineGrammarParser.Back_quoteContext ctx);
}