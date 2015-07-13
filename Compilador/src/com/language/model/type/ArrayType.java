package com.language.model.type;

import com.language.model.type.Type;

import java.util.ArrayList;
import java.util.List;

import com.language.model.expression.Expression;

public class ArrayType extends Type {
	private List<Expression> list;
	
	public ArrayType() {
		list = new ArrayList<Expression>();
		
	}

	public ArrayType(List<Expression> list){
        this.list = list;
    }

    public List<Expression> getList(){
        return list;
    }
    public void setList(List<Expression> l){
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
		//
	}
	
	@Override
	public String getAsString() {
		//
	}
}
