package com.language;

import com.language.parser.ProgramParser;

public class Python {

	public static void main(String[] args) {
		
		if((args == null) || (args.length == 0)){
			//ProgramParser.parse("resources/Simple.py");
			//ProgramParser.parse("resources/WhileTest.py");
			//ProgramParser.parse("resources/Simple2.py");
			//ProgramParser.parse("resources/TestIf.py");
			//ProgramParser.parse("resources/StructuredTypesTest.py");
			//ProgramParser.parse("resources/EntradaSalida.py");
			ProgramParser.parse("resources/FunctionTest.py");
			//ProgramParser.parse("resources/ForStatement.py");
			//ProgramParser.parse("resources/PredefinedFunctions.py");
			//ProgramParser.parse("resources/ForStatement.py");
			ProgramParser.parse("resources/FunctionCallByReferenceTest.py");
		}else{
			for (String fileName : args) {
				ProgramParser.parse(fileName);	
			}
		}
	}
	
}