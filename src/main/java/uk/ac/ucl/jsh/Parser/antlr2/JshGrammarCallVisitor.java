package uk.ac.ucl.jsh.Parser.antlr2;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

public interface JshGrammarCallVisitor<T> extends ParseTreeVisitor<T> {
    /**
     * Visit a parse tree produced by {@link JshGrammarParser#atom}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAtom(JshGrammarParser.AtomContext ctx);
    /**
     * Visit a parse tree produced by {@link JshGrammarParser#argument}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitArgument(JshGrammarParser.ArgumentContext ctx);
    /**
     * Visit a parse tree produced by {@link JshGrammarParser#redirection}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitRedirection(JshGrammarParser.RedirectionContext ctx);
    /**
     * Visit a parse tree produced by {@link JshGrammarParser#quoted}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitQuoted(JshGrammarParser.QuotedContext ctx);
    /**
     * Visit a parse tree produced by {@link JshGrammarParser#singleQuoted}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSingleQuoted(JshGrammarParser.SingleQuotedContext ctx);
    /**
     * Visit a parse tree produced by {@link JshGrammarParser#backQuoted}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitBackQuoted(JshGrammarParser.BackQuotedContext ctx);
    /**
     * Visit a parse tree produced by {@link JshGrammarParser#doubleQuoted}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitDoubleQuoted(JshGrammarParser.DoubleQuotedContext ctx);
}
