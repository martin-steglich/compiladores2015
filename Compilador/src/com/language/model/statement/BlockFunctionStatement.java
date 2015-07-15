package com.language.model.statement;

import java.util.List;

import com.language.exceptions.CompilerException;
import com.language.model.type.*;

public class BlockFunctionStatement extends Statement{

	List<Statement> statements;
	Type ret;
	int lineNumber;
	
	public BlockFunctionStatement(BlockStatement bStatement, int lineNumber) {
		statements = bStatement.getStatements();
		ret = null;
		this.lineNumber = lineNumber;
	}
	
	public String getType() {
		return "Statement Block Function";
	}
	
	public Type execute() throws CompilerException {
		Type ret = null;
		boolean hasReturn = false;
		for (Statement statement : statements) {
			ret = statement.execute();
			 if(ret instanceof FunctionType){
				 hasReturn = true;
				 break;
			 }
		}
		if(!hasReturn)
			ret = null;
		return ret;
	}
	
}
