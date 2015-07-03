package com.language.parser;

import java.io.ByteArrayInputStream;

import java_cup.runtime.Symbol;

import com.language.exceptions.ParsingException;
import com.language.model.Program;

public class ExpressionParser {

	public static Program parse(String expText) {

		byte[] expbytes = expText.getBytes();
		ByteArrayInputStream bais = new ByteArrayInputStream(expbytes);

		Parser parser = new Parser(new Scanner(bais));
		try {
			Symbol topsym = parser.parse();

			Program exp = (Program) topsym.value;
			return exp;

		} catch (Throwable ex) {
			throw new ParsingException(1, "Error parsing source: " + ex.getMessage());
		}

	}
}
