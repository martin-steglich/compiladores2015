package com.language.model.statement;

import java.util.List;

import com.language.exceptions.CompilerException;
import com.language.model.expression.Expression;
import com.language.model.type.BooleanType;

public class WhileStatement extends Statement {

	List<Statement> blockIf;
	List<Statement> blockElse;
	Expression condition;
	int lineNumber;

	public WhileStatement(Expression condition, List<Statement> block) {
		this.condition = condition;
		this.blockIf = block;
	}

	public void execute() throws CompilerException {
		BooleanType cond = (BooleanType) condition.evaluate();
		while (cond.getValue()) {
			for (Statement st : blockIf) {
				st.execute();
			}
			cond = (BooleanType) condition.evaluate();
		}

	}

}
