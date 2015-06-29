package com.language.model;

public class EBoolean extends Expression {

	boolean val;
    int lineNumber;

    public EBoolean(boolean value, int linenum) {
    	val = value;
    	lineNumber = linenum;
	};

	public void printExp(){
		System.out.println(val);
	}

	public String getType(){
		return "Type EBoolean";
	}

	public Type evaluate(){
		Type t = new TBoolean(val);
		return t;
	}
};
