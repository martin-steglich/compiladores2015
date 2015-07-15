package com.language.model.expression;

import java.util.ArrayList;


import com.language.exceptions.CompilerException;
import com.language.model.type.ListType;
import com.language.model.type.Type;

public class ListExpression  extends Expression {
	ArrayList<Expression> elements;
	int lineNumber;
	
	public ListExpression(ArrayList<Expression> elements, int lineNumber) throws CompilerException {
		this.elements = elements;
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
	public Type evaluate() throws CompilerException {
		ArrayList<Type> elems = new ArrayList<>();
		for (Expression e : elements)
		{
		    Type item = e.evaluate();
		    elems.add(item);
		}
		
		ListType arr = new ListType(elems);
		
		return arr;
	}
	
	

	



	
}
