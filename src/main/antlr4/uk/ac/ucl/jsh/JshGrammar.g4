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

/* Command line parsing */

command : pipe | call;

pipe : call '|' call | pipe '|' call;

seq : command ';' command | seq ';' command;

call : (quoted | NON_KEYWORD)* ;

/* Quoting */

quoted : (singleQuoted | doubleQuoted | backQuoted)+;

singleQuoted : SINGLEQUOTE (NONNEWLINE | NONSINGLEQUOTE)* SINGLEQUOTE;

backQuoted : BACKQUOTE (NONNEWLINE | NONBACKQUOTE)* BACKQUOTE;

doubleQuoted : DOUBLEQUOTE (BACKQUOTE | DOUBLEQUOTECONTENT)* DOUBLEQUOTE;

/* Call command */

callcommand : WHITESPACE (redirection WHITESPACE)* argument (WHITESPACE atom)* WHITESPACE;

atom : redirection | argument;

argument : (quoted | UNQUOTED)+;

redirection : '<' WHITESPACE argument 
            | '>' WHITESPACE argument;





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
UNQUOTED : ~([\n\t\r'`´";|<>] | ' ')+; // forward quote included or not?