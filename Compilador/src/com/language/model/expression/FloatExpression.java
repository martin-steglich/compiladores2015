package com.language.model.expression;

import com.language.model.type.FloatType;
import com.language.model.type.IntegerType;
import com.language.model.type.Type;

public class FloatExpression extends  Expression {

	float num;
	int lineNumber;

	public FloatExpression(float num) {
		this.num = num;
		//this.lineNumber = lineNumber;
	}

	public void printExp() {
		System.out.println(num);
	}

	public String getType() {
		return "Type FloatExpression";
	}

	public Type evaluate() {
		Type t = new FloatType(num);
		return t;
	}

	public float getNum() {
		return num;
	}

	public void setNum(int n) {
		num = n;
	}

};
