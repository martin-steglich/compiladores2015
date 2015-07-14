package com.language.model.expression;

import java.util.Scanner;

import com.language.exceptions.CompilerException;
import com.language.model.type.StringType;
import com.language.model.type.Type;

public class ReadExpression extends Expression{
	
	String message;
	int lineNumber;
	
	public ReadExpression(Expression expression) throws CompilerException{
		Type msg = expression.evaluate();
		if(msg instanceof StringType)
			this.message = ((StringType)msg).getText();
		else
			throw new CompilerException(lineNumber, "Tipo de datos no soportado como mensaje para la funcion raw_input");
		
	}

	@Override
	public void printExp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Type evaluate() throws CompilerException {
		System.out.print(message);
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		scanner.close();
		return new StringType(input);
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}
}