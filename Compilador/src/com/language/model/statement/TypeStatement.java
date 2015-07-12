package com.language.model.statement;

import com.language.exceptions.CompilerException;
import com.language.model.expression.Expression;
import com.language.model.type.Type;

public class TypeStatement extends Statement{
	
	Expression expression;
	int lineNumber;
	
	public TypeStatement(Expression expression) {
		this.expression = expression;
		
	}

	public Type execute() throws CompilerException {
		try {
			Type res = expression.evaluate();
			System.out.println("type \""+ res.getTypeEnum().toString() + "\"");
			
			return res;
		}catch(CompilerException e){
			throw e;
		}
		
		
	}
}