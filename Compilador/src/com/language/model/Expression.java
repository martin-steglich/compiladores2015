package com.language.model;

import com.language.exceptions.CompilerException;

public abstract class Expression {
	public abstract void printExp();
	public abstract Type evaluate() throws CompilerException;
	public abstract String getType();
};














