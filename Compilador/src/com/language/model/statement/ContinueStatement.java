package com.language.model.statement;

import com.language.exceptions.CompilerException;
import com.language.model.type.ContinueType;
import com.language.model.type.Type;

public class ContinueStatement extends Statement {

	
	int lineNumber;
	
	public void printSent() {
	}

	public String getType() {
		return "Sentence Break";
	}


	@Override
	public Type execute() throws CompilerException {
		return new ContinueType();
	}

}
