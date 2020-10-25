grammar JshGrammar;

/*
 * Parser Rules
 */

pipe: command ('|' command)*;

command : atomicCommand (';' atomicCommand)*;

atomicCommand : (NONSPECIAL | DOUBLEQUOTED | SINGLEQUOTED)+;




/*
 * Lexer Rules
 */

NONSPECIAL : ~['";|]+;
DOUBLEQUOTED : '"' (~'"')* '"';
SINGLEQUOTED : '\'' (~'\'')* '\'';