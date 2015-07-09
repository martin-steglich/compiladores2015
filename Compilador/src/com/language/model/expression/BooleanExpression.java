package com.language.model.expression;

import com.language.model.type.BooleanType;
import com.language.model.type.Type;

public class BooleanExpression extends Expression {

	boolean value;
    int lineNumber;

    public BooleanExpression(boolean value) {
    	this.value = value;
    	//lineNumber = linenum;
	};

	public void printExp(){
		if (value) {
        	System.out.println("True");
        } else {
        	System.out.println("False");
        }
	}

	public String getType(){
		return "Type BooleanExpression";
	}

	public Type evaluate(){
		Type t = new BooleanType(value);
		return t;
	}

}
