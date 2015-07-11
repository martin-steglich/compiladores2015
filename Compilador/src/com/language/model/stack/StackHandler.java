package com.language.model.stack;

import java.util.HashMap;
import java.util.Stack;

import com.language.model.type.NoneType;
import com.language.model.type.Type;


public class StackHandler {

	private static StackHandler instance = null;
	Stack stack; // execution stack
	private HashMap<String, Type> variables;
	
	
	//private Map<String, SFunction> functions;// list for functions

	StackHandler() {
		stack = new Stack();
		variables = new HashMap<>();
	}
	
	public static StackHandler getInstance() {
		if (instance == null) {
			instance = new StackHandler();
		}
		return instance;
	}
	
	public void reset() {
		//functions = new HashMap<String, SFunction>();
		stack = new Stack();
		variables = new HashMap<>();;
	}

	public Stack getStack() {
		return stack;
	}

	public  void addVariable(String id, Type type) {
		variables.put(id, type);
	}

	public Type findVariable(String id) {
		if (variables == null)
			return null;
		else if (variables.containsKey(id))
			return variables.get(id);
		else return null;

	}



}