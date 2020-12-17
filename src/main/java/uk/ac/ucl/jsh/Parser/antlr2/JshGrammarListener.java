// Generated from C:/Users/aashv/Documents/GitHub/jsh-team-41/src/main/antlr4/uk/ac/ucl/jsh\JshGrammar.g4 by ANTLR 4.9
package uk.ac.ucl.jsh.Parser.antlr2;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link JshGrammarParser}.
 */
public interface JshGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link JshGrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(JshGrammarParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link JshGrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(JshGrammarParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link JshGrammarParser#command}.
	 * @param ctx the parse tree
	 */
	void enterCommand(JshGrammarParser.CommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link JshGrammarParser#command}.
	 * @param ctx the parse tree
	 */
	void exitCommand(JshGrammarParser.CommandContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pipeBaseCase}
	 * labeled alternative in {@link JshGrammarParser#pipe}.
	 * @param ctx the parse tree
	 */
	void enterPipeBaseCase(JshGrammarParser.PipeBaseCaseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pipeBaseCase}
	 * labeled alternative in {@link JshGrammarParser#pipe}.
	 * @param ctx the parse tree
	 */
	void exitPipeBaseCase(JshGrammarParser.PipeBaseCaseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pipeRecursiveCase}
	 * labeled alternative in {@link JshGrammarParser#pipe}.
	 * @param ctx the parse tree
	 */
	void enterPipeRecursiveCase(JshGrammarParser.PipeRecursiveCaseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pipeRecursiveCase}
	 * labeled alternative in {@link JshGrammarParser#pipe}.
	 * @param ctx the parse tree
	 */
	void exitPipeRecursiveCase(JshGrammarParser.PipeRecursiveCaseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code seqBaseCase}
	 * labeled alternative in {@link JshGrammarParser#seq}.
	 * @param ctx the parse tree
	 */
	void enterSeqBaseCase(JshGrammarParser.SeqBaseCaseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code seqBaseCase}
	 * labeled alternative in {@link JshGrammarParser#seq}.
	 * @param ctx the parse tree
	 */
	void exitSeqBaseCase(JshGrammarParser.SeqBaseCaseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code seqRecursiveCase}
	 * labeled alternative in {@link JshGrammarParser#seq}.
	 * @param ctx the parse tree
	 */
	void enterSeqRecursiveCase(JshGrammarParser.SeqRecursiveCaseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code seqRecursiveCase}
	 * labeled alternative in {@link JshGrammarParser#seq}.
	 * @param ctx the parse tree
	 */
	void exitSeqRecursiveCase(JshGrammarParser.SeqRecursiveCaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link JshGrammarParser#call}.
	 * @param ctx the parse tree
	 */
	void enterCall(JshGrammarParser.CallContext ctx);
	/**
	 * Exit a parse tree produced by {@link JshGrammarParser#call}.
	 * @param ctx the parse tree
	 */
	void exitCall(JshGrammarParser.CallContext ctx);
	/**
	 * Enter a parse tree produced by {@link JshGrammarParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(JshGrammarParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link JshGrammarParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(JshGrammarParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link JshGrammarParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(JshGrammarParser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link JshGrammarParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(JshGrammarParser.ArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link JshGrammarParser#redirection}.
	 * @param ctx the parse tree
	 */
	void enterRedirection(JshGrammarParser.RedirectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JshGrammarParser#redirection}.
	 * @param ctx the parse tree
	 */
	void exitRedirection(JshGrammarParser.RedirectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JshGrammarParser#quoted}.
	 * @param ctx the parse tree
	 */
	void enterQuoted(JshGrammarParser.QuotedContext ctx);
	/**
	 * Exit a parse tree produced by {@link JshGrammarParser#quoted}.
	 * @param ctx the parse tree
	 */
	void exitQuoted(JshGrammarParser.QuotedContext ctx);
	/**
	 * Enter a parse tree produced by {@link JshGrammarParser#singleQuoted}.
	 * @param ctx the parse tree
	 */
	void enterSingleQuoted(JshGrammarParser.SingleQuotedContext ctx);
	/**
	 * Exit a parse tree produced by {@link JshGrammarParser#singleQuoted}.
	 * @param ctx the parse tree
	 */
	void exitSingleQuoted(JshGrammarParser.SingleQuotedContext ctx);
	/**
	 * Enter a parse tree produced by {@link JshGrammarParser#backQuoted}.
	 * @param ctx the parse tree
	 */
	void enterBackQuoted(JshGrammarParser.BackQuotedContext ctx);
	/**
	 * Exit a parse tree produced by {@link JshGrammarParser#backQuoted}.
	 * @param ctx the parse tree
	 */
	void exitBackQuoted(JshGrammarParser.BackQuotedContext ctx);
	/**
	 * Enter a parse tree produced by {@link JshGrammarParser#doubleQuoted}.
	 * @param ctx the parse tree
	 */
	void enterDoubleQuoted(JshGrammarParser.DoubleQuotedContext ctx);
	/**
	 * Exit a parse tree produced by {@link JshGrammarParser#doubleQuoted}.
	 * @param ctx the parse tree
	 */
	void exitDoubleQuoted(JshGrammarParser.DoubleQuotedContext ctx);
}