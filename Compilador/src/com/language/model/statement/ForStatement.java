package com.language.model.statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.language.exceptions.CompilerException;
import com.language.model.expression.Expression;
import com.language.model.type.BreakType;
import com.language.model.type.ContinueType;
import com.language.model.type.DictionaryType;
import com.language.model.type.ListType;
import com.language.model.type.StringType;
import com.language.model.type.Type;

public class ForStatement extends Statement {

	List<Statement> block;
	Type list;
	int lineNumber;

	public ForStatement(Expression list, List<Statement> block, int lineNumber) throws CompilerException{
		Type listType = list.evaluate();
		if((listType.getType() != 3) || (listType.getType() != 6) || (listType.getType() != 10) || (listType.getType() != 11))
				throw new CompilerException(lineNumber, "El tipo de datos no soportado para el For");
		
		this.list = listType;
		this.block = block;
		this.lineNumber = lineNumber;
	}

	public Type execute() throws CompilerException {
		Type ret = null;
		if(list.getType() == 3){
			String s = ((StringType)list).getText();
			int length = s.length();
			for(int i = 0; i < length; i++){
				for(Statement st : block){
					ret = st.execute();
					if((ret instanceof BreakType) || (ret instanceof ContinueType))
						break;
				}
				if(ret instanceof BreakType)
					break;
			}
		}else if (list.getType() == 6){
			ArrayList<Type> s = ((ListType)list).getList();
			for(Type t : s){
				for(Statement st : block){
					ret = st.execute();
					if((ret instanceof BreakType) || (ret instanceof ContinueType))
						break;
				}
				if(ret instanceof BreakType)
					break;
			}
			
		}else if (list.getType() == 10){
			Map<Type,Type> s = ((DictionaryType)list).getDictionary();
			int length = s.size();
			for(int i = 0; i < length; i++){
				for(Statement st : block){
					ret = st.execute();
					if((ret instanceof BreakType) || (ret instanceof ContinueType))
						break;
				}
				if(ret instanceof BreakType)
					break;
			}			
		}else if (list.getType() == 11){
			ArrayList<Type> s = ((ListType)list).getList();
			for(Type t : s){
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
