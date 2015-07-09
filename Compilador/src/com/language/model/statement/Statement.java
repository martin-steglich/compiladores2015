package com.language.model.statement;

import com.language.exceptions.CompilerException;

public abstract class Statement {
	public abstract void execute() throws CompilerException;

}
