// Generated from C:/Users/aashv/Documents/GitHub/jsh-team-41/src/main/antlr4/uk/ac/ucl/jsh/.antlr\CommandLineGrammar.g4 by ANTLR 4.9
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CommandLineGrammarParser}.
 */
public interface CommandLineGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CommandLineGrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(CommandLineGrammarParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommandLineGrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(CommandLineGrammarParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommandLineGrammarParser#command}.
	 * @param ctx the parse tree
	 */
	void enterCommand(CommandLineGrammarParser.CommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommandLineGrammarParser#command}.
	 * @param ctx the parse tree
	 */
	void exitCommand(CommandLineGrammarParser.CommandContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pipeBaseCase}
	 * labeled alternative in {@link CommandLineGrammarParser#pipe}.
	 * @param ctx the parse tree
	 */
	void enterPipeBaseCase(CommandLineGrammarParser.PipeBaseCaseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pipeBaseCase}
	 * labeled alternative in {@link CommandLineGrammarParser#pipe}.
	 * @param ctx the parse tree
	 */
	void exitPipeBaseCase(CommandLineGrammarParser.PipeBaseCaseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pipeRecursiveCase}
	 * labeled alternative in {@link CommandLineGrammarParser#pipe}.
	 * @param ctx the parse tree
	 */
	void enterPipeRecursiveCase(CommandLineGrammarParser.PipeRecursiveCaseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pipeRecursiveCase}
	 * labeled alternative in {@link CommandLineGrammarParser#pipe}.
	 * @param ctx the parse tree
	 */
	void exitPipeRecursiveCase(CommandLineGrammarParser.PipeRecursiveCaseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code seqBaseCase}
	 * labeled alternative in {@link CommandLineGrammarParser#seq}.
	 * @param ctx the parse tree
	 */
	void enterSeqBaseCase(CommandLineGrammarParser.SeqBaseCaseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code seqBaseCase}
	 * labeled alternative in {@link CommandLineGrammarParser#seq}.
	 * @param ctx the parse tree
	 */
	void exitSeqBaseCase(CommandLineGrammarParser.SeqBaseCaseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code seqRecursiveCase}
	 * labeled alternative in {@link CommandLineGrammarParser#seq}.
	 * @param ctx the parse tree
	 */
	void enterSeqRecursiveCase(CommandLineGrammarParser.SeqRecursiveCaseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code seqRecursiveCase}
	 * labeled alternative in {@link CommandLineGrammarParser#seq}.
	 * @param ctx the parse tree
	 */
	void exitSeqRecursiveCase(CommandLineGrammarParser.SeqRecursiveCaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommandLineGrammarParser#call}.
	 * @param ctx the parse tree
	 */
	void enterCall(CommandLineGrammarParser.CallContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommandLineGrammarParser#call}.
	 * @param ctx the parse tree
	 */
	void exitCall(CommandLineGrammarParser.CallContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommandLineGrammarParser#quoted}.
	 * @param ctx the parse tree
	 */
	void enterQuoted(CommandLineGrammarParser.QuotedContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommandLineGrammarParser#quoted}.
	 * @param ctx the parse tree
	 */
	void exitQuoted(CommandLineGrammarParser.QuotedContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommandLineGrammarParser#single_quote}.
	 * @param ctx the parse tree
	 */
	void enterSingle_quote(CommandLineGrammarParser.Single_quoteContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommandLineGrammarParser#single_quote}.
	 * @param ctx the parse tree
	 */
	void exitSingle_quote(CommandLineGrammarParser.Single_quoteContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommandLineGrammarParser#double_quote}.
	 * @param ctx the parse tree
	 */
	void enterDouble_quote(CommandLineGrammarParser.Double_quoteContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommandLineGrammarParser#double_quote}.
	 * @param ctx the parse tree
	 */
	void exitDouble_quote(CommandLineGrammarParser.Double_quoteContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommandLineGrammarParser#back_quote}.
	 * @param ctx the parse tree
	 */
	void enterBack_quote(CommandLineGrammarParser.Back_quoteContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommandLineGrammarParser#back_quote}.
	 * @param ctx the parse tree
	 */
	void exitBack_quote(CommandLineGrammarParser.Back_quoteContext ctx);
}