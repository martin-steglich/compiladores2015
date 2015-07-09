package com.language.exceptions;

@SuppressWarnings("serial")
public class CompilerException extends Exception {

	private String message;
	
	public CompilerException(Integer lineNumber, String message) {
		String lineNumberStr;
		if(lineNumber == null){
			lineNumberStr= "N/A";
		}else{
			lineNumber++;
			lineNumberStr= "" + lineNumber;
		}
		this.message = "ERROR: Linea: " + lineNumberStr + " - " + message;
	}

	public String getMessage() {
		return message;
	}
}