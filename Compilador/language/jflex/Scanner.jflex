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

	/*private Symbol symbol(int type) {
		return new Symbol(type, yyline, yycolumn);
	}
	private Symbol symbol(int type, Object value) {
		return new Symbol(type, yyline, yycolumn, value);
	}*/
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

<YYINITIAL> {
	/* palabras reservadas */
	"and"					{ return new Symbol(sym.AND, yyline, yycolumn, "and"); }
	"break"					{ return new Symbol(sym.BREAK, yyline, yycolumn, "break"); }
	"continue"				{ return new Symbol(sym.CONTINUE, yyline, yycolumn, "continue"); }
	"def"					{ return new Symbol(sym.DEF, yyline, yycolumn, "def"); }
	"else"					{ return new Symbol(sym.ELSE, yyline, yycolumn, "else"); }
	"for"					{ return new Symbol(sym.FOR, yyline, yycolumn, "for"); }
	"if"					{ return new Symbol(sym.IF, yyline, yycolumn, "if"); }
	"in"					{ return new Symbol(sym.IN, yyline, yycolumn, "in"); }
	"not"					{ return new Symbol(sym.NOT, yyline, yycolumn, "not"); }
	"or"					{ return new Symbol(sym.OR, yyline, yycolumn, "or"); }
	"print"					{ return new Symbol(sym.PRINT, yyline, yycolumn, "print"); }
	"return"				{ return new Symbol(sym.RETURN, yyline, yycolumn, "return"); }
	"while"					{ return new Symbol(sym.WHILE, yyline, yycolumn, "while"); }
	/* tipos */
	"False"					{ return new Symbol(sym.FALSE, yyline, yycolumn, "False"); }
	"True"					{ return new Symbol(sym.TRUE, yyline, yycolumn, "True"); }
	"bool"					{ return new Symbol(sym.BOOL, yyline, yycolumn, "bool"); }
	"float"					{ return new Symbol(sym.FLOAT, yyline, yycolumn, "float"); }
	"int"					{ return new Symbol(sym.INT, yyline, yycolumn, "int"); }
	"long"					{ return new Symbol(sym.LONG, yyline, yycolumn, "long"); }
	"str"					{ return new Symbol(sym.STR, yyline, yycolumn, "str"); }
	"tuple"					{ return new Symbol(sym.TUPLE, yyline, yycolumn, "tuple"); }
	"type"					{ return new Symbol(sym.TYPE, yyline, yycolumn, "type"); }
	/* operadores */
	"("						{ return new Symbol(sym.LPAREN, yyline, yycolumn, "("); }
	")"						{ return new Symbol(sym.RPAREN, yyline, yycolumn, ")"); }
	"{"						{ return new Symbol(sym.LBLOCK, yyline, yycolumn, "{"); }
	"}"						{ return new Symbol(sym.RBLOCK, yyline, yycolumn, "}"); }
	"["						{ return new Symbol(sym.LRECT, yyline, yycolumn, "["); }
	"]"						{ return new Symbol(sym.LRECT, yyline, yycolumn, "]"); }
	"+"						{ return new Symbol(sym.PLUS, yyline, yycolumn, "+"); }
	"-"						{ return new Symbol(sym.MINUS, yyline, yycolumn, "-"); }
	"*"						{ return new Symbol(sym.MUL, yyline, yycolumn, "*"); }
	"**"					{ return new Symbol(sym.EXP, yyline, yycolumn, "**"); }
	"/"						{ return new Symbol(sym.DIV, yyline, yycolumn, "/"); }
	"//"					{ return new Symbol(sym.INT_DIV, yyline, yycolumn, "//"); }
	"%"						{ return new Symbol(sym.MOD, yyline, yycolumn, "%"); }
	"<<"					{ return new Symbol(sym.LSHIFT, yyline, yycolumn, "<<"); }
	">>"					{ return new Symbol(sym.RSHIFT, yyline, yycolumn, ">>"); }
	"&"						{ return new Symbol(sym.AND_BIT, yyline, yycolumn, "&"); }
	"|"						{ return new Symbol(sym.OR_BIT, yyline, yycolumn, "|"); }
	"^"						{ return new Symbol(sym.XOR_BIT, yyline, yycolumn, "^"); }
	"~"						{ return new Symbol(sym.NOT_BIT, yyline, yycolumn, "~"); }
	"<"             		{ return new Symbol(sym.LESS_THAN, yyline, yycolumn, "<"); }
  	">"             		{ return new Symbol(sym.GREATER_THAN, yyline, yycolumn, ">"); }
  	"<="            		{ return new Symbol(sym.LESSOREQUAL_THAN, yyline, yycolumn, "<="); }
  	">="            		{ return new Symbol(sym.GREATEROREQUAL_THAN, yyline, yycolumn, ">="); }
  	"=="            		{ return new Symbol(sym.EQUAL, yyline, yycolumn, "=="); }
  	"!="            		{ return new Symbol(sym.NOT_EQUAL, yyline, yycolumn, "!="); }
  	","             		{ return new Symbol(sym.COMMA, yyline, yycolumn, ","); }
  	":"             		{ return new Symbol(sym.COLON, yyline, yycolumn, ":"); }
  	"."             		{ return new Symbol(sym.DOT, yyline, yycolumn, "."); }
  	"="             		{ return new Symbol(sym.ASSIGN, yyline, yycolumn, "="); }
  	";"   					{ return new Symbol(sym.SEMICOLON, yyline, yycolumn, ";"); }
  	/* string */
  	\"{3}					{ 
  								string.setLength(0);
  								yybegin(DOUBLE_QUOTE_TRIPLE_STRING); 
  							}
	\"						{ 
								string.setLength(0);
								yybegin(DOUBLE_QUOTE_ONCE_STRING); 
							}
	\'{3}					{ 
								string.setLength(0);
								yybegin(SIMPLE_QUOTE_TRIPLE_STRING);
							}
	\'						{ 
								string.setLength(0);
								yybegin(SIMPLE_QUOTE_ONCE_STRING);
							}
	/* numeros */
	{DecIntegerLiteral} 	{ return new Symbol(sym.INTEGER, yyline, yycolumn, yytext()); }
  	{DecLongLiteral}        { return new Symbol(sym.LONG, yyline, yycolumn, yytext()); }
  	{FloatLiteral}			{ return new Symbol(sym.FLOAT, yyline, yycolumn, yytext()); }
  	{DoubleLiteral}			{ return new Symbol(sym.DOUBLE, yyline, yycolumn, yytext()); }
  	{FloatLiteral}[jJ]		{ return new Symbol(sym.FLOAT, yyline, yycolumn, yytext()); }
  	/* comentarios */
  	{Comment}				{ /* ignore */ }
  	/* espacios */
	{WhiteSpace}			{ /* ignore */ }
	/* identificadores */
	{Identifier}			{ return new Symbol(sym.IDENTIFIER, yyline, yycolumn, yytext()); }
}

/* errores */
<YYINITIAL> {
	"$" 					|
	"?"						{ return new Symbol(sym.ERROR, yyline, yycolumn, yytext()); }
}

<DOUBLE_QUOTE_TRIPLE_STRING> {
	\"{3}					{ 
								yybegin(YYINITIAL); 
								return new Symbol(sym.STRING, yyline, yycolumn, string.toString());
							}
	.						{ 	
								string.append(yytext());
							}
}

<DOUBLE_QUOTE_ONCE_STRING> {
	\"						{
								yybegin(YYINITIAL);
								return new Symbol(sym.STRING, yyline, yycolumn, string.toString());
							}
	\\t						{ 
								string.append('\t');
							}
	\\n						{
								string.append('\n');
							}
	\\r						{
								string.append('\r');	
							}
	\\\"     				{
								string.append('\"');	
							}
	\\						{
								string.append('\');	
							}
	.						{
								string.append(yytext());
							}
}

<SIMPLE_QUOTE_TRIPLE_STRING> {
	\'{3}					{ 
								yybegin(YYINITIAL); 
								return new Symbol(sym.STRING, yyline, yycolumn, string.toString());
							}
	.						{
								string.append(yytext());
							}
}

<SIMPLE_QUOTE_ONCE_STRING> {
	"'"						{
								yybegin(YYINITIAL);
								return new Symbol(sym.STRING, yyline, yycolumn, string.toString());
							}
	\\t						{ 
								string.append('\t');
							}
	\\n						{
								string.append('\n');
							}
	\\r						{
								string.append('\r');	
							}
	\\\'     				{
								string.append('\"');	
							}
	\\						{
								string.append('\');	
							}
	.						{
								string.append(yytext());
							}
}

. 							{ 
								throw new ParsingException(yyline, yycolumn, "No se reconoce lexicograficamente el caracter: " + yytext());
							}

