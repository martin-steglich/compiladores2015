package com.language.model.expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.language.exceptions.CompilerException;
import com.language.model.type.TupleType;
import com.language.model.type.Type;

public class TupleExpression extends Expression{

	ArrayList<Type> tuple;
	int lineNumber;
		
	public TupleExpression(ArrayList<Expression> tuple) throws CompilerException{
		this.tuple = new ArrayList<>();
		for (Expression e : tuple)
		{
		    Type item = e.evaluate();
		    this.tuple.add(item);
		}
		this.lineNumber = lineNumber;
	}

	@Override
	public void printExp() {	
	}

	@Override
	public Type evaluate() throws CompilerException {
		TupleType tup = new TupleType(tuple);
		
		return tup;
		
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Type TupleExpression";
	}

}
