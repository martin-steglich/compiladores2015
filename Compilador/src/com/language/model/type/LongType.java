package com.language.model.type;

public class LongType extends Type{
    private long num;

	public LongType(long n){
        num = n;
    }

    public long getValue(){
        return num;
    }
    public void setValue(long n){
        num=n;
    }

    public int getType(){
        return 2;   //2-typeLong
    }

    public void print(){
    }
    
	@Override
	public TypeEnum getTypeEnum() {
		return TypeEnum.LONG;
	}

	@Override
	public String getAsString() {
		return Long.valueOf(num).toString();
	}
	
	public boolean getBooleanValue(){
		return num != 0;
	}

}
