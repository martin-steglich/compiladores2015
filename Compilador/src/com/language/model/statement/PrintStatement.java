package com.language.model.statement;

import com.language.exceptions.CompilerException;
import com.language.model.expression.Expression;
import com.language.model.type.*;

public class PrintStatement extends Statement{
	
	Expression expression;
	int lineNumber;
	
	public PrintStatement(Expression expression) {
		this.expression = expression;
		
	}

	public Type execute() throws CompilerException {
		try {
			Type res = expression.evaluate();
			System.out.println(res.getAsString());
			
			return res;
		}catch(CompilerException e){
			throw e;
		}
		
		
	}
}
