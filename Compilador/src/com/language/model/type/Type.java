package com.language.model.type;

import com.language.model.type.TypeEnum;

public abstract class Type {
	public abstract int getType();
	public abstract void print();
	public abstract TypeEnum getTypeEnum();
	public abstract String getAsString();
	public abstract boolean getBooleanValue();
	
	@Override
	public boolean equals(Object obj){
		
		if (!(obj instanceof Type))
			return false;
		
		Type type = (Type) obj;
		
		if(this.getType() != type.getType()){
			float thisAux;
			float typeAux;
			if(this instanceof BooleanType){
				thisAux = (float)((BooleanType)this).getEquivalentInt();
			} else if (this instanceof IntegerType){
				thisAux = (float)((IntegerType)this).getValue();
			} else if (this instanceof LongType){
				thisAux = (float)((LongType)this).getValue();
			} else
				return false;
			
			if(type instanceof BooleanType){
				typeAux = (float)((BooleanType)type).getEquivalentInt();
			} else if (type instanceof IntegerType){
				typeAux = (float)((IntegerType)type).getValue();
			} else if (type instanceof LongType){
				typeAux = (float)((LongType)type).getValue();
			} else
				return false;
			
			return thisAux == typeAux;
			
		}
		if(this instanceof IntegerType)
			return ((IntegerType)this).equals((IntegerType)type);
		
		if(this instanceof FloatType)
			return ((FloatType)this).equals((FloatType)type);
		
		if(this instanceof LongType)
			return ((LongType)this).equals((LongType)type);
		
		if(this instanceof StringType)
			return ((StringType)this).equals((StringType)type);
		
		if(this instanceof BooleanType)
			return ((BooleanType)this).equals((BooleanType)type);

		//TODO descomentar esto
		if(this instanceof ListType)
			return ((ListType)this).equals((ListType)type);
		
//		if(this instanceof FunctionType)
//			return false;
		
		if(this instanceof DictionaryType)
			return ((DictionaryType)this).equals((DictionaryType)type);
		
//		if(this instanceof TupleType)
//			return ((TupleType)this).equals((TupleType)type);
		
		return true;
	};
	
	@Override
	public int hashCode() {
		return 1;
	}

}
