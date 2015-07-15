package com.language.model.expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.language.exceptions.CompilerException;
import com.language.model.type.TupleType;
import com.language.model.type.Type;

public class TupleExpression extends Expression{

	ArrayList<Expression> tuple;
	int lineNumber;
		
	public TupleExpression(ArrayList<Expression> tuple, int lineNumber) throws CompilerException{
		this.tuple = tuple;
		this.lineNumber = lineNumber;
	}

	@Override
	public void printExp() {	
	}

	@Override
	public Type evaluate() throws CompilerException {
		ArrayList<Type> tup = new ArrayList<>();
		for (Expression e : tuple)
		{
		    Type item = e.evaluate();
		    tup.add(item);
		}
		TupleType tupleType = new TupleType(tup);
		
		return tupleType;
		
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Type TupleExpression";
	}

}
