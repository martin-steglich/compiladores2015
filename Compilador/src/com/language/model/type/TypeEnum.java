package com.language.model.type;

public enum TypeEnum {
	INTEGER(0), /**/
	FLOAT(1), /**/
	LONG(2),	
	STRING(3), /**/
	NULL(4), /**/
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

}