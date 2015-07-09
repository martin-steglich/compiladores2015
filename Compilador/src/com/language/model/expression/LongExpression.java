package com.language.model.expression;

import com.language.model.type.LongType;
import com.language.model.type.Type;

public class LongExpression extends Expression {

	long num;
	int lineNumber;

	public LongExpression(long num) {
		this.num = num;
		//this.lineNumber = lineNumber;
	}

	public void printExp() {
		System.out.println(num);
	}

	public String getType() {
		return "Type LongExpression";
	}

	public Type evaluate() {
		Type t = new LongType(num);
		return t;
	}

	public long getNum() {
		return num;
	}

	public void setNum(long n) {
		num = n;
	}
}
