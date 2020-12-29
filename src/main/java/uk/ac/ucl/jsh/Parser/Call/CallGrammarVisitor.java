// Generated from C:/Users/aashv/Documents/GitHub/jsh-team-41/src/main/antlr4/uk/ac/ucl/jsh/.antlr\CallGrammar.g4 by ANTLR 4.9
package uk.ac.ucl.jsh.Parser.Call;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CallGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CallGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CallGrammarParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(CallGrammarParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link CallGrammarParser#arguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArguments(CallGrammarParser.ArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CallGrammarParser#redirection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRedirection(CallGrammarParser.RedirectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CallGrammarParser#call_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall_type(CallGrammarParser.Call_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CallGrammarParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument(CallGrammarParser.ArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link CallGrammarParser#single_quote}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingle_quote(CallGrammarParser.Single_quoteContext ctx);
	/**
	 * Visit a parse tree produced by {@link CallGrammarParser#single_quote_contents}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingle_quote_contents(CallGrammarParser.Single_quote_contentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CallGrammarParser#double_quote}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDouble_quote(CallGrammarParser.Double_quoteContext ctx);
	/**
	 * Visit a parse tree produced by {@link CallGrammarParser#double_quote_options}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDouble_quote_options(CallGrammarParser.Double_quote_optionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CallGrammarParser#back_quote}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBack_quote(CallGrammarParser.Back_quoteContext ctx);
	/**
	 * Visit a parse tree produced by {@link CallGrammarParser#back_quote_contents}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBack_quote_contents(CallGrammarParser.Back_quote_contentsContext ctx);
}