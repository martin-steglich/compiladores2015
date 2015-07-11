package com.language.model.expression;

import com.language.exceptions.CompilerException;
import com.language.model.stack.*;
import com.language.model.type.Type;

public class IdentifierExpression extends Expression {

	String id;
    int lineNumber;

    public IdentifierExpression(String id) {
    	this.id = id;
    	//lineNumber = linenum;
	};

	public void printExp(){
		System.out.println(id);
	}

	public String getType(){
		return "Type IdentifierExpression";
	}

	public Type evaluate() throws CompilerException{
		Type t = null;
		StackHandler s = StackHandler.getInstance();
		//Stack stack = s.getStack();
		t = s.findVariable(id);
			if (t == null)
				throw new CompilerException(lineNumber, "Variable "+ id +" no definida");
	
		return t;
	}

}