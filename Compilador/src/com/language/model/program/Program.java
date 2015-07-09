package com.language.model.program;

import java.util.Iterator;
import java.util.List;

import com.language.exceptions.CompilerException;
import com.language.model.statement.Statement;

public class Program {
	List<Statement> statements;
    
    public Program(List<Statement> statements){
            this.statements = statements;
    }
    
    public void execute() throws CompilerException{
    	
    	try {
    		Iterator<Statement> iterator = statements.iterator();
    		while(iterator.hasNext()){ 
    			iterator.next().execute();
    		}
    	}catch(CompilerException e){
			throw e;
		}
    }
}