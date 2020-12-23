grammar CallGrammar;

/*
 * Call parsing
 */

/*
 * Parser Rules
 */

start: arguments EOF;

arguments: WHITESPACE* (redirection WHITESPACE)* argument (WHITESPACE call_type)* WHITESPACE*;

redirection: INPUTREDIRECTION WHITESPACE+ argument | OUTPUTREDIRECTION WHITESPACE+ argument;

call_type: redirection | argument | call_type WHITESPACE+ arguments;

argument: unquotedArgument = UNQUOTED argument?
        | singleQuoteArgument = single_quote argument?
        | doubleQuoteArgument = double_quote argument?
        | backQuoteArgument = back_quote argument?;

single_quote: SINGLEQUOTE contents = (WHITESPACE | UNQUOTED | PIPE | SEMICOLON | INPUTREDIRECTION | OUTPUTREDIRECTION | DOUBLEQUOTE | BACKQUOTE)* SINGLEQUOTE;

double_quote: DOUBLEQUOTE double_quote_options DOUBLEQUOTE;
double_quote_options: contents = (WHITESPACE | UNQUOTED | PIPE | SEMICOLON | INPUTREDIRECTION | SINGLEQUOTE) double_quote_options
                    | back_quote double_quote_options
                    |;

back_quote: BACKQUOTE contents = (WHITESPACE | UNQUOTED | PIPE | SEMICOLON | INPUTREDIRECTION | OUTPUTREDIRECTION | SINGLEQUOTE | DOUBLEQUOTE)* BACKQUOTE;

/*
 * Lexer Rules
 */

WHITESPACE: ('\t' | ' ' | '\r' | '\n');
UNQUOTED: (~('|' | ';' | '<' | '>' | '\'' | '"' | '`' | ' ' | '\n' | '\r' | '\t'))+;
PIPE: '|';
SEMICOLON: ';';
INPUTREDIRECTION: '<';
OUTPUTREDIRECTION: '>';
SINGLEQUOTE: '\'';
DOUBLEQUOTE: '"';
BACKQUOTE: '`';