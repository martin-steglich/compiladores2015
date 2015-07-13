package com.language.model.statement;

import java.util.Map;

import com.language.exceptions.CompilerException;
import com.language.model.expression.Expression;
import com.language.model.expression.IdentifierExpression;
import com.language.model.stack.StackHandler;
import com.language.model.type.DictionaryType;
import com.language.model.type.Type;

public class AssignStructuredTypeStatement extends Statement {

	Expression expression;
	IdentifierExpression id;
	Expression position;
	int lineNumber;

	public AssignStructuredTypeStatement(IdentifierExpression id,
			Expression expression, Expression position) {
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
			Map<Type, Type> dictionary = ((DictionaryType) estructuredType).getDictionary();
			dictionary.put(pos, value);
		} else if (estructuredType.getType() == 6) { // LIST
		} else if(estructuredType.getType() == 11){ //TUPLE
			throw new CompilerException(lineNumber, "El Tipo de Datos Estructurado Tupla es inmutable");
		} else
			throw new CompilerException(lineNumber, "La variable no es un Tipo de Datos Estructurado (Diccionario, Lista, Tupla)");

		return value;
	}

}
