package com.language.model.statement;

import com.language.exceptions.CompilerException;
import com.language.model.expression.Expression;
import com.language.model.stack.Stack;
import com.language.model.stack.StackHandler;
import com.language.model.type.NoneType;
import com.language.model.type.Type;

public class AssignStatement extends Statement{
	Expression expression;
	String id;
	int lineNumber;
	
	public AssignStatement(String id, Expression expression, int lineNumber) {
		this.expression = expression;
		this.id = id;
		this.lineNumber = lineNumber;
		
	}

	public Type execute() throws CompilerException {
		if(expression == null)
			return new NoneType();
		
		Type var = expression.evaluate();
		StackHandler s = StackHandler.getInstance();
		Stack stack = s.getStack();
		Type t = stack.findInActualScope(id);
		if(t == null)
			stack.addVariableToActualScope(id, var);
		else
			stack.replaceTypeValue(id, var);

		return var;

	}

}
