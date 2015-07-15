package com.language.model.stack;

import java.util.HashMap;

import com.language.model.type.Type;
import com.language.model.stack.StackVariable;

public class VariablesList {

	private HashMap<String,StackVariable> varList; 
	
	VariablesList(){
    	varList = new HashMap<String,StackVariable>();
    }
	
	public void addVariable(String id, Type type){
    	StackVariable var = new StackVariable();
    	var.setId(id);
    	var.setType(type);
    	varList.put(id, var);
    }
	
	public Type findVariable(String id){
			Type t = null;
			StackVariable sVar =varList.get(id);
			if(sVar!=null){
				t = sVar.getType();
			}	
			return t;
	}
	
	public void printList(){
        for(StackVariable sVar : varList.values()){
        	System.out.println("Variable: " + sVar.getId());
        }
    }
	
}
