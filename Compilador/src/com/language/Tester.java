package com.language;

import com.language.exceptions.CompilerException;
import com.language.model.program.Program;
import com.language.parser.ProgramParser;

public class Tester {

	public static void main(String[] args) {
		
		String exptext = "print 3 + 2";
		
		Program program = ProgramParser.parse(exptext);
		
		try {
			program.execute();
		} catch (CompilerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
}