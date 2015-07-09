package com.language.model.expression;

import com.language.model.expression.Expression;
import com.language.model.type.*;

public class IntegerExpression extends Expression {

	int num;
	int lineNumber;

	public IntegerExpression(int num) {
		this.num = num;
		//this.lineNumber = lineNumber;
	}

	public void printExp() {
		System.out.println(num);
	}

	public String getType() {
		return "Type IntegerExpression";
	}

	public Type evaluate() {
		Type t = new IntegerType(num);
		return t;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int n) {
		num = n;
	}
};
