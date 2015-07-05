package com.language;

import com.language.model.expression.Expression;
import com.language.model.statement.Statement;
import com.language.parser.ExpressionParser;

public class Tester {

	public static void main(String[] args) {
		
		String exptext = "3";
		
		Statement exp = ExpressionParser.parse(exptext);
		//System.out.println(exp.toString());

		
	}
	
}
