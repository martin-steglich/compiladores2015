package com.language.model.expression;

import java.util.Scanner;

import com.language.exceptions.CompilerException;
import com.language.model.type.StringType;
import com.language.model.type.Type;

public class ReadExpression extends Expression {

	Type message;
	int lineNumber;

	public ReadExpression(Expression expression) throws CompilerException {
		Type msg = expression.evaluate();
		if ((msg.getType() != 4) && (msg.getType() != 7) && (msg.getType() != 8) && (msg.getType() != 9))
			this.message = msg;
		else
			throw new CompilerException(lineNumber,
					"Tipo de datos no soportado como mensaje para la funcion raw_input");

	}

	@Override
	public void printExp() {
		// TODO Auto-generated method stub

	}

	@Override
	public Type evaluate() throws CompilerException {
		System.out.print(message.getAsString());
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
		
		return new StringType(input);
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}
}