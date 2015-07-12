package com.language.model.type;

public class ContinueType extends Type{

    public int getType(){
        return 8;   //8-typeContinue
    }

    public void print(){
    }
    
	@Override
	public TypeEnum getTypeEnum() {
		return TypeEnum.CONTINUE;
	}

	@Override
	public String getAsString() {
		return "continue";
	}
	
	public boolean getBooleanValue(){
		return false;
	}


	public boolean equals(ContinueType type) {
		return true;
	}
	
	
}