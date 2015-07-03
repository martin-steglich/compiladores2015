package com.language.exceptions;

@SuppressWarnings("serial")
public class ParsingException extends RuntimeException {

	private String message;
	
	public ParsingException(int lineNumber, int columnNumber, String message) {
		lineNumber++;
		this.message = "ERROR: Linea: " + lineNumber + ", Columna: " + columnNumber + " - " + message;
	}

	public ParsingException(int lineNumber, String message) {
		lineNumber++;
		this.message = "ERROR: Linea: " + lineNumber + " - " + message;
	}

	public String getMessage() {
		return message;
	}
}
