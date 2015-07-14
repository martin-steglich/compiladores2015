package com.language.model.expression;

import java.util.ArrayList;
import java.util.Map;

import com.language.exceptions.CompilerException;
import com.language.model.type.BooleanType;
import com.language.model.type.DictionaryType;
import com.language.model.type.IntegerType;
import com.language.model.type.LongType;
import com.language.model.type.TupleType;
import com.language.model.type.ListType;
import com.language.model.type.Type;

public class StructuredTypePositionExpression extends Expression{
	
	IdentifierExpression id;
	Expression startPosition;
	Expression endPosition;
	Expression cantPos;
	boolean tienePuntos;
	int lineNumber;
	
	
	
	
	public StructuredTypePositionExpression(IdentifierExpression id, Expression startPosition, Expression endPosition, Expression cantPos, boolean tienePuntos ) {
		this.id = id;
		this.startPosition = startPosition;
		this.endPosition = endPosition; 
		this.cantPos = cantPos;
		this.tienePuntos = tienePuntos;
//		this.lineNumber = lineNumber;
	}

	@Override
	public void printExp() {	
	}

	@Override
	public Type evaluate() throws CompilerException {
		Type structuredType = id.evaluate();
		if(structuredType.getType() == 10){ //DICT
			Type startPos = startPosition.evaluate();
			Map<Type, Type> dictionary = ((DictionaryType)structuredType).getDictionary();
			if(dictionary.containsKey(startPos)){
				return dictionary.get(startPos);
			} else
				throw new CompilerException(lineNumber, "La variable '" + id.getId() + "' no contiene la entrada '" + startPos.getAsString() + "'");
		} else if(structuredType.getType() == 6){ //LIST
			if (startPosition!= null){
				Type startPos = startPosition.evaluate();
				if((startPos.getType() != 2) && (startPos.getType() != 0) && (startPos.getType() != 5))
					throw new CompilerException(lineNumber, "El indice de las listas debe ser entero");
				if(startPos.getType() == 5){
					long start = (long)((BooleanType)startPos).getEquivalentInt();
					startPos = new LongType(start);
				}
				if(startPos.getType() == 0){
					long start = (long)((IntegerType)startPos).getValue();
					startPos = new LongType(start);
				}
				ArrayList<Type> list = ((ListType)structuredType).getList();
				int size = list.size();
				
				if(((LongType)startPos).getValue() >= size)
					throw new CompilerException(lineNumber, "El indice de la lista esta fuera de rango");
				if ((cantPos == null) && (endPosition == null) && (!tienePuntos)){ //[N]
						int index = (int)((LongType)startPos).getValue();
						if (index < 0){
							int index_abs = Math.abs(index);
							if (index_abs <= size){
								index = size - index_abs;
							}else throw new CompilerException(lineNumber, "El indice de la lista esta fuera de rango");
						}
						Type ret = list.get(index);
						return ret;					
				}else{
					if (endPosition != null){//[N:N..]
						Type endPos = endPosition.evaluate();
						if((endPos.getType() != 2) && (endPos.getType() != 0) && (endPos.getType() != 5))
							throw new CompilerException(lineNumber, "El indice de las listas debe ser entero");
						if(endPos.getType() == 5){
							long end = (long)((BooleanType)endPos).getEquivalentInt();
							endPos = new LongType(end);
						}
						if(endPos.getType() == 0){
							long end = (long)((IntegerType)endPos).getValue();
							endPos = new LongType(end);
						}
						
						if(((LongType)endPos).getValue() > size)
							throw new CompilerException(lineNumber, "El indice de la lista esta fuera de rango");
						ArrayList<Type> ListaRet = new ArrayList<>();
						if (cantPos != null){ //[N:N:N]
							int valPos = ((IntegerType)(cantPos.evaluate())).getValue();
							for(long i = ((LongType)startPos).getValue(); i < ((LongType)endPos).getValue(); i= i + (long)valPos){
								ListaRet.add(list.get((int)i));
							}
						}else{ //[N:N]
							for(long i = ((LongType)startPos).getValue(); i < ((LongType)endPos).getValue(); i++){
								ListaRet.add(list.get((int)i));
							}
						}
						return new ListType(ListaRet);
										
					}else{ //[N:..]
						ArrayList<Type> ListaRet = new ArrayList<>();
						if (cantPos != null){ //[N::N]
							int valPos = ((IntegerType)(cantPos.evaluate())).getValue();
							for(long i = ((LongType)startPos).getValue(); i < size; i= i + (long)valPos){
								ListaRet.add(list.get((int)i));
							}
						}else{ //[N:]
							for(long i = ((LongType)startPos).getValue(); i < size; i++){
								ListaRet.add(list.get((int)i));
							}
						}
						return new ListType(ListaRet);
					}
					
				}
			
			}else{//[:..]
				
				ArrayList<Type> list = ((ListType)structuredType).getList();
				int size = list.size();
				ArrayList<Type> ListRet = new ArrayList<>();
				if (endPosition != null){//[:N..]
					Type endPos = endPosition.evaluate();
					if((endPos.getType() != 2) && (endPos.getType() != 0) && (endPos.getType() != 5))
						throw new CompilerException(lineNumber, "El indice de las listas debe ser entero");
					if(endPos.getType() == 5){
						long end = (long)((BooleanType)endPos).getEquivalentInt();
						endPos = new LongType(end);
					}
					if(endPos.getType() == 0){
						long end = (long)((IntegerType)endPos).getValue();
						endPos = new LongType(end);
					}
					
					if(((LongType)endPos).getValue() > size)
						throw new CompilerException(lineNumber, "El indice de la tupla esta fuera de rango");		
					if (cantPos != null){ //[:N:N]
						int valPos = ((IntegerType)(cantPos.evaluate())).getValue();
						for(int i = 0; i < ((LongType)endPos).getValue(); i= i + valPos){
							ListRet.add(list.get((int)i));
						}
					}else{ //[:N]
						for(int i = 0; i < ((LongType)endPos).getValue(); i++){
							ListRet.add(list.get((int)i));
						}
					}
				}else{ //[:..]
					if (cantPos != null){ //[::N]
						int valPos = ((IntegerType)(cantPos.evaluate())).getValue();
						for(int i = 0; i < size; i= i + valPos){
							ListRet.add(list.get((int)i));
						}
					}else{ //[:]
						for(int i = 0; i < size; i++){
							ListRet.add(list.get((int)i));
						}
					}
					
				}
				return new ListType(ListRet);
			}	
		} else if(structuredType.getType() == 11){ //TUPLE
			if (startPosition!= null){ //[N:..]
				Type startPos = startPosition.evaluate();
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
				if ((cantPos == null) && (endPosition == null)&& (!tienePuntos)){ //[N]
					int index = (int)((LongType)startPos).getValue();
					if (index < 0){
						int index_abs = Math.abs(index);
						if (index_abs <= size){
							index = size - index_abs;
						}else throw new CompilerException(lineNumber, "El indice de la tupla esta fuera de rango");
					}
					Type ret = tuple.get(index);
					return ret;
				}else{
					if (endPosition != null){ //[N:N..]
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
						
						if(((LongType)endPos).getValue() > size)
							throw new CompilerException(lineNumber, "El indice de la tupla esta fuera de rango");
						ArrayList<Type> tupleRet = new ArrayList<>();
						if (cantPos != null){ //[N:N:N]
							int valPos = ((IntegerType)(cantPos.evaluate())).getValue();
							for(long i = ((LongType)startPos).getValue(); i < ((LongType)endPos).getValue(); i= i + (long)valPos){
								tupleRet.add(tuple.get((int)i));
							}
						}else{ //[N:N]
							for(long i = ((LongType)startPos).getValue(); i < ((LongType)endPos).getValue(); i++){
								tupleRet.add(tuple.get((int)i));
							}
						}
						return new TupleType(tupleRet);
										
					}else{ //[N:..]
						ArrayList<Type> tupleRet = new ArrayList<>();
						if (cantPos != null){ //[N::N]
							int valPos = ((IntegerType)(cantPos.evaluate())).getValue();
							for(long i = ((LongType)startPos).getValue(); i < size; i= i + (long)valPos){
								tupleRet.add(tuple.get((int)i));
							}
						}else{ //[N:]
							for(long i = ((LongType)startPos).getValue(); i < size; i++){
								tupleRet.add(tuple.get((int)i));
							}
						}
						return new TupleType(tupleRet);
					}
					
				}
			
			}else{ //[:..]
				ArrayList<Type> tuple = ((TupleType)structuredType).getTuple();
				int size = tuple.size();
				ArrayList<Type> tupleRet = new ArrayList<>();
				if (endPosition != null){ //[:N..]
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
					
					if(((LongType)endPos).getValue() > size)
						throw new CompilerException(lineNumber, "El indice de la tupla esta fuera de rango");		
					if (cantPos != null){ //[:N:N]
						int valPos = ((IntegerType)(cantPos.evaluate())).getValue();
						for(int i = 0; i < ((LongType)endPos).getValue(); i= i + valPos){
							tupleRet.add(tuple.get((int)i));
						}
					}else{ //[:N]
						for(int i = 0; i < ((LongType)endPos).getValue(); i++){
							tupleRet.add(tuple.get((int)i));
						}
					}
				}else{ //[:..]
					if (cantPos != null){ //[::N]
						int valPos = ((IntegerType)(cantPos.evaluate())).getValue();
						for(int i = 0; i < size; i= i + valPos){
							tupleRet.add(tuple.get((int)i));
						}
					}else{ //[:]
						for(int i = 0; i < size; i++){
							tupleRet.add(tuple.get((int)i));
						}
					}
					
				}
				return new TupleType(tupleRet);
			}	
		} else
			throw new CompilerException(lineNumber, "La variable no es un Tipo de Datos Estructurado (Diccionario, Lista, Tupla)");
			
			
		
	}
	


	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "EstructuredTypePositionExpression";
	}

}
