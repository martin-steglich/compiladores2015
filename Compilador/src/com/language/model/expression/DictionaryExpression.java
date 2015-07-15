package com.language.model.expression;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.language.exceptions.CompilerException;
import com.language.model.type.DictionaryType;
import com.language.model.type.Type;

public class DictionaryExpression extends Expression{
	
	Map<Expression, Expression> dictionary;
	int lineNumber;
		
	public DictionaryExpression(Map<Expression, Expression> dict, int lineNumber) throws CompilerException {
		
		this.dictionary = dict;
		this.lineNumber = lineNumber;
	}

	@Override
	public void printExp() {	
	}

	@Override
	public Type evaluate() throws CompilerException {
		Map<Type, Type> dict = new HashMap<>();
		for (Entry<Expression, Expression> entry : dictionary.entrySet())
		{
		    Type key = entry.getKey().evaluate();
		    Type value = entry.getValue().evaluate();
		    
		    if((key.getType() == 4) || (key.getType() > 5)){ 		    	
		    	throw new CompilerException(lineNumber, "Tipos de datos no soportado para la llave de un Diccionario");
		    }
		    if ((value.getType() == 4 ) || (value.getType() == 7) || (value.getType() == 8) || (value.getType() == 9))
		    	throw new CompilerException(lineNumber, "Tipos de datos no soportado para el valor de un Diccionario");
		    
		    dict.put(key, value);
		}
		
		
		DictionaryType dictionaryType = new DictionaryType(dict);
		
		return dictionaryType;
		
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Type DictionaryExpression";
	}

}
