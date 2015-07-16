package com.language.model.expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.language.exceptions.CompilerException;
import com.language.model.stack.Stack;
import com.language.model.stack.StackHandler;
import com.language.model.statement.BlockFunctionStatement;
import com.language.model.statement.FunctionStatement;
import com.language.model.type.BooleanType;
import com.language.model.type.DictionaryType;
import com.language.model.type.FloatType;
import com.language.model.type.FunctionReturnType;
import com.language.model.type.IntegerType;
import com.language.model.type.ListType;
import com.language.model.type.LongType;
import com.language.model.type.NoneType;
import com.language.model.type.StringType;
import com.language.model.type.TupleType;
import com.language.model.type.Type;

public class FunctionCallExpression extends Expression {

	String funcName;
	List<Expression> paramsList;
	Map<IdentifierExpression, Expression> paramsMap;
	int lineNumber;

	public FunctionCallExpression(String funcName, List<Expression> params,
			int linenum) {
		this.funcName = funcName;
		this.paramsList = params;
		this.lineNumber = linenum;
	}

	public FunctionCallExpression(String funcName,
			Map<IdentifierExpression, Expression> params, int linenum) {
		this.funcName = funcName;
		this.paramsMap = params;
		this.lineNumber = linenum;
	}

	public FunctionCallExpression(String funcName, int linenum) {
		this.funcName = funcName;
		this.lineNumber = linenum;
	}

	public void printExp() {
		System.out.println("LLAMADA DE FUNCION " + funcName);

		System.out.println("LLAMADA CON ESTOS PARAMETROS");
		for (Expression e : paramsList) {
			e.printExp();
		}
	}

	public String getType() {
		return "Llamada A Funcion";
	}

	public Type evaluate() throws CompilerException {
		Type typeRet = new NoneType();

		StackHandler s = StackHandler.getInstance();
		Stack stack = s.getStack();
		stack.openScope();

		FunctionStatement fun = s.findFunction(funcName);

		if (fun != null) {
			if (paramsList != null) {
				int countArgumentsDef = fun.getParams().size();
				int countArgumentsCall = paramsList.size();
				if (countArgumentsDef != countArgumentsCall) {
					throw new CompilerException(lineNumber,
							"La funcion tiene cantidad incorrecta de parametros");
				} else {
					for (int i = 0; i < countArgumentsCall; i++) {
						String var = fun.getParams().get(i).getId();
						Expression e = paramsList.get(i);
						Type exp = e.evaluate(); // evaluo los parametros de la
													// llamada
						stack.addVariableToActualScope(var, exp);
					}
					BlockFunctionStatement block = new BlockFunctionStatement(
							fun.getFuncBody(), lineNumber);
					typeRet = block.execute();
				}
			} else {
				BlockFunctionStatement block = new BlockFunctionStatement(
						fun.getFuncBody(), lineNumber);
				typeRet = block.execute();
			}

			if (typeRet instanceof FunctionReturnType) {
				FunctionReturnType aux = (FunctionReturnType) typeRet;
				List<Type> values = aux.getValue();
				if (values.isEmpty())
					typeRet = new NoneType();
				else if (values.size() == 1) {
					typeRet = values.get(0);
				} else
					typeRet = new TupleType((ArrayList<Type>) values);
			}
		} else {
			switch (funcName) {
			case "int": {
				if ((paramsList == null) || (paramsList.size() == 0)) {
					typeRet = new IntegerType(0);
				} else {
					if ((paramsList.size() != 1))
						throw new CompilerException(lineNumber, "La funcion "
								+ funcName + " requiere solo un parametro");

					Type param = paramsList.get(0).evaluate();

					if (param instanceof IntegerType)
						typeRet = new IntegerType(
								((IntegerType) param).getValue());
					else if (param instanceof LongType) {
						int num = (int) ((LongType) param).getValue();
						typeRet = new IntegerType(num);
					} else if (param instanceof FloatType) {
						int num = (int) ((FloatType) param).getValue();
						typeRet = new IntegerType(num);
					} else if (param instanceof BooleanType) {
						int num = ((BooleanType) param).getEquivalentInt();
						typeRet = new IntegerType(num);
					} else if (param instanceof StringType) {
						String str = ((StringType) param).getText();
						try {
							int num = Integer.parseInt(str);
							typeRet = new IntegerType(num);
						} catch (NumberFormatException ex) {
							throw new CompilerException(lineNumber,
									"El argumento " + str + " no es valido");
						}
					} else {
						throw new CompilerException(
								lineNumber,
								"El argumento de "
										+ funcName
										+ "() debe ser un string o un numero, no "
										+ param.getTypeEnum().toString());
					}

				}
			}
				break;
			case "float": {
				if ((paramsList == null) || (paramsList.size() == 0)) {
					typeRet = new FloatType(0);
				} else {
					if ((paramsList.size() != 1))
						throw new CompilerException(lineNumber, "La funcion "
								+ funcName + " requiere solo un parametro");
					Type param = paramsList.get(0).evaluate();

					if (param instanceof IntegerType) {
						float num = (float) ((IntegerType) param).getValue();
						typeRet = new FloatType(num);
					} else if (param instanceof LongType) {
						float num = (float) ((LongType) param).getValue();
						typeRet = new FloatType(num);
					} else if (param instanceof FloatType) {
						float num = ((FloatType) param).getValue();
						typeRet = new FloatType(num);
					} else if (param instanceof BooleanType) {
						float num = (float) ((BooleanType) param)
								.getEquivalentInt();
						typeRet = new FloatType(num);
					} else if (param instanceof StringType) {
						String str = ((StringType) param).getText();
						try {
							float num = Float.parseFloat(str);
							typeRet = new FloatType(num);
						} catch (NumberFormatException ex) {
							throw new CompilerException(lineNumber,
									"El argumento " + str + " no es valido");
						}
					} else {
						throw new CompilerException(
								lineNumber,
								"El argumento de "
										+ funcName
										+ "() debe ser un string o un numero, no "
										+ param.getTypeEnum().toString());
					}
				}
			}
				break;
			case "str": {
				if ((paramsList == null) || (paramsList.size() == 0)) {
					typeRet = new StringType("");
				} else {
					if ((paramsList.size() != 1))
						throw new CompilerException(lineNumber, "La funcion "
								+ funcName + " requiere solo un parametro");
					Type param = paramsList.get(0).evaluate();
					typeRet = new StringType(param.getAsString());
				}
			}
				break;
			case "tuple": {
				if ((paramsList == null) || (paramsList.size() == 0)) {
					typeRet = new StringType("");
				} else {
					if ((paramsList.size() != 1))
						throw new CompilerException(lineNumber, "La funcion "
								+ funcName + " requiere solo un parametro");
					Type param = paramsList.get(0).evaluate();

					if (param instanceof DictionaryType) {
						ArrayList<Type> tuple = new ArrayList<>();
						Map<Type, Type> dict = ((DictionaryType) param)
								.getDictionary();
						for (Type key : dict.keySet()) {
							tuple.add(key);
						}

						typeRet = new TupleType(tuple);
					} else if (param instanceof TupleType) {
						typeRet = new TupleType(((TupleType) param).getTuple());
					} else if (param instanceof ListType) {
						typeRet = new TupleType(((ListType) param).getList());
					} else if (param instanceof StringType) {
						ArrayList<Type> tuple = new ArrayList<>();
						String str = ((StringType) param).getText();
						int length = str.length();
						for (int i = 0; i < length; i++) {
							String actualChar = String.valueOf(str.charAt(i));
							tuple.add(new StringType(actualChar));
						}
						
						typeRet = new TupleType(tuple);
					} else 
						throw new CompilerException(lineNumber, "El objeto de tipo " + param.getTypeEnum().toString() + " no es iterable");
				}
			}
				break;
			case "dict": {
				if ((paramsList == null) || (paramsList.size() == 0)) {
					typeRet = new StringType("");
				} else{ 
					if(paramsList.size() != 1)
						throw new  CompilerException(lineNumber, "La funcion " + funcName + " requiere dos parametros");
					
					Type param = paramsList.get(0).evaluate();
					
					if(param instanceof ListType){
						Map<Type, Type> dict = new HashMap<>();
						ArrayList<Type> parameter = ((ListType)param).getList();
						for(Type t : parameter){
							if(t instanceof ListType){
								ArrayList<Type> values = ((ListType)t).getList();
								 
								 if(values.size() != 2)
										throw new CompilerException(lineNumber, "La secuencia de actualizacion "+ t.getAsString() + " tiene largo distinto a 2");
									
									dict.put(values.get(0), values.get(1));
							}else if(t instanceof TupleType){
								ArrayList<Type> values = ((TupleType)t).getTuple();
								if(values.size() != 2)
									throw new CompilerException(lineNumber, "Secuencia de actualizacion "+ t.getAsString() + " tiene largo distinto a 2");
								
								dict.put(values.get(0), values.get(1));
							}else if (t instanceof StringType){
								String str = ((StringType)t).getText();
								
								if(str.length() != 2)
									throw new CompilerException(lineNumber, "Secuencia de actualizacion "+ t.getAsString() + " tiene largo distinto a 2");
								StringType key = new StringType(String.valueOf(str.charAt(0)));
								StringType value = new StringType(String.valueOf(str.charAt(1)));
								dict.put(key, value);
							}else{
								throw new CompilerException(lineNumber, "Secuencia de actualizacion "+ t.getAsString() + " tiene largo distinto a 2");
							}
							
						}
						typeRet = new DictionaryType(dict);
					}else if (param instanceof TupleType){
						Map<Type, Type> dict = new HashMap<>();
						ArrayList<Type> parameter = ((TupleType)param).getTuple();
						for(Type t : parameter){
							if(t instanceof ListType){
								ArrayList<Type> values = ((ListType)t).getList();
								 
								 if(values.size() != 2)
										throw new CompilerException(lineNumber, "La secuencia de actualizacion "+ t.getAsString() + " tiene largo distinto a 2");
									
									dict.put(values.get(0), values.get(1));
							}else if(t instanceof TupleType){
								ArrayList<Type> values = ((TupleType)t).getTuple();
								if(values.size() != 2)
									throw new CompilerException(lineNumber, "Secuencia de actualizacion "+ t.getAsString() + " tiene largo distinto a 2");
								
								dict.put(values.get(0), values.get(1));
							}else if (t instanceof StringType){
								String str = ((StringType)t).getText();
								
								if(str.length() != 2)
									throw new CompilerException(lineNumber, "Secuencia de actualizacion "+ t.getAsString() + " tiene largo distinto a 2");
								StringType key = new StringType(String.valueOf(str.charAt(0)));
								StringType value = new StringType(String.valueOf(str.charAt(1)));
								dict.put(key, value);
							}else{
								throw new CompilerException(lineNumber, "Secuencia de actualizacion "+ t.getAsString() + " tiene largo distinto a 2");
							}
							
						}
						typeRet = new DictionaryType(dict);
					}else if (param instanceof DictionaryType){
						typeRet = new DictionaryType(((DictionaryType)param).getDictionary());
					}else
						throw new CompilerException(lineNumber, "Tipo de datos no soportado para la funcion "+ funcName);
				}
			}
				break;
			default:
				throw new CompilerException(lineNumber, "La funcion "
						+ funcName + " no esta definida");
			}
		}
		stack.closeScope();

		return typeRet;
	}
}
