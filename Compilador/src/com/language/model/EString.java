package com.language.model;

public class EString extends Expression {

	String str;
	int lineNumber;

	public EString (String s, int linenum) {
		str  =s;
		lineNumber = linenum;

	};

	public void printExp(){
		System.out.println(str);
	}

	public String getType(){
		return "Type EString";
	}

	public Type evaluate(){

		Type t = new TString(str);
		return t;
	}
};
