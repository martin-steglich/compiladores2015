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
		Type cond = condition.evaluate();
		int type = cond.getType();
		boolean conditionBool = false;
		if((type == 0) || (type == 1) || (type == 2) || (type == 3) || (type == 5)) 
			conditionBool = cond.getBooleanValue();
		else
			throw new CompilerException(lineNumber, "Tipo de datos no soportado para condicion de If");
		Type ret = null;
		if(conditionBool){
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