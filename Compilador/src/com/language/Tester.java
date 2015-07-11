package com.language;

import com.language.exceptions.CompilerException;
import com.language.model.program.Program;
import com.language.parser.ProgramParser;

public class Tester {

	public static void main(String[] args) {
		ProgramParser.parse("Simple.py");
		//ProgramParser.parse("WhileTest.py");
		//ProgramParser.parse("Simple2.py");
		//ProgramParser.parse("TestIf.py");
	}
	
}