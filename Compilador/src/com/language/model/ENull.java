package com.language.model;

import com.language.exceptions.CompilerException;

public class ENull extends Expression {

	boolean isnull;
	int lineNumber;

	public ENull(boolean atr, int linenum) {
		lineNumber = linenum;
		isnull = atr;
	}

	public void printExp() {
		System.out.println("null");
	}

	public String getType() {
		return "Type ENull";
	}

	public Type evaluate() throws CompilerException {
		Type t = new TNull();
		return t;
	}
}
