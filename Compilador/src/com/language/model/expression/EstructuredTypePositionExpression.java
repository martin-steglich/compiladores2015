package com.language.model.expression;

import java.util.Map;

import com.language.exceptions.CompilerException;
import com.language.model.stack.StackHandler;
import com.language.model.type.DictionaryType;
import com.language.model.type.Type;

public class EstructuredTypePositionExpression extends Expression{
	
	IdentifierExpression id;
	Expression position;
	int lineNumber;
	
	
	
	public EstructuredTypePositionExpression(IdentifierExpression id, Expression position) {
		this.id = id;
		this.position = position;
//		this.lineNumber = lineNumber;
	}

	@Override
	public void printExp() {	
	}

	@Override
	public Type evaluate() throws CompilerException {
		StackHandler stackHandler = StackHandler.getInstance();
		Type estructuredType = id.evaluate();
		Type pos = position.evaluate();
		if(estructuredType.getType() == 10){ //DICT
			Map<Type, Type> dictionary = ((DictionaryType)estructuredType).getDictionary();
			if(dictionary.containsKey(pos)){
				return dictionary.get(pos);
			} else
				throw new CompilerException(lineNumber, "La variable '" + id.getId() + "' no contiene la entrada '" + pos.getAsString() + "'");
		} else if(estructuredType.getType() == 6){ //LIST
			
		} else if(estructuredType.getType() == 11){ //TUPLE
			
		} else
			throw new CompilerException(lineNumber, "La variable no es un Tipo de Datos Estructurado (Diccionario, Lista, Tupla)");
			
		return pos;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "EstructuredTypePositionExpression";
	}

}
