package com.language.model.statement;

import java.util.ArrayList;
import java.util.Map;

import com.language.exceptions.CompilerException;
import com.language.model.expression.Expression;
import com.language.model.expression.IdentifierExpression;
import com.language.model.stack.StackHandler;
import com.language.model.type.BooleanType;
import com.language.model.type.DictionaryType;
import com.language.model.type.IntegerType;
import com.language.model.type.ListType;
import com.language.model.type.LongType;
import com.language.model.type.Type;

public class AssignStructuredTypeStatement extends Statement {

	Expression expression;
	IdentifierExpression id;
	Expression position;
	int lineNumber;

	public AssignStructuredTypeStatement(IdentifierExpression id,Expression expression, Expression position) {
		this.expression = expression;
		this.id = id;
		this.position = position;

	}

	public Type execute() throws CompilerException {
		StackHandler stackHandler = StackHandler.getInstance();
		Type estructuredType = id.evaluate();
		Type pos = position.evaluate();
		Type value = expression.evaluate();
		
		if (estructuredType.getType() == 10) { // DICT
			if((pos.getType() != 2) && (pos.getType() != 0) && (pos.getType() != 5))
				throw new CompilerException(lineNumber, "El indice de los diccionarios debe ser entero");
			if(pos.getType() == 5){
				long start = (long)((BooleanType)pos).getEquivalentInt();
				pos = new LongType(start);
			}
			if(pos.getType() == 0){
				long start = (long)((IntegerType)pos).getValue();
				pos = new LongType(start);
			}
			Map<Type, Type> dictionary = ((DictionaryType) estructuredType).getDictionary();
			dictionary.put(pos, value);
		} else if (estructuredType.getType() == 6) { // LIST
			
			if((pos.getType() != 2) && (pos.getType() != 0) && (pos.getType() != 5))
				throw new CompilerException(lineNumber, "El indice de las listas debe ser entero");
			if(pos.getType() == 5){
				long start = (long)((BooleanType)pos).getEquivalentInt();
				pos = new LongType(start);
			}
			if(pos.getType() == 0){
				long start = (long)((IntegerType)pos).getValue();
				pos = new LongType(start);
			}
			
			ArrayList<Type> list = ((ListType)estructuredType).getList();
			if ( ((LongType)pos).getValue() < (long)list.size()){
				list.remove(((LongType)pos).getValue());
				int index = (int)((LongType)pos).getValue();
				list.add(index, value);
			}else throw new CompilerException(lineNumber, "El indice de la lista se sale de rango");
			
		} else if(estructuredType.getType() == 11){ //TUPLE
			throw new CompilerException(lineNumber, "El Tipo de Datos Estructurado Tupla es inmutable");
		} else
			throw new CompilerException(lineNumber, "La variable no es un Tipo de Datos Estructurado (Diccionario, Lista)");

		return value;
	}

}
