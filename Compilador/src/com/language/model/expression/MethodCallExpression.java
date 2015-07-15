package com.language.model.expression;

import java.util.ArrayList;
import java.util.List;

import com.language.exceptions.CompilerException;
import com.language.model.type.BooleanType;
import com.language.model.type.IntegerType;
import com.language.model.type.ListType;
import com.language.model.type.LongType;
import com.language.model.type.StringType;
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
				
				int count = str.length() - (str.replace(subStr, "")).length();
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
				if(start != null){
					int startInt;
					if (start.getType() == 1){
						startInt = ((IntegerType)start).getValue();
					}else if (start.getType() == 2){
						startInt = (int)((LongType)start).getValue();
					}else if (start.getType() == 5){
						startInt = ((BooleanType)start).getEquivalentInt();
					}
					
				}
				
				String subStr = ((StringType)sub).getText();
				
				//TODO
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
				
				str.replaceFirst(oldStr, newStr);
				
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
			}break;
			case "insert":{
				if(parameters.size() != 2)
					throw new CompilerException(lineNumber, "Cantidad invalida de parametros para la funcion " + function);
			}break;
			case "pop":{
				if(parameters.size() != 1)
					throw new CompilerException(lineNumber, "Cantidad invalida de parametros para la funcion " + function);
			}break;
			case "size":{
				if(parameters.size() != 0)
					throw new CompilerException(lineNumber, "Cantidad invalida de parametros para la funcion " + function);
			}break;
			default: throw new CompilerException(lineNumber, "El tipo de datos de la variable no tiene la funcion "+ function + " definida");
			}
		}break;
		case 10:{//DICT
			switch(function){
			case "has_key":{
				if(parameters.size() != 1)
					throw new CompilerException(lineNumber, "Cantidad invalida de parametros para la funcion " + function);
			}break;
			case "items":{
				if(parameters.size() != 0)
					throw new CompilerException(lineNumber, "Cantidad invalida de parametros para la funcion " + function);
			}break;
			case "keys":{
				if(parameters.size() != 0)
					throw new CompilerException(lineNumber, "Cantidad invalida de parametros para la funcion " + function);
			}break;
			case "pop":{
				if(parameters.size() != 1)
					throw new CompilerException(lineNumber, "Cantidad invalida de parametros para la funcion " + function);
			}break;
			case "values":{
				if(parameters.size() != 0)
					throw new CompilerException(lineNumber, "Cantidad invalida de parametros para la funcion " + function);
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
