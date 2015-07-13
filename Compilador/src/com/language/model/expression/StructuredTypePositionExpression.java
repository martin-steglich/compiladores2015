package com.language.model.expression;

import java.util.ArrayList;
import java.util.Map;

import com.language.exceptions.CompilerException;
import com.language.model.type.BooleanType;
import com.language.model.type.DictionaryType;
import com.language.model.type.IntegerType;
import com.language.model.type.LongType;
import com.language.model.type.TupleType;
import com.language.model.type.Type;

public class StructuredTypePositionExpression extends Expression{
	
	IdentifierExpression id;
	Expression startPosition;
	Expression endPosition;
	int lineNumber;
	
	
	
	public StructuredTypePositionExpression(IdentifierExpression id, Expression startPosition, Expression endPosition) {
		this.id = id;
		this.startPosition = startPosition;
		this.endPosition = endPosition; 
//		this.lineNumber = lineNumber;
	}

	@Override
	public void printExp() {	
	}

	@Override
	public Type evaluate() throws CompilerException {
		Type structuredType = id.evaluate();
		Type startPos = startPosition.evaluate();
		if(structuredType.getType() == 10){ //DICT
			Map<Type, Type> dictionary = ((DictionaryType)structuredType).getDictionary();
			if(dictionary.containsKey(startPos)){
				return dictionary.get(startPos);
			} else
				throw new CompilerException(lineNumber, "La variable '" + id.getId() + "' no contiene la entrada '" + startPos.getAsString() + "'");
		} else if(structuredType.getType() == 6){ //LIST
			
		} else if(structuredType.getType() == 11){ //TUPLE
			
			if((startPos.getType() != 2) && (startPos.getType() != 0) && (startPos.getType() != 5))
				throw new CompilerException(lineNumber, "El indice de las tuplas debe ser entero");
			if(startPos.getType() == 5){
				long start = (long)((BooleanType)startPos).getEquivalentInt();
				startPos = new LongType(start);
			}
			if(startPos.getType() == 0){
				long start = (long)((IntegerType)startPos).getValue();
				startPos = new LongType(start);
			}
			ArrayList<Type> tuple = ((TupleType)structuredType).getTuple();
			int size = tuple.size();
			
			if(((LongType)startPos).getValue() >= size)
				throw new CompilerException(lineNumber, "El indice de la tupla esta fuera de rango");
			
			if (endPosition != null){
				Type endPos = endPosition.evaluate();
				if((endPos.getType() != 2) && (endPos.getType() != 0) && (endPos.getType() != 5))
					throw new CompilerException(lineNumber, "El indice de las tuplas debe ser entero");
				if(endPos.getType() == 5){
					long end = (long)((BooleanType)endPos).getEquivalentInt();
					endPos = new LongType(end);
				}
				if(endPos.getType() == 0){
					long end = (long)((IntegerType)endPos).getValue();
					endPos = new LongType(end);
				}
				
				if(((LongType)endPos).getValue() >= size)
					throw new CompilerException(lineNumber, "El indice de la tupla esta fuera de rango");
				
				ArrayList<Type> tupleRet = new ArrayList<>();
				for(long i = ((LongType)startPos).getValue(); i < ((LongType)endPos).getValue(); i++){
					tupleRet.add(tuple.get((int)i));
				}
				
				return new TupleType(tupleRet);
								
			}
			
			int index = (int)((LongType)startPos).getValue();
			Type ret = tuple.get(index);
			return ret;
		} else
			throw new CompilerException(lineNumber, "La variable no es un Tipo de Datos Estructurado (Diccionario, Lista, Tupla)");
			
		return startPos;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "EstructuredTypePositionExpression";
	}

}
