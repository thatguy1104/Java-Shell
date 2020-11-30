grammar JshGrammar;

/*
 * Parser Rules
 */

// <command> ::= <pipe> | <seq> | <call>
// <seq> ::= <command> “;” <command>
// <call> ::= ( <non-keyword> | <quoted> ) *
// <redirection> ::= “<” [ <whitespace> ] <argument>

// pipe: command ('|' command)* | command;

// command : atomicCommand (';' atomicCommand)*;

// atomicCommand : (NONSPECIAL | DOUBLEQUOTED | SINGLEQUOTED)+;

quoted : (singleQuoted | doubleQuoted | backQuoted)+;

call : (singleQuoted | doubleQuoted | NON_KEYWORD)* ;

singleQuoted : SINGLEQUOTE (NONNEWLINE | NONSINGLEQUOTE)* SINGLEQUOTE;

backQuoted : BACKQUOTE (NONNEWLINE | NONBACKQUOTE)* BACKQUOTE;

doubleQuoted : DOUBLEQUOTE (BACKQUOTE | DOUBLEQUOTECONTENT)* DOUBLEQUOTE;



/*
 * Lexer Rules
 */

NONSPECIAL : ~['";|]+;
WHITESPACE : ('\t' | ' ' | '\r' | '\n')+; //WHITESPACE : ('\t' | ' ' | '\r' | '\n' | '\u000C')+;
NON_KEYWORD : ~['";|\n`];
SINGLEQUOTE : '\'';
DOUBLEQUOTE : '"';
BACKQUOTE : '`';
NONNEWLINE : ~('\n')+;
NONSINGLEQUOTE : ~('\'')+;
NONBACKQUOTE : ~('`')+;
DOUBLEQUOTECONTENT : ~[\n"`]+; 