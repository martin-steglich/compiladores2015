package com.language;

import com.language.parser.ProgramParser;

public class Python {

	public static void main(String[] args) {
		
		if((args == null) || (args.length == 0)){
			
			ProgramParser.parse("resources/LetraTest.py");
			ProgramParser.parse("resources/OppAritmeticosTest.py");
			ProgramParser.parse("resources/OppAritmeticosTest2.py");
			ProgramParser.parse("resources/OppsBitwiseTest.py");
			ProgramParser.parse("resources/OppBooleanosTest.py");
			ProgramParser.parse("resources/OppRelacionalesTest.py");
			ProgramParser.parse("resources/ListTest.py");
			ProgramParser.parse("resources/MapTest.py");
			ProgramParser.parse("resources/TupleTest.py");
			ProgramParser.parse("resources/StringTest.py");
			ProgramParser.parse("resources/IfTest.py");
			ProgramParser.parse("resources/WhileTest.py");
			ProgramParser.parse("resources/BreakTest.py");
			ProgramParser.parse("resources/ContinueTest.py");
			ProgramParser.parse("resources/ForTest.py");
			ProgramParser.parse("resources/FunctionCallByReferenceTest.py");
			ProgramParser.parse("resources/PreFunctionsDicTest.py");
			ProgramParser.parse("resources/PreFunctionsStringTest.py");
			ProgramParser.parse("resources/PreFunctionsListTest.py");
			ProgramParser.parse("resources/EntradaSalida.py");
			ProgramParser.parse("resources/CastFunctionsTest.py");
			

		}else{
			for (String fileName : args) {
				ProgramParser.parse(fileName);	
			}
		}
	}
	
}