package com.language.model.type;

import com.language.model.type.Type;
import com.language.model.type.TypeEnum;

public class TInteger extends Type{
    private int num;

	public TInteger(int n){
        num = n;
    }

    public int getValue(){
        return num;
    }
    public void setValue(int n){
        num=n;
    }

    public int getType(){
        return 0;   //0-typeInteger
    }

    public void print(){
    }
    
	@Override
	protected TypeEnum getTypeEnum() {
		return TypeEnum.INTEGER;
	}

	@Override
	protected String getAsString() {
		return Integer.valueOf(num).toString();
	}

};
