package com.language.model.stack;

import java.util.HashMap;
import java.util.Map;

import com.language.exceptions.CompilerException;
import com.language.model.type.Type;
import com.language.model.statement.FunctionStatement;
import com.language.model.stack.Stack;

public class StackHandler {

	private static StackHandler instance = null;
	Stack stack; // execution stack
	private HashMap<String, Type> variables;
	private Map<String, FunctionStatement> functions;// list for functions

	StackHandler() {
		stack = new Stack();
		variables = new HashMap<>();
		functions = new HashMap<String, FunctionStatement>();
	}
	
	public static StackHandler getInstance() {
		if (instance == null) {
			instance = new StackHandler();
		}
		return instance;
	}
	
	public void reset() {
		stack = new Stack();
		variables = new HashMap<>();;
		functions = new HashMap<String, FunctionStatement>();
	}

	public Stack getStack() {
		return stack;
	}
	
	public Type findVariable(String id) {
		if (variables == null)
			return null;
		else if (variables.containsKey(id))
			return variables.get(id);
		else return null;
	}
	
	public FunctionStatement findFunction(String id) throws CompilerException {
		// Se realiza la busqueda de la funcion en el listado de funciones
		if (functions.containsKey(id)) {
			return functions.get(id);
		} else {
			// en el caso de no encontrarse la funcion se retorna un error
			throw new CompilerException(null, "La function " + id
					+ " no se encuentra definida.");
		}
	}
	
	public void addFunction(String id, FunctionStatement func) throws CompilerException {
		FunctionStatement funcAux= null;
		try {
			// Se busca la funcion y en caso que exista se envia un 
			// mensaje de error de que la funcion ya fue definida con anterioridad
			funcAux = findFunction(id);
		} catch (CompilerException e) {
			functions.put(func.getFuncName().getId(), func);
			return;
		}
		throw new CompilerException(null, "La function " + id
				+ " ya se encuentra definida en la linea "+ funcAux.getLineNumber());
	}

}