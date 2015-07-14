package com.language.model.expression;

import com.language.model.type.StringType;
import com.language.model.type.Type;

public class StringExpression extends Expression {

	String text;
	int lineNumber;

	public StringExpression(String text, int lineNumber) {
		this.text = text;
		this.lineNumber = lineNumber;
	}

	public void printExp() {
		System.out.println(text);
	}

	public String getType() {
		return "Type StringExpression";
	}

	public Type evaluate() {
		Type t = new StringType(text);
		return t;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
