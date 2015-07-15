package com.language.model.statement;

import java.util.List;

import com.language.exceptions.CompilerException;
import com.language.model.stack.StackHandler;
import com.language.model.type.Type;
import com.language.model.expression.IdentifierExpression;

public class FunctionStatement extends Statement {

	IdentifierExpression funcName;
	List<IdentifierExpression> params;
	BlockStatement funcBody;
	int lineNumber;
	
	public FunctionStatement(IdentifierExpression funcName, List<IdentifierExpression> params, BlockStatement funcBody) {
		this.funcName = funcName;
		this.params = params;
		this.funcBody = funcBody;
	}
	
	public IdentifierExpression getFuncName() {
		return funcName;
	}
	
	public List<IdentifierExpression> getParams() {
		return params;
	}
	
	public BlockStatement getFuncBody() {
		return funcBody;
	}
	
	public int getLineNumber() {
		return lineNumber;
	}
	
	public Type execute() throws CompilerException {
		StackHandler stackHandler = StackHandler.getInstance();
		stackHandler.addFunction(funcName.getId(), this);
		return null;
	}
}
