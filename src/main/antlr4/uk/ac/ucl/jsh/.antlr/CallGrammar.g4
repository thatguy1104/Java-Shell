grammar CallGrammar;

start: arguments EOF;

arguments: WHITESPACE* (redirection WHITESPACE)* argument (WHITESPACE call_type)* WHITESPACE*;

redirection: INPUTREDIRECTION WHITESPACE+ argument | OUTPUTREDIRECTION WHITESPACE+ argument;

call_type: redirection | argument | call_type WHITESPACE+ arguments;

argument: unquoted = UNQUOTED argument?
        | single = single_quote argument?
        | double = double_quote argument?
        | back = back_quote argument?;

single_quote: SINGLEQUOTE contents = (WHITESPACE | UNQUOTED | PIPE | SEMICOLON | INPUTREDIRECTION | OUTPUTREDIRECTION | DOUBLEQUOTE | BACKQUOTE)* SINGLEQUOTE;

double_quote: DOUBLEQUOTE (contents = (WHITESPACE | UNQUOTED | PIPE | SEMICOLON | INPUTREDIRECTION | SINGLEQUOTE) | back_quote)* DOUBLEQUOTE;

back_quote: BACKQUOTE contents = (WHITESPACE | UNQUOTED | PIPE | SEMICOLON | INPUTREDIRECTION | OUTPUTREDIRECTION | SINGLEQUOTE | DOUBLEQUOTE)* BACKQUOTE;


WHITESPACE: ('\t' | ' ' | '\r' | '\n');
UNQUOTED: (~('|' | ';' | '<' | '>' | '\'' | '"' | '`' | ' ' | '\n' | '\r' | '\t'))+;
PIPE: '|';
SEMICOLON: ';';
INPUTREDIRECTION: '<';
OUTPUTREDIRECTION: '<';
SINGLEQUOTE: '\'';
DOUBLEQUOTE: '"';
BACKQUOTE: '`';