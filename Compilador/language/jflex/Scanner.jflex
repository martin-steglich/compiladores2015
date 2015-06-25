package com.language.parser;

import java.util.*;
import java_cup.runtime.*;
import com.language.exceptions.*;
import com.language.model.expression.*;

%%

%cup
%line
%unicode
%column

%class Scanner
%{
	private SymbolFactory sf;
	private StringBuffer string = new StringBuffer();

	public Scanner(java.io.InputStream r, SymbolFactory sf) {
		this(r);
		this.sf=sf;
	}

	private Symbol symbol(int type) {
		return new Symbol(type, yyline, yycolumn);
	}
	private Symbol symbol(int type, Object value) {
		return new Symbol(type, yyline, yycolumn, value);
	}
%}

%eofval{
    return symbol(sym.EOF);
%eofval}

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

WhiteSpace = {LineTerminator} | [ \t\f]+

/* comentarios */
Comment = "#" {InputCharacter}* {LineTerminator}?

/* identificadores */
Identifier = [a-zA-Z][a-zA-Z0-9_]*

/* enteros */
DecIntegerLiteral = 0 | [1-9][0-9]*
DecLongLiteral    = {DecIntegerLiteral} [lL]

/*HexIntegerLiteral = 0 [xX] 0* {HexDigit} {1,8}
HexLongLiteral    = 0 [xX] 0* {HexDigit} {1,16} [lL]
HexDigit          = [0-9a-fA-F]

OctIntegerLiteral = 0+ [1-3]? {OctDigit} {1,15}
OctLongLiteral    = 0+ 1? {OctDigit} {1,21} [lL]
OctDigit          = [0-7]*/

/* flotante */
FloatLiteral  = ({FLit1}|{FLit2}|{FLit3}) {Exponent}? [fF]
DoubleLiteral = ({FLit1}|{FLit2}|{FLit3}) {Exponent}?

FLit1    = [0-9]+ \. [0-9]* 
FLit2    = \. [0-9]+ 
FLit3    = [0-9]+ 
Exponent = [eE] [+-]? [0-9]+

/* string */
DoubleStringCharacter = [^\r\n\"\\]
SimpleStringCharacter = [^\r\n\'\\]

/* definicion de estdos */
/*
DOUBLE_QUOTE_ONCE_STRING: "ESTO"
SIMPLE_QUOTE_ONCE_STRING: 'ES'
DOUBLE_QUOTE_TRIPLE_STRING: """UN"""
SIMPLE_QUOTE_TRIPLE_STRING: '''EJEMPLO'''
*/
%state DOUBLE_QUOTE_ONCE_STRING, SIMPLE_QUOTE_ONCE_STRING, DOUBLE_QUOTE_TRIPLE_STRING, SIMPLE_QUOTE_TRIPLE_STRING

%%

/* palabras reservadas */
<YYINITIAL> {
	"and"					|
	"break"					|
	"continue"				|
	"def"					|
	"else"					|
	"for"					|
	"if"					|
	"in"					|
	"not"					|
	"or"					|
	"print"					|
	"return"				|
	"while"					{ return new Symbol(sym.KEYWORD, yytext(), yyline); }
}

/* tipos */
<YYINITIAL> {
	"False"					|
	"True"					|
	"bool"					|
	"float"					|
	"int"					|
	"long"					|
	"str"					|
	"tuple"					|
	"type"					|
	"unichr"				{ return new Symbol(sym.TYPE, yytext(), yyline); }	
}

/* operadores */
<YYINITIAL> {
	"("						|
	")"						|
	"{"						|
	"}"						|
	"["						|
	"]"						|
	"+"						|
	"-"						|
	"*"						|
	"**"					|
	"/"						|
	"//"					|
	"%"						|
	"<<"					|
	">>"					|
	"&"						|
	"|"						|
	"^"						|
	"~"						|
	"<"             		|
  	">"             		|
  	"<="            		|
  	">="            		|
  	"=="            		|
  	"!="            		|
  	","             		|
  	":"             		|
  	"."             		|
  	"`"             		|
  	"="             		|
  	";"   					{ return new Symbol(sym.OPERATOR, yytext(), yyline); }	
}

/* string */
<YYINITIAL> {
	\"{3}					{ yybegin(DOUBLE_QUOTE_TRIPLE_STRING); }
	\"						{ yybegin(DOUBLE_QUOTE_ONCE_STRING); }
	\'{3}					{ yybegin(SIMPLE_QUOTE_TRIPLE_STRING); }
	\'						{ yybegin(SIMPLE_QUOTE_ONCE_STRING); }
}

/* numeros */
<YYINITIAL> {
	{DecIntegerLiteral} 	|
  	{DecLongLiteral}        |
  	{FloatLiteral}			|
  	{DoubleLiteral}			|
  	{FloatLiteral}[jJ]		{ return new Symbol(sym.NUMBER, yytext(), yyline); }		
}

/* comentarios */
<YYINITIAL> {
	{Comment}				{ return new Symbol(sym.COMMENT, yytext(), yyline); }	
}

/* espacios */
<YYINITIAL> {
	{WhiteSpace}			{ }
}

/* identificadores */
<YYINITIAL> {
	{Identifier}			{ return new Symbol(sym.IDENTIFIER, yytext(), yyline); }		
}

/* errores */
<YYINITIAL> {
	"$" 					|
	"?"						{ return new Symbol(sym.ERROR, yytext(), yyline); }
}





