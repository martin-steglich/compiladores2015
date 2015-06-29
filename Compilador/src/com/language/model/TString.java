package com.language.model;

public class TString extends Type{
    
	public String str;

	public TString(String s){
        str = s;
    }

	public String getValue(){
            return str;
        }
	public int getType(){
        return 2;
    }
	
	public void print(){
    }
	
	@Override
	protected TypeEnum getTypeEnum() {
		return TypeEnum.STRING;
	}

	@Override
	protected String getAsString() {
		return str;
	}

};

