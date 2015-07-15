package com.language.model.statement;

import com.language.exceptions.CompilerException;
import com.language.model.type.Type;
import com.language.model.expression.Expression;

public class FunctionCallStatement extends Statement {

	Expression functionCall;
	int lineNumber;
	
	public FunctionCallStatement(Expression functionCall, int lineNumber) {
		this.functionCall = functionCall;
		this.lineNumber = lineNumber;
	}
	
	public void printSent() {
		functionCall.printExp();
	}
	
	public String getType() {
		return "Function Call Statement";
	}
	
	@Override
	public Type execute() throws CompilerException {
		return functionCall.evaluate();
	}
}
