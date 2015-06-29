package com.language.model;

class TNull extends Type{
    
    protected boolean value;

    public TNull(){
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
	protected TypeEnum getTypeEnum() {
		return TypeEnum.NULL;
	}

	@Override
	protected String getAsString() {
		return "null";
	}

};
