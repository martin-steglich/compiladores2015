package com.language.model.stack;

import com.language.model.type.Type;

public class StackVariable {

	private String id;
    private Type type;  

    StackVariable(){
   	}

    void setId(String s){
    	id = s;
    }
    
    void setType(Type t){
    	type = t;
    }

    String getId(){
    	return id;
    }

    Type getType(){
    	return type;
    }
    
}
