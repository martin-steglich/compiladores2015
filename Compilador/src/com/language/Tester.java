package com.language;

import com.language.model.Program;
import com.language.parser.ExpressionParser;

public class Tester {

	public static void main(String[] args) {
		
		String exptext = "c = 3";
		
		Program expobj = ExpressionParser.parse(exptext);
		
	}
	
}
