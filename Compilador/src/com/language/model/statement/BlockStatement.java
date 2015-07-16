package com.language.model.statement;

import java.util.ArrayList;
import java.util.List;

import com.language.exceptions.CompilerException;
import com.language.model.statement.Statement;
import com.language.model.type.*;
import com.language.model.stack.StackHandler;
import com.language.model.stack.Stack;

public class BlockStatement extends Statement {
	
	List<Statement> statements;
	Type ret;
	
	public BlockStatement(List<Statement> statements) {
		this.statements = statements;
		ret = null;
	}
	

	public String getType() {
		return "Statement Block";
	}
	
	public List<Statement> getStatements() {
		return statements;
	}

	public Type execute()  throws CompilerException {
		 StackHandler s = StackHandler.getInstance();
		 Stack stack= s.getStack();
		 stack.openScope();
		 Type ret=null;
		 for(Statement statement : statements){
			 ret = statement.execute();
			 if(ret instanceof ContinueType||ret instanceof BreakType||ret instanceof FunctionType){
				 break;
			 }
		 }
		 stack.closeScope();
		 return ret;
	}
	
}
