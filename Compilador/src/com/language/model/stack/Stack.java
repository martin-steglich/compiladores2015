package com.language.model.stack;

import java.util.ArrayList;
import java.util.List;

import com.language.model.type.Type;
import com.language.model.stack.VariablesList;

public class Stack {
	
	private List<VariablesList> stackList;
	
	Stack(){
    	stackList = new ArrayList<VariablesList>();
     }
	
	public  void openScope() {
        VariablesList l = new VariablesList();
        stackList.add(l);
    }
	
	public  void closeScope(){
        stackList.remove(stackList.size()-1);
    }
	
	public void addVariableToActualScope(String id, Type t){
    	if(stackList!=null && !stackList.isEmpty())
    		stackList.get(stackList.size()-1).addVariable(id, t);
    }
	
	public Type findTypeValue(String id){
    	Type t = null;
    	for(int j = stackList.size() - 1; j >= 0; j--){
    		Type aux =stackList.get(j).findVariable(id); 
    		if(aux!=null){
    			t = aux;
    			break;
    		}
    	}
    	return t;
    }
	
	public  void replaceTypeValue(String id,Type t){
    	for(int j = stackList.size() - 1; j >= 0; j--){
    		Type aux =stackList.get(j).findVariable(id); 
    		if(aux!=null){
    			stackList.get(j).addVariable(id,t);
    			break;
    		}
    	}
    }
	
	public Type findInActualScope(String id){
    	VariablesList vList = stackList.get(stackList.size()-1);
    	return(vList.findVariable(id));
    }
	
	public void printStack(){
        System.out.println("-------Stack--------");
        for(int j = stackList.size() - 1; j >= 0; j--){
        	System.out.println("Scope: " + j);
        	stackList.get(j).printList();
        }
        System.out.println("------fin Stack-----");
    }
	
}
