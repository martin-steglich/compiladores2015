package com.language.model.statement;

import java.util.List;

import com.language.exceptions.CompilerException;
import com.language.model.statement.BlockStatement;
import com.language.model.type.*;
import com.language.model.statement.Statement;

public class BlockForStatement extends Statement {

	List<Statement> statements;
	Type ret;
	int lineNumber;
	boolean hasBreak = false;
	
	public BlockForStatement(BlockStatement bStatement, int lineNumber) {
		statements = bStatement.getStatements();
		ret = null;
		this.lineNumber = lineNumber;
	}
	
	public String getType() {
		return "Statement Block For";
	}
	
	public boolean getHasBreak(){
		return hasBreak;
	}
	
	public Type execute() throws CompilerException {
		Type ret = null;
		for (Statement statement : statements) {
			ret = statement.execute();
			 if(ret instanceof ContinueType ||ret instanceof BreakType ||ret instanceof FunctionType){
				 break;
			 }
		}
		return ret;
	}
}
