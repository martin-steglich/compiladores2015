package com.language.model.type;

import com.language.model.type.Type;

import java.util.ArrayList;


public class ArrayType extends Type {
	
	private ArrayList<Type> list;
	
	public ArrayType() {
		list = new ArrayList<Type>();
		
	}

	public ArrayType(ArrayList<Type> list){
        this.list = list;
    }

    public ArrayList<Type> getList(){
        return list;
    }
    public void setList(ArrayList<Type> l){
        list=l;
    }

    public int getType(){
        return 6;   //6-TypeArray
    }

    public void print(){
    	//
    }
    
	@Override
	public TypeEnum getTypeEnum() {
		return TypeEnum.LIST;
	}
	
	@Override
	public String getAsString() {
		String l = "[";
		int length = list.size();
		for (Type type : list){
			l += type.getAsString();
			if(length > 1) {
				l += ",";
			}
			length--;
		}
		l += ")";
		return l;
	}
	
	public boolean getBooleanValue(){
		return false;
	}
}
