package com.language.model.statement;

import com.language.model.expression.Expression;

public class PrintStatement extends Statement{
	
	Expression expression;
	int lineNumber;
	
	
	
	

	public PrintStatement(Expression expression) {
		this.expression = expression;
		
	}

	public void execute() {
		expression.printExp();
		
	}
}
