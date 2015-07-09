package com.language.parser;

import java.io.ByteArrayInputStream;

import com.language.exceptions.ParsingException;
import com.language.model.program.*;;

public class ProgramParser {

	public static Program parse(String expText) {

		byte[] expbytes = expText.getBytes();
		ByteArrayInputStream bais = new ByteArrayInputStream(expbytes);

		Parser parser = new Parser(new Scanner(bais));
		try {
			Object result = parser.parse().value;
			return (Program)result;

		} catch (Throwable ex) {
			throw new ParsingException(1, "Error parsing source: " + ex.getMessage());
		}

	}
}
