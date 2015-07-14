package com.language.model.expression;

import java.util.ArrayList;


import com.language.exceptions.CompilerException;
import com.language.model.type.ListType;
import com.language.model.type.Type;

public class ListExpression  extends Expression {
	ArrayList<Type> elements;
	int lineNumber;
	
	public ListExpression(ArrayList<Expression> elements, int lineNumber) throws CompilerException {
		this.elements = new ArrayList<>();
		for (Expression e : elements)
		{
		    Type item = e.evaluate();
		    this.elements.add(item);
		}
		this.lineNumber = lineNumber;
	}

	@Override
	public void printExp() {
		//
	}
	
	@Override
	public String getType() {
		return "Type ArrayExpression";
	}
	
	@Override
	public Type evaluate() {
		ListType arr = new ListType(elements);
		
		return arr;
	}
	
	

	



	
}
