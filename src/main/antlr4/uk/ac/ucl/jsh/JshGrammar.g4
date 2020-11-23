grammar JshGrammar;

/*
 * Parser Rules
 */

// <command> ::= <pipe> | <seq> | <call>
// <seq> ::= <command> “;” <command>
// <call> ::= ( <non-keyword> | <quoted> ) *
// <redirection> ::= “<” [ <whitespace> ] <argument>

pipe: command ('|' command)* | command;

command : atomicCommand (';' atomicCommand)*;

atomicCommand : (NONSPECIAL | DOUBLEQUOTED | SINGLEQUOTED)+;



/*
 * Lexer Rules
 */

NONSPECIAL : ~['";|]+;
DOUBLEQUOTED : '"' (~'"')* '"';
SINGLEQUOTED : '\'' (~'\'')* '\'';