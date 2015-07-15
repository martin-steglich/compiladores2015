package com.language.model.expression;

import java.util.List;

import com.language.exceptions.CompilerException;
import com.language.model.expression.Expression;
import com.language.model.type.Type;


public class MethodCallExpression extends Expression {

	IdentifierExpression varName;
	String method;
	List<Expression> parameters;
	int lineNumber;
	
	public MethodCallExpression(IdentifierExpression id, String methodName, List<Expression> params, int linenum) {
		lineNumber = linenum;
		parameters = params;
		varName = id;
		method = methodName;
	}
	
	public void printExp() {
	}

	public String getType() {
		return "Llamada a Metodo";
	}
	
	public Type evaluate() throws CompilerException {
		Type typeRet = null;
		return typeRet;
	}
}
