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
import com.language.model.type.TupleType;
import com.language.model.type.Type;

public class AssignStructuredTypeStatement extends Statement {

	Expression expression;
	IdentifierExpression id;
	Expression position;
	Expression finishPosition;
	int lineNumber;

	public AssignStructuredTypeStatement(IdentifierExpression id,
			Expression expression, Expression position, int lineNumber) {
		this.expression = expression;
		this.id = id;
		this.position = position;
		this.lineNumber = lineNumber;

	}
	
	public AssignStructuredTypeStatement(IdentifierExpression id,
			Expression expression, Expression position,Expression finishPosition, int lineNumber) {
		this.expression = expression;
		this.id = id;
		this.position = position;
		this.finishPosition = finishPosition;
		this.lineNumber = lineNumber;

	}

	public Type execute() throws CompilerException {
		Type estructuredType = id.evaluate();
		Type pos = position.evaluate();
		Type value = expression.evaluate();
		
		if (estructuredType.getType() == 10) {// DICT
			if(finishPosition != null)
				throw new CompilerException(lineNumber, "El Tipo de Datos Estructurado Dict no puede ser modificado de esta forma");
			Map<Type, Type> dictionary = ((DictionaryType) estructuredType).getDictionary();
			dictionary.put(pos, value);	
		} else if (estructuredType.getType() == 6) { // LIST
			if(finishPosition == null){
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
					int index = (int)((LongType)pos).getValue();
					list.remove(index);
					list.add(index, value);
				}else throw new CompilerException(lineNumber, "El indice de la lista se sale de rango");
			}else{
				Type finishPos = finishPosition.evaluate();
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
				if((finishPos.getType() != 2) && (finishPos.getType() != 0) && (finishPos.getType() != 5))
					throw new CompilerException(lineNumber, "El indice de las listas debe ser entero");
				if(finishPos.getType() == 5){
					long end = (long)((BooleanType)finishPos).getEquivalentInt();
					finishPos = new LongType(end);
				}
				if(finishPos.getType() == 0){
					long end = (long)((IntegerType)finishPos).getValue();
					finishPos = new LongType(end);
				}
				
				
				ArrayList<Type> values = new ArrayList<>();
				if(value.getType() == 10){
					for(Type t : ((DictionaryType)value).getDictionary().keySet())
						values.add(t);
				}else if(value.getType() == 6)
					values = ((ListType)value).getList();
				else if(value.getType() == 11)
					values = ((TupleType)value).getTuple();
				else
					throw new CompilerException(lineNumber, "Tipo de datos no permitido para esta operacion");
				
				ArrayList<Type> list = ((ListType)estructuredType).getList();
				if ((((LongType)pos).getValue() < (long)list.size()) || (((LongType)finishPos).getValue() < (long)list.size())){
					for(int i = (int) ((LongType)pos).getValue(); i < ((LongType)finishPos).getValue(); i++){
						list.remove(i);
						list.add(i,values.get(i));
					}
				}else throw new CompilerException(lineNumber, "El indice de la lista se sale de rango");
			}
			
		} else if(estructuredType.getType() == 11){ //TUPLE
			throw new CompilerException(lineNumber, "El Tipo de Datos Estructurado Tupla es inmutable");
		} else
			throw new CompilerException(lineNumber, "La variable no es un Tipo de Datos Estructurado (Diccionario, Lista)");

		return value;
	}

}
