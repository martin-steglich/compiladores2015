package com.language.model.statement;

import java.util.List;

import com.language.exceptions.CompilerException;
import com.language.model.expression.Expression;
import com.language.model.type.BooleanType;
import com.language.model.type.BreakType;
import com.language.model.type.ContinueType;
import com.language.model.type.Type;

public class WhileStatement extends Statement {

	List<Statement> block;
	Expression condition;
	int lineNumber;

	public WhileStatement(Expression condition, List<Statement> block) {
		this.condition = condition;
		this.block = block;
	}

	public Type execute() throws CompilerException {
		Type cond = condition.evaluate();
		int type = cond.getType();
		boolean conditionBool = false;
		if((type == 0) || (type == 1) || (type == 2) || (type == 3) || (type == 5)) 
			conditionBool = cond.getBooleanValue();
		else
			throw new CompilerException(lineNumber, "Tipo de datos no soportado para condicion de While");

		Type ret = null;
		while (conditionBool) {
			for (Statement st : block) {
				ret = st.execute();
				if((ret instanceof BreakType) || (ret instanceof ContinueType))
					break;
			}
			if(ret instanceof BreakType)
				break;
			
			cond = (BooleanType) condition.evaluate();
			type = cond.getType();
			conditionBool = false;
			if((type == 0) || (type == 1) || (type == 2) || (type == 3) || (type == 5)) 
				conditionBool = cond.getBooleanValue();
			else
				throw new CompilerException(lineNumber, "Tipo de datos no soportado para condicion de While");

			if(ret instanceof ContinueType)
				continue;
			
		}
		
		return ret;
	}

}
