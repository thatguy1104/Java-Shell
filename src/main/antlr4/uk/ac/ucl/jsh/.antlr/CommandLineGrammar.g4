grammar CommandLineGrammar;

/*
 * Command line parsing
 */

/*
 * Parser Rules
 */

start: command EOF | seq EOF;

command: pipe | call;

pipe: call1 = call '|' call2 = call #pipeBaseCase
    | pipe '|' call #pipeRecursiveCase;

seq: command1 = command ';' command2 = command #seqBaseCase
    | seq ';' command #seqRecursiveCase;

call: (WHITESPACE | UNQUOTED | INPUTREDIRECTION | OUTPUTREDIRECTION | quoted)+;

quoted: single_quote | double_quote | back_quote;

single_quote: SINGLEQUOTE contents = (WHITESPACE | UNQUOTED | PIPE | SEMICOLON | INPUTREDIRECTION | OUTPUTREDIRECTION | DOUBLEQUOTE | BACKQUOTE)* SINGLEQUOTE;

double_quote: DOUBLEQUOTE (contents = (WHITESPACE | UNQUOTED | PIPE | SEMICOLON | INPUTREDIRECTION | SINGLEQUOTE) | back_quote)* DOUBLEQUOTE;

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
