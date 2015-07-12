package com.language.model.expression;

import java.util.ArrayList;
import java.util.List;


import com.language.exceptions.CompilerException;
//import com.language.exceptions.CompilerException;
import com.language.model.type.Type;

public abstract class Expression {
	
	public abstract void printExp();
	public abstract Type evaluate() throws CompilerException;
	public abstract String getType();

	
}
