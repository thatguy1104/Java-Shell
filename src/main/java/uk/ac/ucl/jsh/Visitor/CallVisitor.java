package uk.ac.ucl.jsh.Visitor;

import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.Parser.Call.CallGrammarBaseVisitor;
import uk.ac.ucl.jsh.Parser.Call.CallGrammarParser;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CallVisitor extends CallGrammarBaseVisitor<ArrayList<String>>{
    @Override
    /**
    Visits the start rule from CallGrammar
    @param ctx The current context from ANTLR being parsed
     */
    public ArrayList<String> visitStart(CallGrammarParser.StartContext ctx) {
        return visit(ctx.arguments());
    }

    @Override
    /**
     Visits the arguments rule from CallGrammar
     @param ctx The current context from ANTLR being parsed
     */
    public ArrayList<String> visitArguments(CallGrammarParser.ArgumentsContext ctx) {
        ArrayList<String> argumentsArray = new ArrayList<>();
        if (ctx.redirection() != null) {
            for (int counter = 0; counter < ctx.redirection().size(); counter++) {
                argumentsArray.addAll(visit(ctx.redirection(counter)));
            }
        }

        argumentsArray.addAll(visit(ctx.argument()));

        if (ctx.call_type() != null) {
            for (int counter = 0; counter < ctx.call_type().size(); counter++) {
                argumentsArray.addAll(visit(ctx.call_type(counter)));
            }
        }
        return argumentsArray;
    }

    @Override
    /**
     Visits the redirection rule from CallGrammar
     @param ctx The current context from ANTLR being parsed
     */
    public ArrayList<String> visitRedirection(CallGrammarParser.RedirectionContext ctx) {
        ArrayList<String> redirectionArray = new ArrayList<>();
        redirectionArray.add(ctx.getChild(0).getText());
        redirectionArray.addAll(visit(ctx.argument()));
        return redirectionArray;
    }

    @Override
    /**
     Visits the call_type rule from CallGrammar
     @param ctx The current context from ANTLR being parsed
     */
    public ArrayList<String> visitCall_type(CallGrammarParser.Call_typeContext ctx) {
        ArrayList<String> callTypeArray = new ArrayList<>();
        if (ctx.redirection() != null) {
            callTypeArray.addAll(visit(ctx.redirection()));
        } else if (ctx.argument() != null) {
            callTypeArray.addAll(visit(ctx.argument()));
        } else {
            if (ctx.call_type() != null) {
                callTypeArray.addAll(visit(ctx.call_type()));
            }
            if (ctx.arguments() != null) {
                callTypeArray.addAll(visit(ctx.arguments()));
            }
        }
        return callTypeArray;
    }

    @Override
    /**
     Visits the argument rule from CallGrammar
     @param ctx The current context from ANTLR being parsed
     */
    public ArrayList<String> visitArgument(CallGrammarParser.ArgumentContext ctx) {
        ArrayList<String> argumentArray = new ArrayList<>();
        if (ctx.unquotedArgument != null) {
            argumentArray.addAll(new ArrayList<>(Arrays.asList(ctx.unquotedArgument.getText())));
        } else if (ctx.singleQuoteArgument != null) {
            argumentArray.addAll(visit(ctx.single_quote()));
        } else if (ctx.doubleQuoteArgument != null) {
            argumentArray.addAll(visit(ctx.double_quote()));
        } else if (ctx.backQuoteArgument != null) {
            argumentArray.addAll(visit(ctx.back_quote()));
        }
        if (ctx.argument() != null) {
            argumentArray.addAll(visit(ctx.argument()));
        }
        return argumentArray;
    }

    @Override
    /**
     Visits the single quote rule from CallGrammar
     @param ctx The current context from ANTLR being parsed
     */
    public ArrayList<String> visitSingle_quote(CallGrammarParser.Single_quoteContext ctx) {
        ArrayList<String> singleQuoteArray = new ArrayList<>(List.of(ctx.contents.getText()));
        return singleQuoteArray;
    }

    @Override
    /**
     Visits the double quote rule from CallGrammar
     @param ctx The current context from ANTLR being parsed
     */
    public ArrayList<String> visitDouble_quote(CallGrammarParser.Double_quoteContext ctx) {
        return visit(ctx.double_quote_options());
    }

    @Override
    /**
     Visits the double quote options rule from CallGrammar
     @param ctx The current context from ANTLR being parsed
     */
    public ArrayList<String> visitDouble_quote_options(CallGrammarParser.Double_quote_optionsContext ctx) {
        ArrayList<String> doubleQuoteArray = new ArrayList<>();
        if (ctx.contents != null) {
            doubleQuoteArray.addAll(new ArrayList<>(Arrays.asList(ctx.contents.getText())));
        } else if (ctx.back_quote() != null) {
            doubleQuoteArray.addAll(visit(ctx.back_quote()));
        }
        if (ctx.double_quote_options() != null) {
            doubleQuoteArray.addAll(visit(ctx.double_quote_options()));
        }
        doubleQuoteArray.add("\"");
        return doubleQuoteArray;
    }

    @Override
    /**
     Visits the back quote rule from CallGrammar
     @param ctx The current context from ANTLR being parsed
     */
    public ArrayList<String> visitBack_quote(CallGrammarParser.Back_quoteContext ctx) {
        String backQuoteString = ctx.contents.getText();
        if (backQuoteString.equals("")) {
            return new ArrayList<>(List.of(""));
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Jsh.eval(backQuoteString, outputStream);
        ArrayList<String> result = new ArrayList<>(Arrays.asList(outputStream.toString().trim().split(Jsh.lineSeparator)));
        result.add("`");

        return result;
    }
}
