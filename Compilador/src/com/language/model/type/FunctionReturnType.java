package com.language.model.type;

import java.util.List;

public class FunctionReturnType extends Type {

	private List<Type> types;
	
	public FunctionReturnType(List<Type> t){
        types = t;
    }
	
	public List<Type> getValue(){
        return types;
    }
	
	public void setValue(List<Type> t){
        types = t;
    }
	
	public int getType(){
        return 12;
    }
	
	public void print(){
    }
	
	public boolean getBooleanValue(){
		return false;
	}
	
	@Override
	public TypeEnum getTypeEnum() {
		return TypeEnum.RETURN;
	}

	@Override
	public String getAsString() {
		if (types.size() == 1){
			return types.get(0).getAsString();
		}else{
			String t = "(";
			int length = types.size();
			for (Type type : types){
				t += type.getAsString();
				if((length > 1) || (types.size() == 1)){
					t += ",";
				}
				length--;
			}
			t += ")";
			return t;
		}
		
	}
}
