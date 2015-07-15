package com.language.model.expression;

import com.language.exceptions.CompilerException;
import com.language.model.stack.*;
import com.language.model.type.Type;

public class IdentifierExpression extends Expression {

	String id;
    int lineNumber;

    public IdentifierExpression(String id, int lineNumber) {
    	this.id = id;
    	this.lineNumber = lineNumber;
	};

	public void printExp(){
		System.out.println(id);
	}

	public String getType(){
		return "Type IdentifierExpression";
	}
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Type evaluate() throws CompilerException{
		Type t = null;
		StackHandler s = StackHandler.getInstance();
		Stack stack = s.getStack();
		//t = stack.findInActualScope(id);
		t = stack.findTypeValue(id);	
		if (t == null)
				throw new CompilerException(lineNumber, "Variable "+ id +" no definida");
	
		return t;
	}

}