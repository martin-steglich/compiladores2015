package com.language.model.type;

public enum TypeEnum {
	INTEGER(0), /**/
	FLOAT(1), /**/
	LONG(2),	
	STRING(3), /**/
	NONE(4), /**/
	BOOLEAN(5), /**/
	ARRAY(6), /**/
	BREAK(7), /**/
	CONTINUE(8), /**/
	FUNCTION(9); /**/

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
			name = "null";
			break;
		case 5:
			name = "bool";
			break;
		}
		
		return name;
	}

}