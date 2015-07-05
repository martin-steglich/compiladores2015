package com.language.model.type;

public enum TypeEnum {
	INTEGER(0), /**/
	FLOAT(1), /**/
	STRING(2), /**/
	NULL(4), /**/
	BOOLEAN(3), /**/
	ARRAY(5), /**/
	BREAK(6), /**/
	CONTINUE(7), /**/
	FUNCTION(8); /**/

	int value;

	TypeEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}