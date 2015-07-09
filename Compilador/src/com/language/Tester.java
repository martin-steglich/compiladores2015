package com.language;

import com.language.exceptions.CompilerException;
import com.language.model.statement.Statement;
import com.language.parser.ExpressionParser;

public class Tester {

	public static void main(String[] args) {
		
		String exptext = "print 2L";
		
		Statement exp = ExpressionParser.parse(exptext);
		try {
			exp.execute();
		} catch (CompilerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
}
