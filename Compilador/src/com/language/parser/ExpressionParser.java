package com.language.parser;

import java.io.ByteArrayInputStream;

import java_cup.runtime.Symbol;

import com.language.exceptions.ParsingException;
import com.language.model.expression.Expression;
import com.language.model.statement.Statement;

public class ExpressionParser {

	public static Statement parse(String expText) {

		byte[] expbytes = expText.getBytes();
		ByteArrayInputStream bais = new ByteArrayInputStream(expbytes);

		Parser parser = new Parser(new Scanner(bais));
		try {
			Object result = parser.parse().value;
			/*Symbol topsym = parser.parse();
			 

			Statement exp = (Statement) topsym.value;*/
			return (Statement)result;

		} catch (Throwable ex) {
			throw new ParsingException(1, "Error parsing source: " + ex.getMessage());
		}

	}
}
