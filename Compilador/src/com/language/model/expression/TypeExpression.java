package com.language.model.expression;

import com.language.exceptions.CompilerException;
import com.language.model.type.StringType;
import com.language.model.type.Type;
import com.language.model.type.TypeEnum;

public class TypeExpression extends Expression{
	
	Expression expression;
	int lineNumber;
	
	public TypeExpression(Expression expression, int lineNumber) {
		this.expression = expression;
		this.lineNumber = lineNumber;
		
	}

	public Type evaluate() throws CompilerException {
		try {
			Type res = expression.evaluate();
			String str = "type \""+ res.getTypeEnum().toString() + "\"";
			
			
			return new StringType(str);
		}catch(CompilerException e){
			throw e;
		}
		
		
	}

	@Override
	public void printExp() {
				
	}



	@Override
	public String getType() {
		return "type TypeExpression";
	}
}