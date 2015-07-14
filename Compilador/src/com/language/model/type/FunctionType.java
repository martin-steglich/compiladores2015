package com.language.model.type;

public class FunctionType extends Type{

	private Type type;
	
	public FunctionType(Type t){
        type = t;
    }
	
	public Type getValue(){
        return type;
    }
	
	public void setValue(Type t){
        type=t;
    }
	
	public int getType(){
        return 9;
    }
	
	public void print(){
    }
	
	public boolean getBooleanValue(){
		return false;
	}
	
	@Override
	public TypeEnum getTypeEnum() {
		return TypeEnum.FUNCTION;
	}

	@Override
	public String getAsString() {
		return "";
	}
}
