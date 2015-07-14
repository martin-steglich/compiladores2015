package com.language.model.type;

import java.util.ArrayList;
import com.language.model.type.Type;
import com.language.model.type.TypeEnum;

public class ListType extends Type {
	
	private ArrayList<Type> list;
	
	public ListType() {
		list = new ArrayList<Type>();
		
	}

	public ListType(ArrayList<Type> list){
        this.list = list;
    }

    public ArrayList<Type> getList(){
        return list;
    }
    
    public void setList(ArrayList<Type> l){
        list=l;
    }

    public int getType(){
        return 6;   //6-TypeList
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
		l += "]";
		return l;
	}
	
	public boolean getBooleanValue(){
		return false;
	}
	
	public boolean equals(ListType obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListType other = (ListType) obj;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		return true;
	}
	
}
