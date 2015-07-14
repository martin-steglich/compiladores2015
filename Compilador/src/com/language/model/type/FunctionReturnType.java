package com.language.model.type;

import java.util.List;

public class FunctionReturnType extends Type {

	private List<Type> types;
	
	public FunctionReturnType(List<Type> t){
        types = t;
    }
	
	public List<Type> getValue(){
        return types;
    }
	
	public void setValue(List<Type> t){
        types = t;
    }
	
	public int getType(){
        return 12;
    }
	
	public void print(){
    }
	
	public boolean getBooleanValue(){
		return false;
	}
	
	@Override
	public TypeEnum getTypeEnum() {
		return TypeEnum.RETURN;
	}

	@Override
	public String getAsString() {
		return "";
	}
}
