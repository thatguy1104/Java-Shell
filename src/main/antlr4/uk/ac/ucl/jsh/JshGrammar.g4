grammar JshGrammar;

/*
 * Parser Rules
 */

/*
 * Command line parsing
 */

start: command EOF | seq EOF;

command: pipe | call;

pipe: call1 = call '|' call2 = call #pipeBaseCase
    | pipe '|' call #pipeRecursiveCase;

seq: command1 = command ';' command2 = command #seqBaseCase
    | seq ';' command #seqRecursiveCase;

//call : (quoted | NON_KEYWORD)* ; //THIS IS THE OLD CALL AS MENTIONED IN THE SPEC

/*
 * Call command
 */

call: WHITESPACE* (redirection WHITESPACE)* argument (WHITESPACE atom)* WHITESPACE*;

atom: redirection | argument;

argument: (quoted | UNQUOTED)+;

redirection: '<' WHITESPACE argument | '>' WHITESPACE argument;

/*
 * Quoting
 */

quoted: (singleQuoted | doubleQuoted | backQuoted)+;

singleQuoted: SINGLEQUOTE (~NEWLINE | ~SINGLEQUOTE)* SINGLEQUOTE;

backQuoted: BACKQUOTE (~NEWLINE | ~BACKQUOTE)* BACKQUOTE;

doubleQuoted:
	DOUBLEQUOTE (
		BACKQUOTE
		| ~NEWLINE
		| ~BACKQUOTE
		| ~DOUBLEQUOTE
	)* DOUBLEQUOTE;
// previously doubleQuoted : DOUBLEQUOTE (BACKQUOTE | DOUBLEQUOTECONTENT)* DOUBLEQUOTE;



/*
 * Lexer Rules
 */

//NONSPECIAL : ~['";|]+;
WHITESPACE: ('\t' | ' ' | '\r' | '\n')+; //WHITESPACE : ('\t' | ' ' | '\r' | '\n' | '\u000C')+;
UNQUOTED: (
		~(
			'\r'
			| '\n'
			| ' '
			| '\''
			| '\t'
			| '"'
			| '`'
			| ';'
			| '|'
			| '<'
			| '>'
		)
	)+;
//NON_KEYWORD : ~['";|\n`]+;
SINGLEQUOTE: '\'';
DOUBLEQUOTE: '"';
BACKQUOTE: '`';
//NONNEWLINE : ~('\n')+;
NEWLINE: '\n';
//NONSINGLEQUOTE : ~('\'')+; NONBACKQUOTE : ~('`')+; DOUBLEQUOTECONTENT : ~[\n"`]+; UNQUOTED :
// ~([\n\t\r'`´";|<>] | ' ')+; // forward quote included or not?

// MAIN Q: forward quote to be included in unquoted or not?