package com.language.model.expression;

import com.language.exceptions.CompilerException;
import com.language.model.type.BooleanType;
import com.language.model.type.FloatType;
import com.language.model.type.IntegerType;
import com.language.model.type.LongType;
import com.language.model.type.StringType;
import com.language.model.type.Type;

public class UnaryOperationExpression extends Expression {

	Expression  expression;
	String op;
    int lineNumber;

	public UnaryOperationExpression(String type, Expression exp, int lineNumber) {
		expression = exp;
		op = type; 
		this.lineNumber = lineNumber;
	};

	public void printExp(){
		System.out.print("Op : ");
		System.out.print(op);
		expression.printExp();
		System.out.println();
	}

	public String getType(){
		return "Type UnaryOperationExpression";
	}

	public Type evaluate() throws CompilerException {
		Type res = null;
		Type exp = expression.evaluate();

		if (exp == null){
		    throw new CompilerException(lineNumber, "La funcion evaluada no tiene valor de retorno");
		} else {
			boolean isInt = exp.getType() == 0;
			boolean isFloat = exp.getType() == 1;
			boolean isLong = exp.getType() == 2;
			boolean isString = exp.getType() == 3;
			boolean isBoolean = exp.getType() == 5;	
			
			if(op.equals("-")){
				if(isInt){
					int num = ((IntegerType)exp).getValue() * (-1);
					res = new IntegerType(num);
				} else if(isFloat){
					float num = ((FloatType)exp).getValue() * (-1);
					res = new FloatType(num);
				} else if(isLong){
					long num = ((LongType)exp).getValue() * (-1);
					res = new LongType(num);
				} else if(isBoolean){
					int num = ((BooleanType)exp).getEquivalentInt() * (-1);
					res = new IntegerType(num);
				} else {
					throw new CompilerException(lineNumber, "Tipo de datos no esperado para la operacion negacion");
				}
			} else if(op.equals("~")){
				if(isInt){
					int num = ~((IntegerType)exp).getValue();
					res = new IntegerType(num);
				} else if (isLong){
					long num = ~((IntegerType)exp).getValue();
					res = new LongType(num);
				} else if (isBoolean){
					int num = ~((BooleanType)exp).getEquivalentInt();
					res = new IntegerType(num);
				} else {
					throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion not");
				}
			} else if (op.equals("not")){
				if(isInt){
					int num = ((IntegerType)exp).getValue();
					if(num == 0)
						res = new BooleanType(true);
					else
						res = new BooleanType(false);
				} else if(isFloat){
					float num = ((FloatType)exp).getValue();
					if(num == 0)
						res = new BooleanType(true);
					else
						res = new BooleanType(false);
				} else if(isLong){
					long num = ((LongType)exp).getValue();
					if(num == 0)
						res = new BooleanType(true);
					else
						res = new BooleanType(false);
				} else if(isString){
					String text = ((StringType)exp).getText();
					if(text.isEmpty())
						res = new BooleanType(true);
					else
						res = new BooleanType(false);
				} else if(isBoolean){
					boolean bool = !((BooleanType)exp).getValue();
					res = new BooleanType(bool);
				} else {
					throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion not");
				}
			}
		}
		return res;
	}
	

}
