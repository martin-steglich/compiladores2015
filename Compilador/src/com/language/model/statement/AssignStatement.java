package com.language.model.statement;

import com.language.exceptions.CompilerException;
import com.language.model.expression.Expression;
import com.language.model.stack.StackHandler;
import com.language.model.type.Type;

public class AssignStatement extends Statement{
	Expression expression;
	String id;
	int lineNumber;
	
	public AssignStatement(String id, Expression expression) {
		this.expression = expression;
		this.id = id;
		
	}

	public void execute() throws CompilerException {
		Type var = expression.evaluate();
		StackHandler s = StackHandler.getInstance();
		//Stack stack = s.getStack();
		s.addVariable(id, var);
		
		String aux = s.findVariable(id).getAsString();
		
		System.out.println(aux);
	}

}
