package com.language.model.type;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public class TupleType extends Type{
	
	private ArrayList<Type> tuple;
	
    public TupleType(ArrayList<Type> tuple) {
		this.tuple = tuple;
	}
    
        
	public ArrayList<Type> getTuple() {
		return tuple;
	}

	public void setTuple(ArrayList<Type> tuple) {
		this.tuple = tuple;
	}

	public int getType(){
        return 11;   //1-typeTuple
    }

    public void print(){
    }
    
	@Override
	public TypeEnum getTypeEnum() {
		return TypeEnum.TUPLE;
	}

	@Override
	public String getAsString() {
		String tup = "(";
		int length = tuple.size();
		for (Type type : tuple){
			tup += type.getAsString();
			if((length > 1) || (tuple.size() == 1)){
				tup += ",";
			}
			length--;
		}
		tup += ")";
		return tup;
	}
	
	public boolean getBooleanValue(){
		return false;
	}

	public boolean equals(TupleType obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		TupleType other = (TupleType) obj;
		if (tuple == null) {
			if (other.tuple != null)
				return false;
		} else if (!tuple.equals(other.tuple))
			return false;
		return true;
	}
	
	
}
	