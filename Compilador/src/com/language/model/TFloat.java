package com.language.model;

public class TFloat extends Type{
    private float num;

	public TFloat(float n){
        num = n;
    }

    public float getValue(){
        return num;
    }
    public void setValue(float n){
        num=n;
    }

    public int getType(){
        return 1;   //1-typeNumber
    }

    public void print(){
    }
	@Override
	protected TypeEnum getTypeEnum() {
		return TypeEnum.FLOAT;
	}

	@Override
	protected String getAsString() {
		return Float.valueOf(num).toString();
	}

};
