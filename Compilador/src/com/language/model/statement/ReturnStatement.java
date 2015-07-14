package com.language.model.statement;

import java.util.ArrayList;
import java.util.List;

import com.language.exceptions.CompilerException;
import com.language.model.expression.Expression;
import com.language.model.type.*;

public class ReturnStatement extends Statement {

	List<Expression> ret;
	int lineNumber;
	Type value;
	
	public ReturnStatement(List<Expression> ret, int lineNumber) {
		this.value = null;
		this.lineNumber = lineNumber;
		this.ret = ret;
	}
	
	public String getType() {
		return "Statement Return";
	}
	
	public Type getValue() {
		return value;
	}
	
	public Type execute() throws CompilerException {
		
		FunctionReturnType tFunction = null;
		
		if(ret==null){
			ArrayList<Type> types = new ArrayList<Type>();
			types.add(new NoneType());
			tFunction = new FunctionReturnType(types);
		}
		else{
			ArrayList<Type> types = new ArrayList<Type>();
			for (Expression e : ret)
			{
			    Type item = e.evaluate();
			    types.add(item);
			}
			tFunction = new FunctionReturnType(types);
		}
		return tFunction;
	}
}
