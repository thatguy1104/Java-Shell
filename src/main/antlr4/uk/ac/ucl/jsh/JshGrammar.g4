grammar JshGrammar;

/*
 * Parser Rules
 */

 command: pipe | seq | call;
 seq: command ';' command;
 call: (nonkeyword | quoted) *;
 redirection: '>' argument;

//pipe: command ('|' command)* | command;
//
//command : atomicCommand (';' atomicCommand)*;
//
//atomicCommand : (NONSPECIAL | DOUBLEQUOTED | SINGLEQUOTED)+;



/*
 * Lexer Rules
 */

NONSPECIAL : ~['";|]+;
DOUBLEQUOTED : '"' (~'"')* '"';
SINGLEQUOTED : '\'' (~'\'')* '\'';