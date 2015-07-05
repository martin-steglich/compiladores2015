package com.language.model.type;

import com.language.model.type.TypeEnum;

public abstract class Type {
	protected abstract int getType();
	protected abstract void print();
	protected abstract TypeEnum getTypeEnum();
	protected abstract String getAsString();

}
