package com.language.parser;

import java.util.*;
import java_cup.runtime.*;
import java.util.Stack;
import com.language.exceptions.*;
import com.language.model.expression.*;

%%

%cup
%line
%unicode
%column

%class Scanner

%init{
        this.stack.push(0);
        current_indent = 0;
        yybegin(INDENT_STATE);
%init}

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
	
	private static final int TAB_LENGTH = 4;
	
	Stack<Integer> stack = new Stack<Integer>();
   	private int current_indent;
%}

%eofval{
    return symbol(sym.EOF);
%eofval}

LineTerminator  		= \r|\n|\r\n
WhiteSpace      		= [ \t\f]
digit                   = [0-9]
integer                 = {digit}+
long 					= {integer}[L]
float                   = {pointfloat} | {exponentfloat}
pointfloat      		= {intpart}? {fraction} | {intpart} "."
exponentfloat   		= ( {intpart} | {pointfloat} ) {exponent}
intpart                 = {integer}
fraction                = "." {integer}
exponent                = ( "e" | "E" ) [ "+" | "-" ] {integer}
letter                  = {lowercase} | {uppercase}
lowercase               = [a-z]
uppercase               = [A-Z]
identifier              = ({letter}|"_") ({letter} | {digit} | "_")*
comment                 = #[^\n]*

/* definicion de estados */
/*
DOUBLE_QUOTE_ONCE_STRING: "ESTO"
SIMPLE_QUOTE_ONCE_STRING: 'ES'
DOUBLE_QUOTE_TRIPLE_STRING: """UN"""
SIMPLE_QUOTE_TRIPLE_STRING: '''EJEMPLO'''
*/
%state INDENT_STATE
%state NORMAL_STATE
%state DOUBLE_QUOTE_ONCE_STRING
%state SIMPLE_QUOTE_ONCE_STRING
%state DOUBLE_QUOTE_TRIPLE_STRING
%state SIMPLE_QUOTE_TRIPLE_STRING

%%

<INDENT_STATE> {

	" "                         {       
									current_indent++;
								}
	"\t"                        {       
									current_indent = current_indent + TAB_LENGTH;
								}
	"\f"                        {   
									/*Ignore whitespace*/
								}
	.                         	{       
									yypushback(1);
                                 	if (current_indent > stack.peek()){
                                    	stack.push(current_indent);
                                      	yybegin(NORMAL_STATE);
                                     	return symbol(sym.START_BLOCK);
                                     }
                                     else if (current_indent == stack.peek()){
                                      	yybegin(NORMAL_STATE);
                                     }
                                     else{
                                     	int tmp = stack.pop();
                                      	return symbol(sym.END_BLOCK);
                                     }
                            	}
	{LineTerminator}        	{
									if (current_indent > stack.peek()){
                                    	stack.push(current_indent);
                                       	yybegin(NORMAL_STATE);
                                      	return symbol(sym.START_BLOCK);
                                  	}
                                    else if (current_indent == stack.peek()){
                                      	yybegin(NORMAL_STATE);
                                    }
                                    else{
                                     	yypushback(1);
                                      	int tmp = stack.pop();
                                      	return symbol(sym.END_BLOCK);
                                    }
                             	}
}

<NORMAL_STATE> {
  	"="             		{ return new Symbol(sym.ASSIGN, yyline, yycolumn, "="); }
  	"+"						{ return new Symbol(sym.PLUS, yyline, yycolumn, yytext()); }
	"-"						{ return new Symbol(sym.MINUS, yyline, yycolumn, yytext()); }
	"*"						{ return new Symbol(sym.MUL, yyline, yycolumn, yytext()); }
	"/"						{ return new Symbol(sym.DIV, yyline, yycolumn, yytext()); }
	"**"					{ return new Symbol(sym.EXP, yyline, yycolumn, yytext()); }
	"//"					{ return new Symbol(sym.INT_DIV, yyline, yycolumn, yytext()); }
	"%"						{ return new Symbol(sym.MOD, yyline, yycolumn, yytext()); }
	"&"						{ return symbol(sym.AND_BIT); }
	"|"						{ return symbol(sym.OR_BIT); }
	"^"						{ return symbol(sym.XOR_BIT); }
	"~"						{ return symbol(sym.NOT_BIT); }
	"<<"					{ return symbol(sym.LSHIFT_BIT); }
	">>"					{ return symbol(sym.RSHIFT_BIT); }
	"and"					{ return symbol(sym.AND); }
	"or"					{ return symbol(sym.OR); }
	"not"					{ return symbol(sym.NOT); }
	"=="					{ return symbol(sym.EQUALS); }
	"!="					{ return symbol(sym.NOT_EQUALS); }
	"<"						{ return symbol(sym.LESS_THAN); }
	">"						{ return symbol(sym.GREAT_THAN); }
	"<="					{ return symbol(sym.LESSEQUAL_THAN); }
	">="					{ return symbol(sym.GREATEQUAL_THAN); }
	"print"					{ return symbol(sym.PRINT); }
	"type"					{ return symbol(sym.TYPE);	}
	"True"					{ return symbol(sym.TRUE); }
	"False"					{ return symbol(sym.FALSE); }
	"if"					{ return symbol(sym.IF); }
	"else"					{ return symbol(sym.ELSE); }
	":"						{ return symbol(sym.COLON); }
	"def"					{ return symbol(sym.DEF); }
	"("						{ return symbol(sym.LEFTPARENTHESE); }  
	")"						{ return symbol(sym.RIGHTPARENTHESE); } 
	";" 					{ return symbol(sym.SEMICOLON); } 
	","						{ return symbol(sym.COMMA); }

	/* numeros */
	{integer} 				{ return symbol(sym.INTEGER, yytext());}
  	{long}        			{ return new Symbol(sym.LONG, yyline, yycolumn, yytext()); }
  	{float}					{ return new Symbol(sym.FLOAT, yyline, yycolumn, yytext()); }
  	
  	/* comentarios */
  	{comment}				{ /* ignore */ }
  	/* espacios */
	{WhiteSpace}			{ /* ignore */ }
	/* identificadores */
	{identifier}			{ return new Symbol(sym.ID, yyline, yycolumn, yytext()); }
	
	/* string */
	'{3}					{ 
								string.setLength(0);
								yybegin(SIMPLE_QUOTE_TRIPLE_STRING);
							}
	'						{ 
								string.setLength(0);
								yybegin(SIMPLE_QUOTE_ONCE_STRING);
							}
	\"{3}					{ 
								string.setLength(0);
								yybegin(DOUBLE_QUOTE_TRIPLE_STRING);
							}
	\"						{ 
								string.setLength(0);
								yybegin(DOUBLE_QUOTE_ONCE_STRING);
							}
							
	{LineTerminator}        {       
								yybegin(INDENT_STATE);
                                current_indent = 0;
                                return symbol(sym.NEWLINE);
                            }
}

<SIMPLE_QUOTE_TRIPLE_STRING> {
	'{3}					{ 
								yybegin(NORMAL_STATE); 
								return new Symbol(sym.STRING, yyline, yycolumn, string.toString());
							}
	[^'{3}]					{
								string.append(yytext());
							}
}

<SIMPLE_QUOTE_ONCE_STRING> {
	'						{
								yybegin(NORMAL_STATE);
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
								string.append('\'');	
							}
	\\						{
								string.append('\\');	
							}
	.						{
								string.append(yytext());
							}
}

<DOUBLE_QUOTE_TRIPLE_STRING> {
	\"{3}					{ 
								yybegin(NORMAL_STATE); 
								return new Symbol(sym.STRING, yyline, yycolumn, string.toString());
							}
	[^\"{3}]					{
								string.append(yytext());
							}
}

<DOUBLE_QUOTE_ONCE_STRING> {
	\"						{
								yybegin(NORMAL_STATE);
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
								string.append('\\');	
							}
	.						{
								string.append(yytext());
							}	
}

. 							{ 
								throw new ParsingException(yyline, yycolumn, "No se reconoce lexicograficamente el caracter: " + yytext());
							}

