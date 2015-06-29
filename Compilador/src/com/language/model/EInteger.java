package com.language.model;

public class EInteger extends Expression {
	  
	int num;
    int lineNumber;

	public EInteger(int n, int linenum) {
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
		Type t = new TInteger(num);
		return t;
	}

	public float getNum(){
        return num;
     }

	public void setNum(int n){
        num=n;
     }
};