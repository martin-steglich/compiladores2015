package com.language.model.statement;

import com.language.exceptions.CompilerException;
import com.language.model.expression.Expression;
import com.language.model.type.Type;

public class ExpressionStatement extends Statement {

	Expression expression;
	int lineNumber;
	
	public ExpressionStatement(Expression expression, int lineNumber) {
		this.expression = expression;
		this.lineNumber = lineNumber;
	}
	
	public Type execute() throws CompilerException {
		return expression.evaluate();
	}
}
