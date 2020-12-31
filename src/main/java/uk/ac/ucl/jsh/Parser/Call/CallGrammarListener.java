// Generated from C:/Users/aashv/Documents/GitHub/jsh-team-41/src/main/antlr4/uk/ac/ucl/jsh/.antlr\CallGrammar.g4 by ANTLR 4.9
package uk.ac.ucl.jsh.Parser.Call;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CallGrammarParser}.
 */
public interface CallGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CallGrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(CallGrammarParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link CallGrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(CallGrammarParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link CallGrammarParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(CallGrammarParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CallGrammarParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(CallGrammarParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CallGrammarParser#redirection}.
	 * @param ctx the parse tree
	 */
	void enterRedirection(CallGrammarParser.RedirectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CallGrammarParser#redirection}.
	 * @param ctx the parse tree
	 */
	void exitRedirection(CallGrammarParser.RedirectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CallGrammarParser#call_type}.
	 * @param ctx the parse tree
	 */
	void enterCall_type(CallGrammarParser.Call_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CallGrammarParser#call_type}.
	 * @param ctx the parse tree
	 */
	void exitCall_type(CallGrammarParser.Call_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CallGrammarParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(CallGrammarParser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link CallGrammarParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(CallGrammarParser.ArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link CallGrammarParser#single_quote}.
	 * @param ctx the parse tree
	 */
	void enterSingle_quote(CallGrammarParser.Single_quoteContext ctx);
	/**
	 * Exit a parse tree produced by {@link CallGrammarParser#single_quote}.
	 * @param ctx the parse tree
	 */
	void exitSingle_quote(CallGrammarParser.Single_quoteContext ctx);
	/**
	 * Enter a parse tree produced by {@link CallGrammarParser#single_quote_contents}.
	 * @param ctx the parse tree
	 */
	void enterSingle_quote_contents(CallGrammarParser.Single_quote_contentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CallGrammarParser#single_quote_contents}.
	 * @param ctx the parse tree
	 */
	void exitSingle_quote_contents(CallGrammarParser.Single_quote_contentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CallGrammarParser#double_quote}.
	 * @param ctx the parse tree
	 */
	void enterDouble_quote(CallGrammarParser.Double_quoteContext ctx);
	/**
	 * Exit a parse tree produced by {@link CallGrammarParser#double_quote}.
	 * @param ctx the parse tree
	 */
	void exitDouble_quote(CallGrammarParser.Double_quoteContext ctx);
	/**
	 * Enter a parse tree produced by {@link CallGrammarParser#double_quote_options}.
	 * @param ctx the parse tree
	 */
	void enterDouble_quote_options(CallGrammarParser.Double_quote_optionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CallGrammarParser#double_quote_options}.
	 * @param ctx the parse tree
	 */
	void exitDouble_quote_options(CallGrammarParser.Double_quote_optionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CallGrammarParser#back_quote}.
	 * @param ctx the parse tree
	 */
	void enterBack_quote(CallGrammarParser.Back_quoteContext ctx);
	/**
	 * Exit a parse tree produced by {@link CallGrammarParser#back_quote}.
	 * @param ctx the parse tree
	 */
	void exitBack_quote(CallGrammarParser.Back_quoteContext ctx);
	/**
	 * Enter a parse tree produced by {@link CallGrammarParser#back_quote_contents}.
	 * @param ctx the parse tree
	 */
	void enterBack_quote_contents(CallGrammarParser.Back_quote_contentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CallGrammarParser#back_quote_contents}.
	 * @param ctx the parse tree
	 */
	void exitBack_quote_contents(CallGrammarParser.Back_quote_contentsContext ctx);
}