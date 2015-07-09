package com.language.model.statement;

import com.language.model.expression.Expression;

public class AssignStatement extends Statement{
	Expression expression;
	String id;
	int lineNumber;
	
	public AssignStatement(String id, Expression expression) {
		this.expression = expression;
		this.id = id;
		
	}

	public void execute() {
		expression.printExp();
		
	}

}
