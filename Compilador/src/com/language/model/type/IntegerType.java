package com.language.model.type;

import com.language.model.type.Type;
import com.language.model.type.TypeEnum;

public class IntegerType extends Type{
    private int num;

	public IntegerType(int n){
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
	public TypeEnum getTypeEnum() {
		return TypeEnum.INTEGER;
	}

	@Override
	public String getAsString() {
		return Integer.valueOf(num).toString();
	}
	
	public boolean getBooleanValue(){
		return num != 0;
	}


	public boolean equals(IntegerType obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IntegerType other = (IntegerType) obj;
		if (num != other.num)
			return false;
		return true;
	}

	
}
