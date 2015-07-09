package com.language.model.type;

import com.language.model.type.Type;
import com.language.model.type.TypeEnum;

public class BooleanType extends Type {

	protected boolean value;

	public BooleanType(boolean b) {
		value = b;
	}

	public boolean getValue() {
		return value;
	}

	public int getType() {
		return 5; // 5-typeBool
	}

	public void print() {
		if (value) {
			System.out.println("True");
		} else {
			System.out.println("False");
		}
	}

	@Override
	public TypeEnum getTypeEnum() {
		return TypeEnum.BOOLEAN;
	}

	@Override
	public String getAsString() {
		if (value) {
			return "True";
		} else {
			return "False";
		}
	}
	
	public int getEquivalentInt(){
		if(value)
			return 1;
		else
			return 0;
	}

}
