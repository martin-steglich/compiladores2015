package com.language.model.statement;

import java.util.List;

import com.language.exceptions.CompilerException;
import com.language.model.expression.Expression;
import com.language.model.type.BooleanType;
import com.language.model.type.Type;

public class IfStatement extends Statement{
	
	List<Statement> blockIf;
	List<Statement> blockElse;
	Expression condition;
	int lineNumber;
	
	public IfStatement(Expression condition, List<Statement> blockIf, List<Statement> blockElse) {
		this.condition = condition;
		this.blockIf = blockIf;
		this.blockElse = blockElse;
		
	}

	public Type execute() throws CompilerException {
		BooleanType cond = (BooleanType)condition.evaluate();
		Type ret = null;
		if(cond.getValue()){
			for(Statement st : blockIf){
				ret = st.execute();
			}
		}else if(blockElse != null){
			for(Statement st: blockElse){
				ret = st.execute();
			}
		}
		
		return ret;
	}
}