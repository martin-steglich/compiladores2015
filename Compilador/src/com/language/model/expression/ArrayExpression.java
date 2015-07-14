package com.language.model.expression;

import java.util.ArrayList;
import java.util.List;

import com.language.exceptions.CompilerException;
import com.language.model.type.ArrayType;
import com.language.model.type.TupleType;
import com.language.model.type.Type;

public class ArrayExpression  extends Expression {
	ArrayList<Type> elements;
	int lineNumber;
	
	public ArrayExpression(ArrayList<Expression> elements) throws CompilerException {
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
		ArrayType arr = new ArrayType(elements);
		
		return arr;
	}
	
	

	



	
}
