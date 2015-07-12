package com.language.model.type;

import com.language.model.type.TypeEnum;

public abstract class Type {
	public abstract int getType();
	public abstract void print();
	public abstract TypeEnum getTypeEnum();
	public abstract String getAsString();
	public abstract boolean getBooleanValue();

}
