package com.language.model;

public class EDecimal extends Expression {
	  
	float num;
    int lineNumber;

	public EDecimal(float n, int linenum) {
		lineNumber = linenum;
		num = n;
	};

	public void printExp() {
		System.out.println(num);
	}

	public String getType(){
		return "Type EInteger";
	}

	public Type evaluate(){
		Type t = new TFloat(num);
		return t;
	}

	public float getNum(){
        return num;
     }

	public void setNum(float n){
        num=n;
     }
};