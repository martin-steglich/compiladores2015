package com.language.model.type;

import java.util.Map;
import java.util.Map.Entry;

import com.language.model.expression.Expression;

public class DictionaryType extends Type{
	
	private Map<Type, Type> dictionary;
	
    public DictionaryType(Map<Type, Type> dictionary) {
		this.dictionary = dictionary;
	}
    
        
	public Map<Type, Type> getDictionary() {
		return dictionary;
	}


	public void setDictionary(Map<Type, Type> dictionary) {
		this.dictionary = dictionary;
	}



	public int getType(){
        return 10;   //1-typeDict
    }

    public void print(){
    }
    
	@Override
	public TypeEnum getTypeEnum() {
		return TypeEnum.DICT;
	}

	@Override
	public String getAsString() {
		String dic = "{";
		int length = dictionary.size();
		for (Entry<Type, Type> entry : dictionary.entrySet()){
			dic += entry.getKey().getAsString() + ":" + entry.getValue().getAsString();
			if(length > 1){
				dic += ",";
			}
			length--;
		}
		dic += "}";
		return dic;
	}
	
	public boolean getBooleanValue(){
		return false;
	}

	public boolean equals(DictionaryType obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DictionaryType other = (DictionaryType) obj;
		if (dictionary == null) {
			if (other.dictionary != null)
				return false;
		} else if (!dictionary.equals(other.dictionary))
			return false;
		return true;
	}
	
	
}
