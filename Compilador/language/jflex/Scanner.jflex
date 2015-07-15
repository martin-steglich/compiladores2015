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
        colon = false;
        map = false;
        yybegin(INDENT_STATE);
%init}

%eofval{
		if(stack.size() > 1){
			yypushback(yylength());
          	int tmp = stack.pop();
          	System.out.println("END_BLOCK");
          	return symbol(sym.END_BLOCK);
		}
		
		return symbol(sym.EOF);
%eofval}

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
   	private boolean colon;
   	private boolean map;
%}

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
%state BLOCK_STATE
%state NORMAL_STATE
%state DOUBLE_QUOTE_ONCE_STRING
%state SIMPLE_QUOTE_ONCE_STRING
%state DOUBLE_QUOTE_TRIPLE_STRING
%state SIMPLE_QUOTE_TRIPLE_STRING

%%
<INDENT_STATE> {
	" "                        {
                                 	System.out.println("SPACE");
									current_indent = current_indent + 1;
								}
	"\t"                        {
                                 	System.out.println("TAB");
									current_indent = current_indent + TAB_LENGTH;
								}
	"\f"                        {   
									/*Ignore whitespace*/
								}
	.                         	{       
									
                                 	if (current_indent > stack.peek()){
                                 		if(!map)
                                 			throw new ParsingException(yyline, yycolumn, "Error lexicografico: Indentacion incorrecta." );
                                 		else{
                                 				yypushback(1);
                                      			yybegin(NORMAL_STATE);
                                 			}
                                     }
                                     else if (current_indent == stack.peek()){
                                     	yypushback(1);
                                      	yybegin(NORMAL_STATE);
                                     }
                                     else{
                                     	System.out.println("END_BLOCK");
                                     	yypushback(1);
                                      	int tmp = stack.pop();
                                      	return symbol(sym.END_BLOCK);
                                     }
                            	}
	{LineTerminator}        	{
									if (current_indent > stack.peek()){
                                 		System.out.println("START_BLOCK");
                                    	stack.push(current_indent);
                                       	yybegin(NORMAL_STATE);
                                      	return symbol(sym.START_BLOCK);
                                  	}
                                    else if (current_indent == stack.peek()){
                                      	yybegin(NORMAL_STATE);
                                    }
                                    else{
                                     	System.out.println("END_BLOCK");
                                     	yypushback(1);
                                      	int tmp = stack.pop();
                                      	return symbol(sym.END_BLOCK);
                                    }
                             	}
}

<BLOCK_STATE> {
	" "                        {
                                 	System.out.println("SPACE");
									current_indent = current_indent + 1;
								}
	"\t"                        {
                                 	System.out.println("TAB");
									current_indent = current_indent + TAB_LENGTH;
								}
	"\f"                        {   
									/*Ignore whitespace*/
								}
	.                         	{       
									
									yypushback(1);
                                 	if (current_indent > stack.peek()){
                                 		System.out.println("START_BLOCK");
                                    	stack.push(current_indent);
                                      	yybegin(NORMAL_STATE);
                                     	return symbol(sym.START_BLOCK);
                                     }
                                     else if (current_indent == stack.peek()){
                                      	yybegin(NORMAL_STATE);
                                     }
                                     else{
                                     	System.out.println("END_BLOCK");
                                     	int tmp = stack.pop();
                                      	return symbol(sym.END_BLOCK);
                                     }
                            	}
	{LineTerminator}        	{
									if (current_indent > stack.peek()){
                                 		System.out.println("START_BLOCK");
                                    	stack.push(current_indent);
                                       	yybegin(NORMAL_STATE);
                                      	return symbol(sym.START_BLOCK);
                                  	}
                                    else if (current_indent == stack.peek()){
                                      	yybegin(NORMAL_STATE);
                                    }
                                    else{
                                     	System.out.println("END_BLOCK");
                                     	yypushback(1);
                                      	int tmp = stack.pop();
                                      	return symbol(sym.END_BLOCK);
                                    }
                             	}
}

<NORMAL_STATE> {
  	"="             		{ 
                                System.out.println("ASSIGN");
  								return new Symbol(sym.ASSIGN, yyline, yycolumn, "="); 	
  							}
  	"+"						{ 
  								System.out.println("PLUS");
  								return new Symbol(sym.PLUS, yyline, yycolumn, yytext());
  							}
	"-"						{ 
								System.out.println("MINUS");
								return new Symbol(sym.MINUS, yyline, yycolumn, yytext());
							}
	"*"						{ 
								System.out.println("MUL");
								return new Symbol(sym.MUL, yyline, yycolumn, yytext()); 
							}
	"/"						{ 
								System.out.println("DIV");
								return new Symbol(sym.DIV, yyline, yycolumn, yytext()); 
							}
	"**"					{ 
								System.out.println("EXP");
								return new Symbol(sym.EXP, yyline, yycolumn, yytext()); 
							}
	"//"					{ 
								System.out.println("INT_DIV");
								return new Symbol(sym.INT_DIV, yyline, yycolumn, yytext()); 
							}
	"%"						{ 
								System.out.println("MOD");
								return new Symbol(sym.MOD, yyline, yycolumn, yytext()); 
							}
	"&"						{ 
								System.out.println("AND_BIT");
								return symbol(sym.AND_BIT); 
							}
	"|"						{ 
								System.out.println("OR_BIT");
								return symbol(sym.OR_BIT);
							}
	"^"						{ 
								System.out.println("XOR_BIT");
								return symbol(sym.XOR_BIT); 
							}
	"~"						{ 
								System.out.println("NOT_BIT");
								return symbol(sym.NOT_BIT); 
							}
	"<<"					{ 
								System.out.println("LSHIFT_BIT");
								return symbol(sym.LSHIFT_BIT); 
							}
	">>"					{ 
								System.out.println("RSHIFT_BIT");
								return symbol(sym.RSHIFT_BIT); 
							}
	"and"					{ 
								System.out.println("AND");
								return symbol(sym.AND);
							}
	"or"					{ 
								System.out.println("OR");
								return symbol(sym.OR);
							}
	"not"					{
								System.out.println("NOT");
	 							return symbol(sym.NOT);
	 						}
	"=="					{ 
								System.out.println("EQUALS");
								return symbol(sym.EQUALS); 
							}
	"!="					{ 
								System.out.println("NOT_EQUALS");
								return symbol(sym.NOT_EQUALS);
							}
	"<"						{ 
								System.out.println("LESS_THAN");
								return symbol(sym.LESS_THAN); 
							}
	">"						{ 
                                System.out.println("GREAT_THAN");
								return symbol(sym.GREAT_THAN);
							}
	"<="					{ 
								System.out.println("LESSEQUAL_THAN");
								return symbol(sym.LESSEQUAL_THAN); 
							}
	">="					{ 
								System.out.println("GREATEQUAL_THAN");
								return symbol(sym.GREATEQUAL_THAN); 
							}
	"."						{
								System.out.println("DOT");
								return symbol(sym.DOT);
							}
	"print"					{ 
                                System.out.println("PRINT");
								return symbol(sym.PRINT);
							}
	"raw_input"				{ 
                                System.out.println("RAW_INPUT");
								return symbol(sym.RAW_INPUT);
							}
	"type"					{ 
								System.out.println("TYPE");
								return symbol(sym.TYPE);	
							}
	"True"					{ 	
								System.out.println("TRUE");
								return symbol(sym.TRUE); 
							}
	"False"					{ 
								System.out.println("FALSE");
								return symbol(sym.FALSE); 
							}
	"if"					{ 
                                System.out.println("IF");
								return symbol(sym.IF); 	
							}
	"else"					{ 
								System.out.println("ELSE");
								return symbol(sym.ELSE); 
							}
	":"						{ 
                                System.out.println("COLON");
								return symbol(sym.COLON);
							}
	"while"					{
								System.out.println("WHILE");
								return symbol(sym.WHILE);
							}
	"for"					{
								System.out.println("FOR");
								return symbol(sym.FOR);
							}
	"in"					{
								System.out.println("IN");
								return symbol(sym.IN);
							}
	"break"					{
								System.out.println("BREAK");
								return symbol(sym.BREAK);
							}
	"continue"					{
								System.out.println("CONTINUE");
								return symbol(sym.CONTINUE);
							}
	"return"				{
								System.out.println("RETURN");
								return symbol(sym.RETURN);
							}
	"def"					{ 
								System.out.println("DEF");
								return symbol(sym.DEF); 
							}
	"("						{ 
								System.out.println("LEFTPARENTHESE");
								return symbol(sym.LEFTPARENTHESE); 
							}  
	")"						{ 
								System.out.println("RIGHTPARENTHESE");
								return symbol(sym.RIGHTPARENTHESE); 
							}
	"{" 					{
								System.out.println("LEFTLLAVE");
								map = true;
								return symbol(sym.LEFTLLAVE);
							}
	"}" 					{
								System.out.println("RIGHTLLAVE");
								map = false;
								return symbol(sym.RIGHTLLAVE);
							}
	"[" 					{
								System.out.println("LEFTRECT");
								return symbol(sym.LEFTRECT);
							}
	"]" 					{
								System.out.println("RIGHTRECT");
								return symbol(sym.RIGHTRECT);
							}
	";" 					{ 
								System.out.println("SEMICOLON");
								return symbol(sym.SEMICOLON); 
							} 
	","						{ 
								System.out.println("COMMA");
								return symbol(sym.COMMA); 
							}

	/* numeros */
	{integer} 				{ 
                                System.out.println("INTEGER");
								return symbol(sym.INTEGER, yytext());
							}
  	{long}        			{ 
  								System.out.println("LONG");
  								return new Symbol(sym.LONG, yyline, yycolumn, yytext()); 
  							}
  	{float}					{ 
  								System.out.println("FLOAT");
  								return new Symbol(sym.FLOAT, yyline, yycolumn, yytext()); 
  							}
  	
  	/* comentarios */
  	{comment}				{ /* ignore */ }
  	/* espacios */
	{WhiteSpace}			{ /* ignore */ }
	/* identificadores */
	{identifier}			{ 
								System.out.println("ID");
								return new Symbol(sym.ID, yyline, yycolumn, yytext()); 
							}
	
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
                                System.out.println("NEWLINE");
								yybegin(INDENT_STATE);
                                current_indent = 0;
                                return symbol(sym.NEWLINE);
                            }
	":"{WhiteSpace}*{LineTerminator}        {   if(!colon){
													colon = true;
													System.out.println("COLON");
													yypushback(yylength());
													return symbol(sym.COLON);
												}
					    						colon = false;
				                                System.out.println("NEWLINE");
												yybegin(BLOCK_STATE);
				                                current_indent = 0;
				                                return symbol(sym.NEWLINE);
				                            }
}

<SIMPLE_QUOTE_TRIPLE_STRING> {
	'{3}					{ 
                                System.out.println("STRING");
								yybegin(NORMAL_STATE); 
								return new Symbol(sym.STRING, yyline, yycolumn, string.toString());
							}
	[^'{3}]					{
								string.append(yytext());
							}
}

<SIMPLE_QUOTE_ONCE_STRING> {
	'						{
                                System.out.println("STRING");
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
                                System.out.println("STRING");
								yybegin(NORMAL_STATE); 
								return new Symbol(sym.STRING, yyline, yycolumn, string.toString());
							}
	[^\"{3}]					{
								string.append(yytext());
							}
}

<DOUBLE_QUOTE_ONCE_STRING> {
	\"						{
                                System.out.println("STRING");
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

