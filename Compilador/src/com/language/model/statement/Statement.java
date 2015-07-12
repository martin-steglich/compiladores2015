package com.language.model.statement;

import com.language.exceptions.CompilerException;
import com.language.model.type.Type;

public abstract class Statement {
	public abstract Type execute() throws CompilerException;

}
