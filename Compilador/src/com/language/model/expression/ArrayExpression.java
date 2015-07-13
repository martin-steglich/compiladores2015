package com.language.model.expression;

import java.util.ArrayList;
import java.util.List;

import com.language.model.type.ArrayType;
import com.language.model.type.Type;

public class ArrayExpression {
	List<Expression> elements;

	
	public ArrayExpression(List<Expression> elements) {
		this.elements = elements;
		//this.lineNumber = lineNumber;
	}

	public void printExp() {
		//
	}

	public String getType() {
		return "Type ArrayExpression";
	}

	public Type evaluate() {
		ArrayType arr = new ArrayType();
        for (Expression e : elements) {
            Type res = e.evaluate();
            arr.assignElement(res);
        }
        return arr;
	}

	
}
