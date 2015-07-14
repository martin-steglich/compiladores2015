package com.language.model.expression;

import java.util.List;

import com.language.exceptions.CompilerException;
import com.language.model.type.Type;
import com.language.model.expression.Expression;

public class FunctionCallExpression extends Expression {

	IdentifierExpression funcName;
	List<Expression> params;
	int lineNumber;
	
	public FunctionCallExpression(IdentifierExpression funcName,List<Expression> params, int linenum){
		this.funcName = funcName;
		this.params = params;
		this.lineNumber = linenum;
	}
	
	public void printExp(){
	    System.out.println("LLAMADA DE FUNCION ");
	    funcName.printExp();
		System.out.println("LLAMADA CON ESTOS PARAMETROS");
	    for(Expression e : params){
	    	e.printExp();
	    }	
	 }
	
	public String getType(){
		 return "LlamadaAFuncion";
	 }
	
	public Type evaluate() throws CompilerException {
		Type typeRet = null;
		
		return typeRet;
	}
}
