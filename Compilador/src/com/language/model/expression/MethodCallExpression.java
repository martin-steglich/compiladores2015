package com.language.model.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.language.exceptions.CompilerException;
import com.language.model.type.BooleanType;
import com.language.model.type.DictionaryType;
import com.language.model.type.IntegerType;
import com.language.model.type.ListType;
import com.language.model.type.LongType;
import com.language.model.type.NoneType;
import com.language.model.type.StringType;
import com.language.model.type.TupleType;
import com.language.model.type.Type;


public class MethodCallExpression extends Expression {

	IdentifierExpression id;
	String function;
	List<Expression> parameters;
	int lineNumber;
	
	public MethodCallExpression(IdentifierExpression id, String function, List<Expression> parameters, int lineNumber) {
		this.lineNumber = lineNumber;
		this.parameters = parameters;
		this.id = id;
		this.function = function;
	}
	
	public void printExp() {
	}

	public String getType() {
		return "Llamada a Metodo";
	}
	
	public Type evaluate() throws CompilerException {
		Type var = id.evaluate();
		Type ret = null;
		switch(var.getType()){
		case 3:{//STRING
			String str = ((StringType)var).getText();
			switch(function){
			case "count":{
				if(parameters.size() != 1)
					throw new CompilerException(lineNumber, "Cantidad invalida de parametros para la funcion " + function);
				
				Type sub = parameters.get(0).evaluate();
				
				//TODO comprobar
				if(sub.getType() != 3){
					throw new CompilerException(lineNumber, "Tipo de datos invalido como parametro para la funcion " + function);
				}
				
				String subStr = ((StringType)sub).getText();
				
				
				int lastIndex = 0;
				int count = 0;

				while((lastIndex != -1) && (subStr.length() > 0)){

				    lastIndex = str.indexOf(subStr,lastIndex);

				    if(lastIndex != -1){
				        count ++;
				        lastIndex += subStr.length();
				    }
				}
				ret = new IntegerType(count);
				
			}break;
			case "find":{
				if((parameters.size() != 1) && (parameters.size() != 2))
					throw new CompilerException(lineNumber, "Cantidad invalida de parametros para la funcion " + function);
				
				Type sub = parameters.get(0).evaluate();
				Type start = null;
				if(parameters.size() > 1)
					start = parameters.get(1).evaluate();
				
				//TODO comprobar
				if(sub.getType() != 3) {
					throw new CompilerException(lineNumber, "Tipo de datos invalido como primer parametro para la funcion " + function);
				}
				String subStr = ((StringType)sub).getText();
				if(start != null){
					int startInt = 0;
					if (start.getType() == 0){
						startInt = ((IntegerType)start).getValue();
					}else if (start.getType() == 2){
						startInt = (int)((LongType)start).getValue();
					}else if (start.getType() == 5){
						startInt = ((BooleanType)start).getEquivalentInt();
					}else
						throw new CompilerException(lineNumber, "Tipo de datos invalido como segundo parametro para la funcion " + function);
					
					int index = str.indexOf(subStr, startInt);
					ret = new IntegerType(index);
				}else{
					int index = str.indexOf(subStr);
					ret = new IntegerType(index);
				}			
			}break;
			case "join":{
				if(parameters.size() != 1)
					throw new CompilerException(lineNumber, "Cantidad invalida de parametros para la funcion " + function);
				
				Type sub = parameters.get(0).evaluate();
				
				//TODO comprobar
				if(sub.getType() != 3){
					throw new CompilerException(lineNumber, "Tipo de datos invalido como parametro para la funcion " + function);
				}
				
				String subStr = ((StringType)sub).getText();
				
				String[] strAux = str.split("");
				String resultado = "";
				int length = strAux.length;
				for(String s :strAux){
					resultado += s;
					if(length > 1)
						resultado += subStr;
					
					length--;
				}
				
				ret = new StringType(resultado);
			}break;
			case "split":{
				if(parameters.size() != 1)
					throw new CompilerException(lineNumber, "Cantidad invalida de parametros para la funcion " + function);
				
				Type sub = parameters.get(0).evaluate();
				
				//TODO comprobar
				if(sub.getType() != 3){
					throw new CompilerException(lineNumber, "Tipo de datos invalido como parametro para la funcion " + function);
				}
				
				String subStr = ((StringType)sub).getText();
				
				
				ArrayList<Type> strList = new ArrayList<>();
				for(String s :str.split(subStr)){
					StringType t = new StringType(s);
					strList.add(t);
				}
				
				ret = new ListType(strList);
				
			}break;
			case "replace":{
				if(parameters.size() != 2)
					throw new CompilerException(lineNumber, "Cantidad invalida de parametros para la funcion " + function);
				
				Type old = parameters.get(0).evaluate();
				Type newT = parameters.get(1).evaluate();
				//TODO comprobar
				if((old.getType() != 3) || (newT.getType() != 3)){
					throw new CompilerException(lineNumber, "Tipo de datos invalido como parametro para la funcion " + function);
				}
				
				String oldStr = ((StringType)old).getText();
				String newStr = ((StringType)newT).getText();
				str = str.replaceFirst(oldStr, newStr);
				
				ret = new StringType(str);
				
			}break;
			case "length":{
				if(parameters.size() != 0)
					throw new CompilerException(lineNumber, "Cantidad invalida de parametros para la funcion " + function);
				
				ret = new IntegerType(str.length());
			}break;
			default: throw new CompilerException(lineNumber, "El tipo de datos de la variable no tiene la funcion "+ function + " definida");
			}
		}break;
		case 6:{//LIST
			ArrayList<Type> list = ((ListType)var).getList();
			switch(function){
			case "append":{
				if(parameters.size() != 1)
					throw new CompilerException(lineNumber, "Cantidad invalida de parametros para la funcion " + function);
				
				Type item = parameters.get(0).evaluate();
				list.add(item);
				ret = new ListType(list);
				
			}break;
			case "count":{
				if(parameters.size() != 1)
					throw new CompilerException(lineNumber, "Cantidad invalida de parametros para la funcion " + function);
				
				Type item = parameters.get(0).evaluate();
				int count = 0;
				for(Type t: list){
					if(item.equals(t))
						count++;
				}
				
				ret = new IntegerType(count);
				
			}break;
			case "extend":{
				if(parameters.size() != 1)
					throw new CompilerException(lineNumber, "Cantidad invalida de parametros para la funcion " + function);
				
				Type secondList = parameters.get(0).evaluate();
				
				if(secondList.getType() != 6)
					throw new CompilerException(lineNumber, "Tipo de datos invalido como parametro para la funcion " + function);
				
				list.addAll(((ListType)secondList).getList());
				
				ret = new ListType(list);
			}break;
			case "index":{
				if((parameters.size() != 1) && (parameters.size() != 2))
					throw new CompilerException(lineNumber, "Cantidad invalida de parametros para la funcion " + function);
				
				Type value = parameters.get(0).evaluate();
				Type startType;
				if(parameters.size() == 2){
					startType = parameters.get(1).evaluate();
					int start;
					if(startType.getType() == 0)
						start = ((IntegerType)startType).getValue();
					else if(startType.getType() == 2)
						start = (int) ((LongType)startType).getValue();
					else if (startType.getType() == 5)
						start = ((BooleanType)startType).getEquivalentInt();
					else
						throw new CompilerException(lineNumber, "Tipo de datos invalido como indice para la funcion" + function);
					
					int index = list.size();
					for(int i = start; i < list.size(); i++){
						if(list.get(i).equals(value)){			
							index = i;
							break;
						}
					}
					
					if(index == list.size())
						ret = new NoneType();
					else
						ret = new IntegerType(index);
					
				}else{
					int index = list.indexOf(value);
					if (index < 0){
						throw new CompilerException(lineNumber, "El elemento indicado no esta en la lista");
					}
					else ret = new IntegerType(index);
				}
			}break;
			case "insert":{
				if(parameters.size() != 2)
					throw new CompilerException(lineNumber, "Cantidad invalida de parametros para la funcion " + function);
				
				Type indexType = parameters.get(0).evaluate();
				Type valueType = parameters.get(1).evaluate();

				int index = 0;
				if(indexType.getType() == 0)
					index = ((IntegerType)indexType).getValue();
				else if(indexType.getType() == 2)
					index = (int) ((LongType)indexType).getValue();
				else if (indexType.getType() == 5)
					index = ((BooleanType)indexType).getEquivalentInt();
				else
					throw new CompilerException(lineNumber, "Tipo de datos invalido como indice para la funcion" + function);
				
				list.add(index,valueType);
				
				ret = new ListType(list);
				
			}break;
			case "pop":{
				if(parameters.size() != 1)
					throw new CompilerException(lineNumber, "Cantidad invalida de parametros para la funcion " + function);
				
				Type indexType = parameters.get(0).evaluate();

				int index = 0;
				if(indexType.getType() == 0)
					index = ((IntegerType)indexType).getValue();
				else if(indexType.getType() == 2)
					index = (int) ((LongType)indexType).getValue();
				else if (indexType.getType() == 5)
					index = ((BooleanType)indexType).getEquivalentInt();
				else
					throw new CompilerException(lineNumber, "Tipo de datos invalido como indice para la funcion" + function);
				
				if(index >= list.size())
					throw new CompilerException(lineNumber, "El indice se encuentra fuera de rango");
				
				list.remove(index);
				
				ret = new ListType(list);
				
			}break;
			case "size":{
				if(parameters.size() != 0)
					throw new CompilerException(lineNumber, "Cantidad invalida de parametros para la funcion " + function);
				
				ret = new IntegerType(list.size());
			}break;
			default: throw new CompilerException(lineNumber, "El tipo de datos de la variable no tiene la funcion "+ function + " definida");
			}
		}break;
		case 10:{//DICT
			Map<Type,Type> map = ((DictionaryType)var).getDictionary();
			switch(function){
			case "has_key":{
				if(parameters.size() != 1)
					throw new CompilerException(lineNumber, "Cantidad invalida de parametros para la funcion " + function);
				
				Type keyType = parameters.get(0).evaluate();
				
				boolean result = map.containsKey(keyType);
				
				ret = new BooleanType(result);
				
			}break;
			case "items":{
				if(parameters.size() != 0)
					throw new CompilerException(lineNumber, "Cantidad invalida de parametros para la funcion " + function);
				
				ArrayList<Type> list = new ArrayList<>();
				for(Entry<Type,Type> entry : map.entrySet()){
					ArrayList<Type> tuple = new ArrayList<>();
					tuple.add(entry.getKey());
					tuple.add(entry.getValue());
					Type tupleType = new TupleType(tuple);
					list.add(tupleType);
				}
				
				ret = new ListType(list);
			}break;
			case "keys":{
				if(parameters.size() != 0)
					throw new CompilerException(lineNumber, "Cantidad invalida de parametros para la funcion " + function);
				
				ArrayList<Type> keys = new ArrayList<>();
				for(Type key : map.keySet()){
					keys.add(key);
				}
				
				ret = new ListType(keys);
			}break;
			case "pop":{
				if(parameters.size() != 1)
					throw new CompilerException(lineNumber, "Cantidad invalida de parametros para la funcion " + function);
				
				Type key = parameters.get(0).evaluate();
				
				if(map.containsKey(key)){
					map.remove(key);
					ret = new DictionaryType(map);
				} else 
					throw new CompilerException(lineNumber, "El diccionario no contiene la entrada " + key.getAsString());
			}break;
			case "values":{
				if(parameters.size() != 0)
					throw new CompilerException(lineNumber, "Cantidad invalida de parametros para la funcion " + function);
				
				ArrayList<Type> values = new ArrayList<>();
				for(Type value : map.values()){
					values.add(value);
				}
				
				ret = new ListType(values);
			}break;
			default: throw new CompilerException(lineNumber, "El tipo de datos de la variable no tiene la funcion "+ function + " definida");
			}
		}break;
		default: throw new CompilerException(lineNumber, "El tipo de datos de la variable no tiene la funcion "+ function + " definida");
		}
		
		return ret;
	}
	
	
	
	/****TODO*******
	 * String -> find
	 * 
	 * List -> index, insert, pop, size
	 * 
	 * Dict -> Todas 
	 * 
	 ****TODO*******/
	
	
}
