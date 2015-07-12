package com.language.model.type;


public class BreakType extends Type{

    public int getType(){
        return 7;   //7-typeBreak
    }

    public void print(){
    }
    
	@Override
	public TypeEnum getTypeEnum() {
		return TypeEnum.BREAK;
	}

	@Override
	public String getAsString() {
		return "break";
	}
	
	public boolean getBooleanValue(){
		return false;
	}

}