package com.language.model.expression;

import java.util.List;

import com.language.exceptions.CompilerException;
import com.language.model.statement.*;
import com.language.model.stack.*;
import com.language.model.type.*;
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
		 return "Llamada A Funcion";
	 }
	
	public Type evaluate() throws CompilerException {
		Type typeRet = null;
		
		StackHandler s = StackHandler.getInstance();
		Stack stack = s.getStack();
		stack.openScope();
		
		FunctionStatement fun = s.findFunction(funcName.getId());
		
		if (fun!=null){
			if (params != null){
				int countArgumentsDef = fun.getParams().size();
		        int countArgumentsCall = params.size();
		        if (countArgumentsDef != countArgumentsCall){
		        	throw new CompilerException(lineNumber, "La funcion tiene cantidad incorrecta de parametros");
		        }
		        else{
		        	for (int i=0; i<countArgumentsCall; i++){
		                String var = fun.getParams().get(i).getId();
		                Expression e = params.get(i);
		                Type exp = e.evaluate(); //evaluo los parametros de la llamada
		                stack.addVariableToActualScope(var, exp);
		            }
		        	BlockFunctionStatement block = new BlockFunctionStatement(fun.getFuncBody(), lineNumber);
		        	typeRet = block.execute();
		        }
			}
			else{
				BlockFunctionStatement block = new BlockFunctionStatement(fun.getFuncBody(), lineNumber);
	        	typeRet = block.execute();
			}
			if(typeRet instanceof FunctionType){
				FunctionType aux = (FunctionType)typeRet;
				typeRet = aux.getValue();
			}
		} 
		else{
	        throw new CompilerException(lineNumber, "La funcion " + funcName.getId() +" no esta definida");
		}
		stack.closeScope();
		
		return typeRet;
	}
}
