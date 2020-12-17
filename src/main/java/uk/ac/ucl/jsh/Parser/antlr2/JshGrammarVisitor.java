// Generated from C:/Users/aashv/Documents/GitHub/jsh-team-41/src/main/antlr4/uk/ac/ucl/jsh\JshGrammar.g4 by ANTLR 4.9
package uk.ac.ucl.jsh.Parser.antlr2;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link JshGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface JshGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link JshGrammarParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(JshGrammarParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link JshGrammarParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommand(JshGrammarParser.CommandContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pipeBaseCase}
	 * labeled alternative in {@link JshGrammarParser#pipe}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPipeBaseCase(JshGrammarParser.PipeBaseCaseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pipeRecursiveCase}
	 * labeled alternative in {@link JshGrammarParser#pipe}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPipeRecursiveCase(JshGrammarParser.PipeRecursiveCaseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code seqBaseCase}
	 * labeled alternative in {@link JshGrammarParser#seq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeqBaseCase(JshGrammarParser.SeqBaseCaseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code seqRecursiveCase}
	 * labeled alternative in {@link JshGrammarParser#seq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeqRecursiveCase(JshGrammarParser.SeqRecursiveCaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link JshGrammarParser#call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall(JshGrammarParser.CallContext ctx);
}