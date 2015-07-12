package com.language.model.type;

import com.language.model.type.Type;
import com.language.model.type.TypeEnum;

public class NoneType extends Type{
    
    protected boolean value;

    public NoneType(){
        value = false;
    }
    public boolean getValue(){
            return value;
    }
    public int getType(){
        return 4; //4- typeNil
    }
    public void print(){
    }
    
	@Override
	public TypeEnum getTypeEnum() {
		return TypeEnum.NONE;
	}

	@Override
	public String getAsString() {
		return "none";
	}
	
	public boolean getBooleanValue(){
		return false;
	}
};
