package com.language.model.type;

public class FloatType extends Type{
    private float num;

	public FloatType(float n){
        num = n;
    }

    public float getValue(){
        return num;
    }
    public void setValue(float n){
        num=n;
    }

    public int getType(){
        return 1;   //1-typeFloat
    }

    public void print(){
    }
    
	@Override
	public TypeEnum getTypeEnum() {
		return TypeEnum.FLOAT;
	}

	@Override
	public String getAsString() {
		return Float.valueOf(num).toString();
	}
	
	public boolean getBooleanValue(){
		return num != 0;
	}
}
