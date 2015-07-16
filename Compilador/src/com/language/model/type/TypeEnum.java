package com.language.model.type;


public enum TypeEnum {
	INTEGER(0), /**/
	FLOAT(1), /**/
	LONG(2),	
	STRING(3), /**/
	NONE(4), /**/
	BOOLEAN(5), /**/
	LIST(6), /**/
	BREAK(7), /**/
	CONTINUE(8), /**/
	FUNCTION(9),
	DICT(10),
	TUPLE(11), /**/
	RETURN(12);
	int value;

	TypeEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	@Override
	public String toString(){
		String name = "";
		switch(ordinal()){
		case 0: 
			name = "int"; 
			break;
		case 1:
			name = "float";
			break;
		case 2:
			name = "long";
			break; 
		case 3:
			name = "str";
			break;
		case 4:
			name = "None";
			break;
		case 5:
			name = "bool";
			break;
		case 6:
			name = "list";
			break;
		case 9:
			name = "function";
			break;
		case 10:
			name = "dict";
			break;
		case 11:
			name = "tuple";
			break;
		case 12:
			name = "return";
			break;
		}
		
		return name;
	}
	
}