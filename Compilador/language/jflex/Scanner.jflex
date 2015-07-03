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
/*
%eofval{
    return symbol(sym.EOF);
%eofval}*/

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
  	"="             		{ return new Symbol(sym.ASSIGN, yyline, yycolumn, "="); }
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
	{Identifier}			{ return new Symbol(sym.ID, yyline, yycolumn, yytext()); }
}
s
. 							{ 
								throw new ParsingException(yyline, yycolumn, "No se reconoce lexicograficamente el caracter: " + yytext());
							}

