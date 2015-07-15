package com.language.model.statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.language.exceptions.CompilerException;
import com.language.model.expression.Expression;
import com.language.model.expression.IdentifierExpression;
import com.language.model.stack.StackHandler;
import com.language.model.type.BreakType;
import com.language.model.type.ContinueType;
import com.language.model.type.DictionaryType;
import com.language.model.type.ListType;
import com.language.model.type.StringType;
import com.language.model.type.Type;

public class ForStatement extends Statement {

	List<Statement> block;
	String var;
	Expression iterable;
	int lineNumber;

	public ForStatement(String var, Expression iterable, List<Statement> block, int lineNumber){
		this.iterable = iterable;
		this.var = var;
		this.block = block;
		this.lineNumber = lineNumber;
	}

	public Type execute() throws CompilerException {
		Type iterableType = iterable.evaluate();
		if((iterableType.getType() != 3) && (iterableType.getType() != 6) && (iterableType.getType() != 10) && (iterableType.getType() != 11))
				throw new CompilerException(lineNumber, "Tipo de datos no soportado para el For");
		
		StackHandler stack = StackHandler.getInstance();
		//Stack stack = s.getStack();
		Type ret = null;
		if(iterableType.getType() == 3){
			String s = ((StringType)iterableType).getText();
			int length = s.length();
			for(int i = 0; i < length; i++){
				
				String actualChar = String.valueOf(s.charAt(i));
				stack.addVariable(var, new StringType(actualChar));
				
				for(Statement st : block){
					ret = st.execute();
					if((ret instanceof BreakType) || (ret instanceof ContinueType))
						break;
				}
				if(ret instanceof BreakType)
					break;
			}
		}else if (iterableType.getType() == 6){
			ArrayList<Type> s = ((ListType)iterableType).getList();
			for(Type t : s){
				
				stack.addVariable(var, t);
				
				for(Statement st : block){
					ret = st.execute();
					if((ret instanceof BreakType) || (ret instanceof ContinueType))
						break;
				}
				if(ret instanceof BreakType)
					break;
			}
			
		}else if (iterableType.getType() == 10){
			Map<Type,Type> s = ((DictionaryType)iterableType).getDictionary();
			
			for(Entry<Type, Type> entry : s.entrySet()){
				
				stack.addVariable(var, entry.getKey());
				
				for(Statement st : block){
					ret = st.execute();
					if((ret instanceof BreakType) || (ret instanceof ContinueType))
						break;
				}
				if(ret instanceof BreakType)
					break;
			}			
		}else if (iterableType.getType() == 11){
			ArrayList<Type> s = ((ListType)iterableType).getList();
			for(Type t : s){
				
				stack.addVariable(var, t);
				
				for(Statement st : block){
					ret = st.execute();
					if((ret instanceof BreakType) || (ret instanceof ContinueType))
						break;
				}
				if(ret instanceof BreakType)
					break;
			}
		}

		
		return ret;
	}

}
