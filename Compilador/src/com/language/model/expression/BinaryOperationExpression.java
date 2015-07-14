package com.language.model.expression;

import java.util.ArrayList;

import com.language.exceptions.CompilerException;
import com.language.model.type.BooleanType;
import com.language.model.type.FloatType;
import com.language.model.type.IntegerType;
import com.language.model.type.ListType;
import com.language.model.type.LongType;
import com.language.model.type.StringType;
import com.language.model.type.TupleType;
import com.language.model.type.Type;


public class BinaryOperationExpression extends Expression {

	Expression  left;
	Expression right;
	String op;
    int lineNumber;

	public BinaryOperationExpression(Expression lop, String type, Expression rop) {
		left = lop;
		right = rop;
		op = type; 
		//lineNumber = linenum;
	};

	public void printExp(){
		System.out.print("Op : ");
		left.printExp();
		System.out.print(" "+op);
		right.printExp();
		System.out.println();
	}

	public String getType(){
		return "Type BinaryOperationExpression";
	}

	public String Convert (float number){
      return Float.toString(number);
    }
	
	private Type suma(Type izq, Type der) throws CompilerException{
		Type res = null;
		boolean intInt = (izq.getType() == 0) && (der.getType() == 0);
		boolean intLong = (izq.getType() == 0) && (der.getType() == 2);
		boolean intFloat = (izq.getType() == 0) && (der.getType() == 1);
		boolean intBoolean = (izq.getType() == 0) && (der.getType() == 5);
		
		boolean stringString = (izq.getType() == 3) && (der.getType() == 3);
		
		boolean longInt = (izq.getType() == 2) && (der.getType() == 0);
		boolean longLong = (izq.getType() == 2) && (der.getType() == 2);
		boolean longFloat = (izq.getType() == 2) && (der.getType() == 1);
		boolean longBoolean = (izq.getType() == 2) && (der.getType() == 5);
		
		boolean floatInt = (izq.getType() == 1) && (der.getType() == 0);
		boolean floatLong = (izq.getType() == 1) && (der.getType() == 2);
		boolean floatFloat = (izq.getType() == 1) && (der.getType() == 1);
		boolean floatBoolean = (izq.getType() == 1) && (der.getType() == 5);
		
		boolean booleanInt = (izq.getType() == 5) && (der.getType() == 0);
		boolean booleanLong = (izq.getType() == 5) && (der.getType() == 2);
		boolean booleanFloat = (izq.getType() == 5) && (der.getType() == 1);
		boolean booleanBoolean = (izq.getType() == 5) && (der.getType() == 5);
		
		boolean tupleTuple = (izq.getType() == 11) && (der.getType() == 11);
		
		boolean listList = (izq.getType() == 6) && (der.getType() == 6);
		
		if(intInt){
			int resultado = ((IntegerType)izq).getValue() + ((IntegerType)der).getValue();
			res = new IntegerType(resultado);
		}
		else if(intLong){
			long resultado = ((IntegerType)izq).getValue() + ((LongType)der).getValue();
			res = new LongType(resultado);
		}
		else if(intFloat){
			float resultado = ((IntegerType)izq).getValue() + ((FloatType)der).getValue();
			res = new FloatType(resultado);
		}
		else if(intBoolean){
			int resultado = ((IntegerType)izq).getValue() + ((BooleanType)der).getEquivalentInt();
			res = new IntegerType(resultado);
		}
		else if(stringString){
			String resultado = ((StringType)izq).getText() + ((StringType)der).getText();
			res = new StringType(resultado);
		}
		else if(longInt){
			long resultado = ((LongType)izq).getValue() + ((IntegerType)der).getValue();
			res = new LongType(resultado);
		}
		else if(longLong){
			long resultado = ((LongType)izq).getValue() + ((LongType)der).getValue();
			res = new LongType(resultado);
		}
		else if(longFloat){
			float resultado = ((LongType)izq).getValue() + ((FloatType)der).getValue();
			res = new FloatType(resultado);
		}
		else if(longBoolean){
			long resultado = ((LongType)izq).getValue() + ((BooleanType)der).getEquivalentInt();
			res = new LongType(resultado);
		}
		else if(floatInt){
			float resultado = ((FloatType)izq).getValue() + ((IntegerType)der).getValue();
			res = new FloatType(resultado);
		}
		else if(floatLong){
			float resultado = ((FloatType)izq).getValue() + ((LongType)der).getValue();
			res = new FloatType(resultado);
		}
		else if(floatFloat){
			float resultado = ((FloatType)izq).getValue() + ((FloatType)der).getValue();
			res = new FloatType(resultado);
		}
		else if(floatBoolean){
			float resultado = ((FloatType)izq).getValue() + ((BooleanType)der).getEquivalentInt();
			res = new FloatType(resultado);
		}
		else if(booleanInt){
			int resultado = ((BooleanType)izq).getEquivalentInt() + ((IntegerType)der).getValue();
			res = new IntegerType(resultado);
		}
		else if(booleanLong){
			long resultado = ((BooleanType)izq).getEquivalentInt() + ((LongType)der).getValue();
			res = new LongType(resultado);
		}
		else if(booleanFloat){
			float resultado = ((BooleanType)izq).getEquivalentInt() + ((FloatType)der).getValue();
			res = new FloatType(resultado);
		}
		else if(booleanBoolean){
			int resultado = ((BooleanType)izq).getEquivalentInt() + ((BooleanType)der).getEquivalentInt();
			res = new IntegerType(resultado);
		} else if(tupleTuple){
			ArrayList<Type> resultado = new ArrayList<>();
			ArrayList<Type> tupleIzq = ((TupleType)izq).getTuple();
			ArrayList<Type> tupleDer = ((TupleType)der).getTuple();
			resultado.addAll(tupleIzq);
			resultado.addAll(tupleDer);
			res = new TupleType(resultado);
		}
		else if(listList){
			ArrayList<Type> resultado = new ArrayList<>();
			ArrayList<Type> listIzq = ((ListType)izq).getList();
			ArrayList<Type> listDer = ((ListType)der).getList();
			resultado.addAll(listIzq);
			resultado.addAll(listDer);
			res = new ListType(resultado);
		}
		else{
			throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion suma");
		}
		
		return res;
		
	}
	private Type resta(Type izq, Type der) throws CompilerException{
		Type res = null;
		boolean intInt = (izq.getType() == 0) && (der.getType() == 0);
		boolean intLong = (izq.getType() == 0) && (der.getType() == 2);
		boolean intFloat = (izq.getType() == 0) && (der.getType() == 1);
		boolean intBoolean = (izq.getType() == 0) && (der.getType() == 5);
		
		boolean longInt = (izq.getType() == 2) && (der.getType() == 0);
		boolean longLong = (izq.getType() == 2) && (der.getType() == 3);
		boolean longFloat = (izq.getType() == 2) && (der.getType() == 1);
		boolean longBoolean = (izq.getType() == 2) && (der.getType() == 5);
		
		boolean floatInt = (izq.getType() == 1) && (der.getType() == 0);
		boolean floatLong = (izq.getType() == 1) && (der.getType() == 2);
		boolean floatFloat = (izq.getType() == 1) && (der.getType() == 1);
		boolean floatBoolean = (izq.getType() == 1) && (der.getType() == 5);
		
		boolean booleanInt = (izq.getType() == 5) && (der.getType() == 0);
		boolean booleanLong = (izq.getType() == 5) && (der.getType() == 2);
		boolean booleanFloat = (izq.getType() == 5) && (der.getType() == 1);
		boolean booleanBoolean = (izq.getType() == 5) && (der.getType() == 5);
		
		if(intInt){
			int resultado = ((IntegerType)izq).getValue() - ((IntegerType)der).getValue();
			res = new IntegerType(resultado);
		}
		else if(intLong){
			long resultado = ((IntegerType)izq).getValue() - ((LongType)der).getValue();
			res = new LongType(resultado);
		}
		else if(intFloat){
			float resultado = ((IntegerType)izq).getValue() - ((FloatType)der).getValue();
			res = new FloatType(resultado);
		}
		else if(intBoolean){
			int resultado = ((IntegerType)izq).getValue() - ((BooleanType)der).getEquivalentInt();
			res = new IntegerType(resultado);
		}
		else if(longInt){
			long resultado = ((LongType)izq).getValue() - ((IntegerType)der).getValue();
			res = new LongType(resultado);
		}
		else if(longLong){
			long resultado = ((LongType)izq).getValue() - ((LongType)der).getValue();
			res = new LongType(resultado);
		}
		else if(longFloat){
			float resultado = ((LongType)izq).getValue() - ((FloatType)der).getValue();
			res = new FloatType(resultado);
		}
		else if(longBoolean){
			long resultado = ((LongType)izq).getValue() - ((BooleanType)der).getEquivalentInt();
			res = new LongType(resultado);
		}
		else if(floatInt){
			float resultado = ((FloatType)izq).getValue() - ((IntegerType)der).getValue();
			res = new FloatType(resultado);
		}
		else if(floatLong){
			float resultado = ((FloatType)izq).getValue() - ((LongType)der).getValue();
			res = new FloatType(resultado);
		}
		else if(floatFloat){
			float resultado = ((FloatType)izq).getValue() - ((FloatType)der).getValue();
			res = new FloatType(resultado);
		}
		else if(floatBoolean){
			float resultado = ((FloatType)izq).getValue() - ((BooleanType)der).getEquivalentInt();
			res = new FloatType(resultado);
		}
		else if(booleanInt){
			int resultado = ((BooleanType)izq).getEquivalentInt() - ((IntegerType)der).getValue();
			res = new IntegerType(resultado);
		}
		else if(booleanLong){
			long resultado = ((BooleanType)izq).getEquivalentInt() - ((LongType)der).getValue();
			res = new LongType(resultado);
		}
		else if(booleanFloat){
			float resultado = ((BooleanType)izq).getEquivalentInt() - ((FloatType)der).getValue();
			res = new FloatType(resultado);
		}
		else if(booleanBoolean){
			int resultado = ((BooleanType)izq).getEquivalentInt() - ((BooleanType)der).getEquivalentInt();
			res = new IntegerType(resultado);
		}
	    else {
            throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion resta");
		}
		
		return res;
	}
	private Type multiplicacion(Type izq, Type der) throws CompilerException{
		Type res = null;
		boolean intInt = (izq.getType() == 0) && (der.getType() == 0);
		boolean intString = (izq.getType() == 0) && (der.getType() == 3);
		boolean intLong = (izq.getType() == 0) && (der.getType() == 2);
		boolean intFloat = (izq.getType() == 0) && (der.getType() == 1);
		boolean intBoolean = (izq.getType() == 0) && (der.getType() == 5);
		boolean intTuple = (izq.getType() == 0) && (der.getType() == 11);
		boolean intList = (izq.getType() == 0) && (der.getType() == 6);
		
		boolean stringInt = (izq.getType() == 3) && (der.getType() == 0);
		boolean stringLong = (izq.getType() == 3) && (der.getType() == 2);
		boolean stringBoolean = (izq.getType() == 3) && (der.getType() == 5);
		
		boolean longInt = (izq.getType() == 2) && (der.getType() == 0);
		boolean longString = (izq.getType() == 2) && (der.getType() == 3);
		boolean longLong = (izq.getType() == 2) && (der.getType() == 3);
		boolean longFloat = (izq.getType() == 2) && (der.getType() == 1);
		boolean longBoolean = (izq.getType() == 2) && (der.getType() == 5);
		boolean longTuple = (izq.getType() == 2) && (der.getType() == 11);
		boolean longList = (izq.getType() == 2) && (der.getType() == 6);
		
		boolean floatInt = (izq.getType() == 1) && (der.getType() == 0);
		boolean floatLong = (izq.getType() == 1) && (der.getType() == 2);
		boolean floatFloat = (izq.getType() == 1) && (der.getType() == 1);
		boolean floatBoolean = (izq.getType() == 1) && (der.getType() == 5);
		
		
		boolean booleanInt = (izq.getType() == 5) && (der.getType() == 0);
		boolean booleanString = (izq.getType() == 5) && (der.getType() == 3);
		boolean booleanLong = (izq.getType() == 5) && (der.getType() == 2);
		boolean booleanFloat = (izq.getType() == 5) && (der.getType() == 1);
		boolean booleanBoolean = (izq.getType() == 5) && (der.getType() == 5);
		boolean booleanTuple = (izq.getType() == 5) && (der.getType() == 11);
		boolean booleanList = (izq.getType() == 5) && (der.getType() == 6);
		
		boolean tupleInt = (izq.getType() == 11) && (der.getType() == 0);
		boolean tupleLong = (izq.getType() == 11) && (der.getType() == 2);
		boolean tupleBoolean = (izq.getType() == 11) && (der.getType() == 5);
		
		boolean listInt = (izq.getType() == 6) && (der.getType() == 0);
		boolean listLong = (izq.getType() == 6) && (der.getType() == 2);
		boolean listBoolean = (izq.getType() == 6) && (der.getType() == 5);
		
		if(intInt){
			int resultado = ((IntegerType)izq).getValue() * ((IntegerType)der).getValue();
			res = new IntegerType(resultado);
		}
		else if(intLong){
			long resultado = ((IntegerType)izq).getValue() * ((LongType)der).getValue();
			res = new LongType(resultado);
		}
		else if(intFloat){
			float resultado = ((IntegerType)izq).getValue() * ((FloatType)der).getValue();
			res = new FloatType(resultado);
		}
		else if(intBoolean){
			int resultado = ((IntegerType)izq).getValue() * ((BooleanType)der).getEquivalentInt();
			res = new IntegerType(resultado);
		}
		else if(intString){
			int rep = ((IntegerType)izq).getValue();
			String resultado = "";
			for(int i = 1; i<= rep; i++)
				resultado += ((StringType)der).getText();
			res = new StringType(resultado);
		}else if(intTuple){
			int rep = ((IntegerType)izq).getValue();
			ArrayList<Type> tupleDer = ((TupleType)der).getTuple();
			ArrayList<Type> resultado = new ArrayList<>();
			for(int i = 1; i<= rep; i++)
				resultado.addAll(tupleDer);
			res = new TupleType(resultado);
		}else if(intList){
			int rep = ((IntegerType)izq).getValue();
			ArrayList<Type> listDer = ((ListType)der).getList();
			ArrayList<Type> resultado = new ArrayList<>();
			for(int i = 1; i<= rep; i++)
				resultado.addAll(listDer);
			res = new ListType(resultado);
		}
		else if(stringInt){
			int rep = ((IntegerType)der).getValue();
			String resultado = "";
			for(int i = 1; i<= rep; i++)
				resultado += ((StringType)izq).getText();
			res = new StringType(resultado);
		}
		else if(stringBoolean){
			int rep = ((BooleanType)der).getEquivalentInt();
			String resultado = "";
			for(int i = 1; i<= rep; i++)
				resultado += ((StringType)izq).getText();
			res = new StringType(resultado);
		}
		else if(stringLong){
			long rep = ((LongType)der).getValue();
			String resultado = "";
			for(long i = 1; i<= rep; i++)
				resultado += ((StringType)izq).getText();
			res = new StringType(resultado);
		}
		else if(longInt){
			long resultado = ((LongType)izq).getValue() * ((IntegerType)der).getValue();
			res = new LongType(resultado);
		}
		else if(longLong){
			long resultado = ((LongType)izq).getValue() * ((LongType)der).getValue();
			res = new LongType(resultado);
		}
		else if(longFloat){
			float resultado = ((LongType)izq).getValue() * ((FloatType)der).getValue();
			res = new FloatType(resultado);
		}
		else if(longBoolean){
			long resultado = ((LongType)izq).getValue() * ((BooleanType)der).getEquivalentInt();
			res = new LongType(resultado);
		}
		else if(longString){
			long rep = ((LongType)izq).getValue();
			String resultado = "";
			for(long i = 1; i<= rep; i++)
				resultado += ((StringType)der).getText();
			res = new StringType(resultado);
		} 
		else if(longTuple){
			long rep = ((LongType)izq).getValue();
			ArrayList<Type> tupleDer = ((TupleType)der).getTuple();
			ArrayList<Type> resultado = new ArrayList<>();
			for(long i = 1; i<= rep; i++)
				resultado.addAll(tupleDer);
			res = new TupleType(resultado);
		}else if(longList){
			long rep = ((LongType)izq).getValue();
			ArrayList<Type> listDer = ((ListType)der).getList();
			ArrayList<Type> resultado = new ArrayList<>();
			for(long i = 1; i<= rep; i++)
				resultado.addAll(listDer);
			res = new ListType(resultado);
		}
		else if(floatInt){
			float resultado = ((FloatType)izq).getValue() * ((IntegerType)der).getValue();
			res = new FloatType(resultado);
		}
		else if(floatLong){
			float resultado = ((FloatType)izq).getValue() * ((LongType)der).getValue();
			res = new FloatType(resultado);
		}
		else if(floatFloat){
			float resultado = ((FloatType)izq).getValue() * ((FloatType)der).getValue();
			res = new FloatType(resultado);
		}
		else if(floatBoolean){
			float resultado = ((FloatType)izq).getValue() * ((BooleanType)der).getEquivalentInt();
			res = new FloatType(resultado);
		}
		else if(booleanInt){
			int resultado = ((BooleanType)izq).getEquivalentInt() * ((IntegerType)der).getValue();
			res = new IntegerType(resultado);
		}
		else if(booleanLong){
			long resultado = ((BooleanType)izq).getEquivalentInt() * ((LongType)der).getValue();
			res = new LongType(resultado);
		}
		else if(booleanFloat){
			float resultado = ((BooleanType)izq).getEquivalentInt() * ((FloatType)der).getValue();
			res = new FloatType(resultado);
		}
		else if(booleanBoolean){
			int resultado = ((BooleanType)izq).getEquivalentInt() * ((BooleanType)der).getEquivalentInt();
			res = new IntegerType(resultado);
		}
		else if(booleanString){
			int rep = ((BooleanType)izq).getEquivalentInt();
			String resultado = "";
			for(int i = 1; i<= rep; i++)
				resultado += ((StringType)der).getText();
			res = new StringType(resultado);
		}
		else if(booleanTuple){
			int rep = ((BooleanType)izq).getEquivalentInt();
			ArrayList<Type> tupleDer = ((TupleType)der).getTuple();
			ArrayList<Type> resultado = new ArrayList<>();
			for(int i = 1; i<= rep; i++)
				resultado.addAll(tupleDer);
			res = new TupleType(resultado);
		}
		else if(booleanList){
			int rep = ((BooleanType)izq).getEquivalentInt();
			ArrayList<Type> listDer = ((ListType)der).getList();
			ArrayList<Type> resultado = new ArrayList<>();
			for(int i = 1; i<= rep; i++)
				resultado.addAll(listDer);
			res = new ListType(resultado);
		}
		else if(tupleInt){
			int rep = ((IntegerType)der).getValue();
			ArrayList<Type> resultado = new ArrayList<>();
			ArrayList<Type> tupleIzq = ((TupleType)izq).getTuple();
			for(int i = 1; i<= rep; i++)
				resultado.addAll(tupleIzq);
			res = new TupleType(resultado);
		}
		else if(tupleBoolean){
			int rep = ((BooleanType)der).getEquivalentInt();
			ArrayList<Type> resultado = new ArrayList<>();
			ArrayList<Type> tupleIzq = ((TupleType)izq).getTuple();
			for(int i = 1; i<= rep; i++)
				resultado.addAll(tupleIzq);
			res = new TupleType(resultado);
		}
		else if(tupleLong){
			long rep = ((LongType)der).getValue();
			ArrayList<Type> resultado = new ArrayList<>();
			ArrayList<Type> tupleIzq = ((TupleType)izq).getTuple();
			for(long i = 1; i<= rep; i++)
				resultado.addAll(tupleIzq);
			res = new TupleType(resultado);
		}
		else if(listInt){
			int rep = ((IntegerType)der).getValue();
			ArrayList<Type> resultado = new ArrayList<>();
			ArrayList<Type> listIzq = ((ListType)izq).getList();
			for(int i = 1; i<= rep; i++)
				resultado.addAll(listIzq);
			res = new ListType(resultado);
		}
		else if(listBoolean){
			int rep = ((BooleanType)der).getEquivalentInt();
			ArrayList<Type> resultado = new ArrayList<>();
			ArrayList<Type> listIzq = ((ListType)izq).getList();
			for(int i = 1; i<= rep; i++)
				resultado.addAll(listIzq);
			res = new TupleType(resultado);
		}
		else if(listLong){
			long rep = ((LongType)der).getValue();
			ArrayList<Type> resultado = new ArrayList<>();
			ArrayList<Type> listIzq = ((ListType)izq).getList();
			for(long i = 1; i<= rep; i++)
				resultado.addAll(listIzq);
			res = new TupleType(resultado);
		}
	    else {
            throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion multiplicacion");
		}
		
		return res;
	}
	private Type division(Type izq, Type der) throws CompilerException{
		Type res = null;
		boolean intInt = (izq.getType() == 0) && (der.getType() == 0);
		boolean intLong = (izq.getType() == 0) && (der.getType() == 2);
		boolean intFloat = (izq.getType() == 0) && (der.getType() == 1);
		boolean intBoolean = (izq.getType() == 0) && (der.getType() == 5);
		
		boolean longInt = (izq.getType() == 2) && (der.getType() == 0);
		boolean longLong = (izq.getType() == 2) && (der.getType() == 3);
		boolean longFloat = (izq.getType() == 2) && (der.getType() == 1);
		boolean longBoolean = (izq.getType() == 2) && (der.getType() == 5);
		
		boolean floatInt = (izq.getType() == 1) && (der.getType() == 0);
		boolean floatLong = (izq.getType() == 1) && (der.getType() == 2);
		boolean floatFloat = (izq.getType() == 1) && (der.getType() == 1);
		boolean floatBoolean = (izq.getType() == 1) && (der.getType() == 5);
		
		boolean booleanInt = (izq.getType() == 5) && (der.getType() == 0);
		boolean booleanLong = (izq.getType() == 5) && (der.getType() == 2);
		boolean booleanFloat = (izq.getType() == 5) && (der.getType() == 1);
		boolean booleanBoolean = (izq.getType() == 5) && (der.getType() == 5);
		
		if(intInt){
			int div = ((IntegerType)der).getValue();
			if(div == 0){
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			}
			int resultado = ((IntegerType)izq).getValue() / div ;
			res = new IntegerType(resultado);
		}
		else if(intLong){
			long div = ((LongType)der).getValue();
			if(div == 0l)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			
			long resultado = ((IntegerType)izq).getValue() / div ;
			res = new LongType(resultado);
		}
		else if(intFloat){
			float div = ((FloatType)der).getValue();
			if(div == 0f)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			
			float resultado = ((IntegerType)izq).getValue() / div;
			res = new FloatType(resultado);
		}
		else if(intBoolean){
			int div = ((BooleanType)der).getEquivalentInt();
			if(div == 0)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			
			int resultado = ((IntegerType)izq).getValue() / div;
			res = new IntegerType(resultado);
		}
		else if(longInt){
			int div = ((IntegerType)der).getValue();
			if(div == 0){
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			}
			long resultado = ((LongType)izq).getValue() / div;
			res = new LongType(resultado);
		}
		else if(longLong){
			long div = ((LongType)der).getValue();
			if(div == 0l)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			
			long resultado = ((LongType)izq).getValue() / div;
			res = new LongType(resultado);
		}
		else if(longFloat){
			float div = ((FloatType)der).getValue();
			if(div == 0f)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			float resultado = ((LongType)izq).getValue() / div;
			res = new FloatType(resultado);
		}
		else if(longBoolean){
			int div = ((BooleanType)der).getEquivalentInt();
			if(div == 0)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			
			long resultado = ((LongType)izq).getValue() / div;
			res = new LongType(resultado);
		}
		else if(floatInt){
			int div = ((IntegerType)der).getValue();
			if(div == 0){
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			}
			
			float resultado = ((FloatType)izq).getValue() / div;
			res = new FloatType(resultado);
		}
		else if(floatLong){
			long div = ((LongType)der).getValue();
			if(div == 0l)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			
			float resultado = ((FloatType)izq).getValue() / div;
			res = new FloatType(resultado);
		}
		else if(floatFloat){
			float div = ((FloatType)der).getValue();
			if(div == 0f)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			
			float resultado = ((FloatType)izq).getValue() / div;
			res = new FloatType(resultado);
		}
		else if(floatBoolean){
			int div = ((BooleanType)der).getEquivalentInt();
			if(div == 0)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			
			float resultado = ((FloatType)izq).getValue() / div;
			res = new FloatType(resultado);
		}
		else if(booleanInt){
			int div = ((IntegerType)der).getValue();
			if(div == 0){
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			}
			
			int resultado = ((BooleanType)izq).getEquivalentInt() / div;
			res = new IntegerType(resultado);
		}
		else if(booleanLong){
			long div = ((LongType)der).getValue();
			if(div == 0l)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			
			long resultado = ((BooleanType)izq).getEquivalentInt() / div;
			res = new LongType(resultado);
		}
		else if(booleanFloat){
			float div = ((FloatType)der).getValue();
			if(div == 0f)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			
			float resultado = ((BooleanType)izq).getEquivalentInt() / div;
			res = new FloatType(resultado);
		}
		else if(booleanBoolean){
			int div = ((BooleanType)der).getEquivalentInt();
			if(div == 0)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			
			int resultado = ((BooleanType)izq).getEquivalentInt() / div;
			res = new IntegerType(resultado);
		}
	    else {
            throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion division");
	    }
		
		return res;
	}
	private Type divisionEntera(Type izq, Type der) throws CompilerException{
		Type res = null; 
		
		boolean intInt = (izq.getType() == 0) && (der.getType() == 0);
		boolean intLong = (izq.getType() == 0) && (der.getType() == 2);
		boolean intFloat = (izq.getType() == 0) && (der.getType() == 1);
		boolean intBoolean = (izq.getType() == 0) && (der.getType() == 5);

		boolean longInt = (izq.getType() == 2) && (der.getType() == 0);
		boolean longLong = (izq.getType() == 2) && (der.getType() == 3);
		boolean longFloat = (izq.getType() == 2) && (der.getType() == 1);
		boolean longBoolean = (izq.getType() == 2) && (der.getType() == 5);
		
		boolean floatInt = (izq.getType() == 1) && (der.getType() == 0);
		boolean floatLong = (izq.getType() == 1) && (der.getType() == 2);
		boolean floatFloat = (izq.getType() == 1) && (der.getType() == 1);
		boolean floatBoolean = (izq.getType() == 1) && (der.getType() == 5);
		
		boolean booleanInt = (izq.getType() == 5) && (der.getType() == 0);
		boolean booleanLong = (izq.getType() == 5) && (der.getType() == 2);
		boolean booleanFloat = (izq.getType() == 5) && (der.getType() == 1);
		boolean booleanBoolean = (izq.getType() == 5) && (der.getType() == 5);
		
		if(intInt){
			int div = ((IntegerType)der).getValue();
			if(div == 0){
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			}
			int resultado = ((IntegerType)izq).getValue() / div ;
			res = new IntegerType(resultado);
		}
		else if(intLong){
			long div = ((LongType)der).getValue();
			if(div == 0l)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			
			long resultado = ((IntegerType)izq).getValue() / div ;
			res = new LongType(resultado);
		}
		else if(intFloat){
			float div = ((FloatType)der).getValue();
			if(div == 0f)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			
			float resultado =(float) Math.floor(((IntegerType)izq).getValue() / div);
			res = new FloatType(resultado);
		}
		else if(intBoolean){
			int div = ((BooleanType)der).getEquivalentInt();
			if(div == 0)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			
			int resultado = ((IntegerType)izq).getValue() / div;
			res = new IntegerType(resultado);
		}
		else if(longInt){
			int div = ((IntegerType)der).getValue();
			if(div == 0){
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			}
			long resultado = ((LongType)izq).getValue() / div;
			res = new LongType(resultado);
		}
		else if(longLong){
			long div = ((LongType)der).getValue();
			if(div == 0l)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			
			long resultado = ((LongType)izq).getValue() / div;
			res = new LongType(resultado);
		}
		else if(longFloat){
			float div = ((FloatType)der).getValue();
			if(div == 0f)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			float resultado = (float)Math.floor(((LongType)izq).getValue() / div);
			res = new FloatType(resultado);
		}
		else if(longBoolean){
			int div = ((BooleanType)der).getEquivalentInt();
			if(div == 0)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			
			long resultado = ((LongType)izq).getValue() / div;
			res = new LongType(resultado);
		}
		else if(floatInt){
			int div = ((IntegerType)der).getValue();
			if(div == 0){
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			}
			
			float resultado =(float) Math.floor(((FloatType)izq).getValue() / div);
			res = new FloatType(resultado);
		}
		else if(floatLong){
			long div = ((LongType)der).getValue();
			if(div == 0l)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			
			float resultado = (float) Math.floor(((FloatType)izq).getValue() / div);
			res = new FloatType(resultado);
		}
		else if(floatFloat){
			float div = ((FloatType)der).getValue();
			if(div == 0f)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			
			float resultado =  (float)Math.floor(((FloatType)izq).getValue() / div);
			res = new FloatType(resultado);
		}
		else if(floatBoolean){
			int div = ((BooleanType)der).getEquivalentInt();
			if(div == 0)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			
			float resultado = (float)Math.floor(((FloatType)izq).getValue() / div);
			res = new FloatType(resultado);
		}
		else if(booleanInt){
			int div = ((IntegerType)der).getValue();
			if(div == 0){
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			}
			
			int resultado = ((BooleanType)izq).getEquivalentInt() / div;
			res = new IntegerType(resultado);
		}
		else if(booleanLong){
			long div = ((LongType)der).getValue();
			if(div == 0l)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			
			long resultado = ((BooleanType)izq).getEquivalentInt() / div;
			res = new LongType(resultado);
		}
		else if(booleanFloat){
			float div = ((FloatType)der).getValue();
			if(div == 0f)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			
			float resultado = (float)Math.floor(((BooleanType)izq).getEquivalentInt() / div);
			res = new FloatType(resultado);
		}
		else if(booleanBoolean){
			int div = ((BooleanType)der).getEquivalentInt();
			if(div == 0)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			
			int resultado = ((BooleanType)izq).getEquivalentInt() / div;
			res = new IntegerType(resultado);
		}
	    else {
            throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion division");
	    }
		
		
		return res;
	}
	private Type modulo(Type izq, Type der) throws CompilerException{
		Type res = null;
		boolean intInt = (izq.getType() == 0) && (der.getType() == 0);
		boolean intLong = (izq.getType() == 0) && (der.getType() == 2);
		boolean intFloat = (izq.getType() == 0) && (der.getType() == 1);
		boolean intBoolean = (izq.getType() == 0) && (der.getType() == 5);

		boolean longInt = (izq.getType() == 2) && (der.getType() == 0);
		boolean longLong = (izq.getType() == 2) && (der.getType() == 3);
		boolean longFloat = (izq.getType() == 2) && (der.getType() == 1);
		boolean longBoolean = (izq.getType() == 2) && (der.getType() == 5);
		
		boolean floatInt = (izq.getType() == 1) && (der.getType() == 0);
		boolean floatLong = (izq.getType() == 1) && (der.getType() == 2);
		boolean floatFloat = (izq.getType() == 1) && (der.getType() == 1);
		boolean floatBoolean = (izq.getType() == 1) && (der.getType() == 5);
		
		boolean booleanInt = (izq.getType() == 5) && (der.getType() == 0);
		boolean booleanLong = (izq.getType() == 5) && (der.getType() == 2);
		boolean booleanFloat = (izq.getType() == 5) && (der.getType() == 1);
		boolean booleanBoolean = (izq.getType() == 5) && (der.getType() == 5);
		
		if(intInt){
			int div = ((IntegerType)der).getValue();
			if(div == 0){
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			}
			int resultado = ((IntegerType)izq).getValue() % div ;
			res = new IntegerType(resultado);
		}
		else if(intLong){
			long div = ((LongType)der).getValue();
			if(div == 0l)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			
			long resultado = ((IntegerType)izq).getValue() % div ;
			res = new LongType(resultado);
		}
		else if(intFloat){
			float div = ((FloatType)der).getValue();
			if(div == 0f)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			
			float resultado = ((IntegerType)izq).getValue() % div;
			res = new FloatType(resultado);
		}
		else if(intBoolean){
			int div = ((BooleanType)der).getEquivalentInt();
			if(div == 0)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			
			int resultado = ((IntegerType)izq).getValue() % div;
			res = new IntegerType(resultado);
		}
		else if(longInt){
			int div = ((IntegerType)der).getValue();
			if(div == 0){
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			}
			long resultado = ((LongType)izq).getValue() % div;
			res = new LongType(resultado);
		}
		else if(longLong){
			long div = ((LongType)der).getValue();
			if(div == 0l)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			
			long resultado = ((LongType)izq).getValue() % div;
			res = new LongType(resultado);
		}
		else if(longFloat){
			float div = ((FloatType)der).getValue();
			if(div == 0f)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			float resultado = ((LongType)izq).getValue() % div;
			res = new FloatType(resultado);
		}
		else if(longBoolean){
			int div = ((BooleanType)der).getEquivalentInt();
			if(div == 0)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			
			long resultado = ((LongType)izq).getValue() % div;
			res = new LongType(resultado);
		}
		else if(floatInt){
			int div = ((IntegerType)der).getValue();
			if(div == 0){
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			}
			
			float resultado = ((FloatType)izq).getValue() % div;
			res = new FloatType(resultado);
		}
		else if(floatLong){
			long div = ((LongType)der).getValue();
			if(div == 0l)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			
			float resultado = ((FloatType)izq).getValue() % div;
			res = new FloatType(resultado);
		}
		else if(floatFloat){
			float div = ((FloatType)der).getValue();
			if(div == 0f)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			
			float resultado = ((FloatType)izq).getValue() % div;
			res = new FloatType(resultado);
		}
		else if(floatBoolean){
			int div = ((BooleanType)der).getEquivalentInt();
			if(div == 0)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			
			float resultado = ((FloatType)izq).getValue() % div;
			res = new FloatType(resultado);
		}
		else if(booleanInt){
			int div = ((IntegerType)der).getValue();
			if(div == 0){
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			}
			
			int resultado = ((BooleanType)izq).getEquivalentInt() % div;
			res = new IntegerType(resultado);
		}
		else if(booleanLong){
			long div = ((LongType)der).getValue();
			if(div == 0l)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			
			long resultado = ((BooleanType)izq).getEquivalentInt() % div;
			res = new LongType(resultado);
		}
		else if(booleanFloat){
			float div = ((FloatType)der).getValue();
			if(div == 0f)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			
			float resultado = ((BooleanType)izq).getEquivalentInt() % div;
			res = new FloatType(resultado);
		}
		else if(booleanBoolean){
			int div = ((BooleanType)der).getEquivalentInt();
			if(div == 0)
				throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
			
			int resultado = ((BooleanType)izq).getEquivalentInt() % div;
			res = new IntegerType(resultado);
		}
	    else {
            throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion division");
	    }
		
		return res;
	}
	private Type exponente(Type izq, Type der) throws CompilerException{
		Type res = null;
		
		boolean intInt = (izq.getType() == 0) && (der.getType() == 0);
		boolean intLong = (izq.getType() == 0) && (der.getType() == 2);
		boolean intFloat = (izq.getType() == 0) && (der.getType() == 1);
		boolean intBoolean = (izq.getType() == 0) && (der.getType() == 5);
		
		boolean longInt = (izq.getType() == 2) && (der.getType() == 0);
		boolean longLong = (izq.getType() == 2) && (der.getType() == 3);
		boolean longFloat = (izq.getType() == 2) && (der.getType() == 1);
		boolean longBoolean = (izq.getType() == 2) && (der.getType() == 5);
		
		boolean floatInt = (izq.getType() == 1) && (der.getType() == 0);
		boolean floatLong = (izq.getType() == 1) && (der.getType() == 2);
		boolean floatFloat = (izq.getType() == 1) && (der.getType() == 1);
		boolean floatBoolean = (izq.getType() == 1) && (der.getType() == 5);
		
		boolean booleanInt = (izq.getType() == 5) && (der.getType() == 0);
		boolean booleanLong = (izq.getType() == 5) && (der.getType() == 2);
		boolean booleanFloat = (izq.getType() == 5) && (der.getType() == 1);
		boolean booleanBoolean = (izq.getType() == 5) && (der.getType() == 5);
		
		if(intInt){
			int intIzq = ((IntegerType)izq).getValue();
			int intDer = ((IntegerType)der).getValue();
			int resultado = (int)Math.pow(intIzq, intDer);
			res = new IntegerType(resultado);
		}
		else if(intLong){
			int intIzq = ((IntegerType)izq).getValue();
			long longDer = ((LongType)der).getValue();
			long resultado = (long)Math.pow(intIzq, longDer);
			res = new LongType(resultado);
		}
		else if(intFloat){
			int intIzq = ((IntegerType)izq).getValue();
			float floatDer = ((FloatType)der).getValue();
			float resultado = (float)Math.pow(intIzq, floatDer);
			res = new FloatType(resultado);
		}
		else if(intBoolean){
			int intIzq = ((IntegerType)izq).getValue();
			int booleanDer = ((BooleanType)der).getEquivalentInt();
			int resultado = (int)Math.pow(intIzq, booleanDer);
			res = new IntegerType(resultado);
		}
		else if(longInt){
			int intDer = ((IntegerType)der).getValue();
			long longIzq = ((LongType)izq).getValue();
			long resultado = (long)Math.pow(longIzq, intDer);
			res = new LongType(resultado);
		}
		else if(longLong){
			long longDer = ((LongType)der).getValue();
			long longIzq = ((LongType)izq).getValue();
			long resultado = (long)Math.pow(longIzq, longDer);
			res = new LongType(resultado);
		}
		else if(longFloat){
			float floatDer = ((FloatType)der).getValue();
			long longIzq = ((LongType)izq).getValue();
			float resultado = (float)Math.pow(longIzq, floatDer);
			res = new FloatType(resultado);
		}
		else if(longBoolean){
			long longIzq = ((LongType)izq).getValue();
			int booleanDer = ((BooleanType)der).getEquivalentInt();
			long resultado = (long)Math.pow(longIzq, booleanDer);
			res = new LongType(resultado);
		}
		else if(floatInt){
			int intDer = ((IntegerType)der).getValue();
			float floatIzq = ((FloatType)izq).getValue();
			float resultado = (float)Math.pow(floatIzq, intDer);
			res = new FloatType(resultado);
		}
		else if(floatLong){
			long longDer = ((LongType)der).getValue();
			float floatIzq = ((FloatType)izq).getValue();
			float resultado = (float)Math.pow(floatIzq, longDer);
			res = new FloatType(resultado);
		}
		else if(floatFloat){
			float floatDer = ((FloatType)der).getValue();
			float floatIzq = ((FloatType)izq).getValue();
			float resultado = (float)Math.pow(floatIzq, floatDer);
			res = new FloatType(resultado);
		}
		else if(floatBoolean){
			int booleanDer = ((BooleanType)der).getEquivalentInt();
			float floatIzq = ((FloatType)izq).getValue();
			float resultado = (float)Math.pow(floatIzq, booleanDer);
			res = new FloatType(resultado);
		}
		else if(booleanInt){
			int booleanIzq = ((BooleanType)izq).getEquivalentInt();
			int intDer = ((IntegerType)der).getValue();
			int resultado = (int)Math.pow(booleanIzq, intDer);
			res = new IntegerType(resultado);
		}
		else if(booleanLong){
			int booleanIzq = ((BooleanType)izq).getEquivalentInt();
			long longDer = ((LongType)der).getValue();
			long resultado = (long)Math.pow(booleanIzq, longDer);
			res = new LongType(resultado);
		}
		else if(booleanFloat){
			int booleanIzq = ((BooleanType)izq).getEquivalentInt();
			float floatDer = ((FloatType)der).getValue();
			int resultado = (int)Math.pow(booleanIzq, floatDer);
			res = new FloatType(resultado);
		}
		else if(booleanBoolean){
			int booleanIzq = ((BooleanType)izq).getEquivalentInt();
			int booleanDer = ((BooleanType)der).getEquivalentInt();
			int resultado = (int)Math.pow(booleanIzq, booleanDer);
			res = new IntegerType(resultado);
		}
	    else {
            throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion division");
	    }
		
		return res;
		
		
	}
	private Type and(Type izq, Type der) throws CompilerException{
		Type res = null;
		
		boolean intInt = (izq.getType() == 0) && (der.getType() == 0);
		boolean intString = (izq.getType() == 0) && (der.getType() == 3);
		boolean intLong = (izq.getType() == 0) && (der.getType() == 2);
		boolean intFloat = (izq.getType() == 0) && (der.getType() == 1);
		boolean intBoolean = (izq.getType() == 0) && (der.getType() == 5);
		boolean intTuple = (izq.getType() == 0) && (der.getType() == 11);
		boolean intList = (izq.getType() == 0) && (der.getType() == 6);
		
		boolean stringInt = (izq.getType() == 3) && (der.getType() == 0);
		boolean stringString = (izq.getType() == 3) && (der.getType() == 3);
		boolean stringLong = (izq.getType() == 3) && (der.getType() == 2);
		boolean stringBoolean = (izq.getType() == 3) && (der.getType() == 5);
		boolean stringFloat = (izq.getType() == 3) && (der.getType() == 1);
		boolean stringTuple = (izq.getType() == 3) && (der.getType() == 11);
		boolean stringList = (izq.getType() == 3) && (der.getType() == 6);
		
		boolean longInt = (izq.getType() == 2) && (der.getType() == 0);
		boolean longString = (izq.getType() == 2) && (der.getType() == 3);
		boolean longLong = (izq.getType() == 2) && (der.getType() == 3);
		boolean longFloat = (izq.getType() == 2) && (der.getType() == 1);
		boolean longTuple = (izq.getType() == 2) && (der.getType() == 11);
		boolean longList = (izq.getType() == 2) && (der.getType() == 6);
		boolean longBoolean = (izq.getType() == 2) && (der.getType() == 5);
		
		boolean floatInt = (izq.getType() == 1) && (der.getType() == 0);
		boolean floatString = (izq.getType() == 1) && (der.getType() == 3);
		boolean floatLong = (izq.getType() == 1) && (der.getType() == 2);
		boolean floatFloat = (izq.getType() == 1) && (der.getType() == 1);
		boolean floatTuple = (izq.getType() == 1) && (der.getType() == 11);
		boolean floatList = (izq.getType() == 1) && (der.getType() == 6);
		boolean floatBoolean = (izq.getType() == 1) && (der.getType() == 5);
		
		boolean booleanInt = (izq.getType() == 5) && (der.getType() == 0);
		boolean booleanString = (izq.getType() == 5) && (der.getType() == 3);
		boolean booleanLong = (izq.getType() == 5) && (der.getType() == 2);
		boolean booleanFloat = (izq.getType() == 5) && (der.getType() == 1);
		boolean booleanTuple = (izq.getType() == 5) && (der.getType() == 11);
		boolean booleanList = (izq.getType() == 5) && (der.getType() == 6);
		boolean booleanBoolean = (izq.getType() == 5) && (der.getType() == 5);
		
		boolean tupleInt = (izq.getType() == 11) && (der.getType() == 0);
		boolean tupleString = (izq.getType() == 11) && (der.getType() == 3);
		boolean tupleLong = (izq.getType() == 11) && (der.getType() == 2);
		boolean tupleBoolean = (izq.getType() == 11) && (der.getType() == 5);
		boolean tupleFloat = (izq.getType() == 11) && (der.getType() == 1);
		boolean tupleTuple = (izq.getType() == 11) && (der.getType() == 11);
		boolean tupleList = (izq.getType() == 11) && (der.getType() == 6);
		
		boolean listInt = (izq.getType() == 6) && (der.getType() == 0);
		boolean listString = (izq.getType() == 6) && (der.getType() == 3);
		boolean listLong = (izq.getType() == 6) && (der.getType() == 2);
		boolean listBoolean = (izq.getType() == 6) && (der.getType() == 5);
		boolean listFloat = (izq.getType() == 6) && (der.getType() == 1);
		boolean listTuple = (izq.getType() == 6) && (der.getType() == 11);
		boolean listList = (izq.getType() == 6) && (der.getType() == 6);
		
		if(intInt){
			int intDer = ((IntegerType)der).getValue();
			int intIzq = ((IntegerType)izq).getValue();
			if(intIzq == 0)
				res = new IntegerType(0);
			else
				res = new IntegerType(intDer);
		}
		else if(intLong){
			long longDer = ((LongType)der).getValue();
			int intIzq = ((IntegerType)izq).getValue();
			if(intIzq == 0)
				res = new IntegerType(0);
			else
				res = new LongType(longDer);
		}
		else if(intFloat){
			float floatDer = ((FloatType)der).getValue();
			int intIzq = ((IntegerType)izq).getValue();
			if(intIzq == 0)
				res = new IntegerType(0);
			else
				res = new FloatType(floatDer);
		}
		else if(intBoolean){
			int intIzq = ((IntegerType)izq).getValue();
			boolean booleanDer = ((BooleanType)der).getValue();
			if(intIzq == 0)
				res = new IntegerType(0);
			else
				res = new BooleanType(booleanDer);
	
		}
		else if(intString){
			int intIzq = ((IntegerType)izq).getValue();
			String stringDer = ((StringType)der).getText();
			if(intIzq == 0)
				res = new IntegerType(0);
			else
				res = new StringType(stringDer);
			
		}
		else if(intTuple){
			int intIzq = ((IntegerType)izq).getValue();
			ArrayList<Type> tupleDer = ((TupleType)der).getTuple();
			if(intIzq == 0)
				res = new IntegerType(0);
			else
				res = new TupleType(tupleDer);
			
		}else if(intList){
			int intIzq = ((IntegerType)izq).getValue();
			ArrayList<Type> listDer = ((ListType)der).getList();
			if(intIzq == 0)
				res = new IntegerType(0);
			else
				res = new ListType(listDer);
			
		}
		else if(stringInt){
			int intDer = ((IntegerType)izq).getValue();
			String stringIzq = ((StringType)der).getText();
			if(stringIzq.isEmpty())
				res = new StringType(stringIzq);
			else
				res = new IntegerType(intDer);
			
		}
		else if(stringBoolean){
			boolean booleanDer = ((BooleanType)izq).getValue();
			String stringIzq = ((StringType)der).getText();
			if(stringIzq.isEmpty())
				res = new StringType(stringIzq);
			else
				res = new BooleanType(booleanDer);
		}
		else if(stringLong){
			long longDer = ((LongType)izq).getValue();
			String stringIzq = ((StringType)der).getText();
			if(stringIzq.isEmpty())
				res = new StringType(stringIzq);
			else
				res = new LongType(longDer);
			
		}
		else if(stringFloat){
			float floatDer = ((FloatType)izq).getValue();
			String stringIzq = ((StringType)der).getText();
			if(stringIzq.isEmpty())
				res = new StringType(stringIzq);
			else
				res = new FloatType(floatDer);
			
		}
		else if(stringString){
			String stringDer = ((StringType)izq).getText();
			String stringIzq = ((StringType)der).getText();
			if(stringIzq.isEmpty())
				res = new StringType(stringIzq);
			else
				res = new StringType(stringDer);
			
		}
		else if(stringTuple){
			ArrayList<Type> tupleDer = ((TupleType)der).getTuple();
			String stringIzq = ((StringType)izq).getText();
			if(stringIzq.isEmpty())
				res = new StringType(stringIzq);
			else
				res = new TupleType(tupleDer);
			
		}
		else if(stringList){
			ArrayList<Type> listDer = ((ListType)der).getList();
			String stringIzq = ((StringType)izq).getText();
			if(stringIzq.isEmpty())
				res = new StringType(stringIzq);
			else
				res = new ListType(listDer);
			
		}
		else if(longInt){
			long longIzq = ((LongType)izq).getValue();
			int intDer = ((IntegerType)der).getValue();
			if (longIzq == 0)
				res = new LongType(0);
			else
				res = new IntegerType(intDer);

		}
		else if(longLong){
			long longIzq = ((LongType)izq).getValue();
			long longDer = ((LongType)der).getValue();
			if(longIzq == 0)
				res = new LongType(0);
			else
				res = new LongType(longDer);

		}
		else if(longFloat){
			long longIzq = ((LongType)izq).getValue();
			long longDer = ((LongType)der).getValue();
			if(longIzq == 0)
				res = new LongType(0);
			else
				res = new LongType(longDer);

		}
		else if(longBoolean){
			long longIzq = ((LongType)izq).getValue();
			boolean booleanDer = ((BooleanType)der).getValue();
			if(longIzq == 0)
				res = new LongType(0);
			else
				res = new BooleanType(booleanDer);

		}
		else if(longString){
			long longIzq = ((LongType)izq).getValue();
			String stringDer = ((StringType)der).getText();
			if(longIzq == 0)
				res = new LongType(0);
			else
				res = new StringType(stringDer);
		}
		else if(longTuple){
			long longIzq = ((LongType)izq).getValue();
			ArrayList<Type> tupleDer = ((TupleType)der).getTuple();
			if(longIzq == 0)
				res = new LongType(0);
			else
				res = new TupleType(tupleDer);
		}
		else if(longTuple){
			long longIzq = ((LongType)izq).getValue();
			ArrayList<Type> listDer = ((ListType)der).getList();
			if(longIzq == 0)
				res = new LongType(0);
			else
				res = new ListType(listDer);
		}
		else if(floatInt){
			float floatIzq = ((FloatType)izq).getValue();
			int intDer = ((IntegerType)der).getValue();
			if (floatIzq == 0)
				res = new FloatType(0);
			else
				res = new IntegerType(intDer);

		}
		else if(floatLong){
			float floatIzq = ((FloatType)izq).getValue();
			long longDer = ((LongType)der).getValue();
			if (floatIzq == 0)
				res = new FloatType(0);
			else
				res = new LongType(longDer);

		}
		else if(floatFloat){
			float floatIzq = ((FloatType)izq).getValue();
			float floatDer = ((FloatType)der).getValue();
			if (floatIzq == 0)
				res = new FloatType(0);
			else
				res = new FloatType(floatDer);

		}
		else if(floatBoolean){
			float floatIzq = ((FloatType)izq).getValue();
			boolean booleanDer = ((BooleanType)der).getValue();
			if (floatIzq == 0)
				res = new FloatType(0);
			else
				res = new BooleanType(booleanDer);

		}
		else if(floatString){
			float floatIzq = ((FloatType)izq).getValue();
			String stringDer = ((StringType)der).getText();
			if (floatIzq == 0)
				res = new FloatType(0);
			else
				res = new StringType(stringDer);
		}
		else if(floatTuple){
			float floatIzq = ((FloatType)izq).getValue();
			ArrayList<Type> tupleDer = ((TupleType)der).getTuple();
			if (floatIzq == 0)
				res = new FloatType(0);
			else
				res = new TupleType(tupleDer);
		}
		else if(floatList){
			float floatIzq = ((FloatType)izq).getValue();
			ArrayList<Type> listDer = ((ListType)der).getList();
			if (floatIzq == 0)
				res = new FloatType(0);
			else
				res = new ListType(listDer);
		}
		else if(booleanInt){
			int intDer = ((IntegerType)der).getValue();
			boolean booleanIzq = ((BooleanType)izq).getValue();
			if (!booleanIzq)
				res = new BooleanType(false);
			else
				res = new IntegerType(intDer);

		}
		else if(booleanLong){
			long longDer = ((LongType)der).getValue();
			boolean booleanIzq = ((BooleanType)izq).getValue();
			if (!booleanIzq)
				res = new BooleanType(false);
			else
				res = new LongType(longDer);

		}
		else if(booleanFloat){
			float floatDer = ((FloatType)der).getValue();
			boolean booleanIzq = ((BooleanType)izq).getValue();
			if (!booleanIzq)
				res = new BooleanType(false);
			else
				res = new FloatType(floatDer);

		}
		else if(booleanBoolean){
			boolean booleanDer = ((BooleanType)der).getValue();
			boolean booleanIzq = ((BooleanType)izq).getValue();
			if (!booleanIzq)
				res = new BooleanType(false);
			else
				res = new BooleanType(booleanDer);

		}
		else if (booleanString){
			String stringDer = ((StringType)der).getText();
			boolean booleanIzq = ((BooleanType)izq).getValue();
			if (!booleanIzq)
				res = new BooleanType(false);
			else
				res = new StringType(stringDer);
		}
		else if (booleanTuple){
			ArrayList<Type> tupleDer = ((TupleType)der).getTuple();
			boolean booleanIzq = ((BooleanType)izq).getValue();
			if (!booleanIzq)
				res = new BooleanType(false);
			else
				res = new TupleType(tupleDer);
		}
		else if (booleanList){
			ArrayList<Type> listDer = ((ListType)der).getList();
			boolean booleanIzq = ((BooleanType)izq).getValue();
			if (!booleanIzq)
				res = new BooleanType(false);
			else
				res = new ListType(listDer);
		}
		else if(tupleInt){
			int intDer = ((IntegerType)der).getValue();
			ArrayList<Type> tupleIzq = ((TupleType)izq).getTuple();
			if (tupleIzq.isEmpty())
				res = new TupleType(tupleIzq);
			else
				res = new IntegerType(intDer);

		}
		else if(tupleLong){
			long longDer = ((LongType)der).getValue();
			ArrayList<Type> tupleIzq = ((TupleType)izq).getTuple();
			if (tupleIzq.isEmpty())
				res = new TupleType(tupleIzq);
			else
				res = new LongType(longDer);

		}
		else if(tupleFloat){
			float floatDer = ((FloatType)der).getValue();
			ArrayList<Type> tupleIzq = ((TupleType)izq).getTuple();
			if (tupleIzq.isEmpty())
				res = new TupleType(tupleIzq);
			else
				res = new FloatType(floatDer);

		}
		else if(tupleBoolean){
			boolean booleanDer = ((BooleanType)der).getValue();
			ArrayList<Type> tupleIzq = ((TupleType)izq).getTuple();
			if (tupleIzq.isEmpty())
				res = new TupleType(tupleIzq);
			else
				res = new BooleanType(booleanDer);

		}
		else if (tupleString){
			String stringDer = ((StringType)der).getText();
			ArrayList<Type> tupleIzq = ((TupleType)izq).getTuple();
			if (tupleIzq.isEmpty())
				res = new TupleType(tupleIzq);
			else
				res = new StringType(stringDer);
		}
		else if (tupleTuple){
			ArrayList<Type> tupleDer = ((TupleType)der).getTuple();
			ArrayList<Type> tupleIzq = ((TupleType)izq).getTuple();
			if (tupleIzq.isEmpty())
				res = new TupleType(tupleIzq);
			else
				res = new TupleType(tupleDer);
		}
		else if (tupleList){
			ArrayList<Type> listDer = ((ListType)der).getList();
			ArrayList<Type> tupleIzq = ((TupleType)izq).getTuple();
			if (tupleIzq.isEmpty())
				res = new TupleType(tupleIzq);
			else
				res = new ListType(listDer);
		}
		else if(listInt){
			int intDer = ((IntegerType)der).getValue();
			ArrayList<Type> listIzq = ((ListType)izq).getList();
			if (listIzq.isEmpty())
				res = new ListType(listIzq);
			else
				res = new IntegerType(intDer);

		}
		else if(listLong){
			long longDer = ((LongType)der).getValue();
			ArrayList<Type> listIzq = ((ListType)izq).getList();
			if (listIzq.isEmpty())
				res = new ListType(listIzq);
			else
				res = new LongType(longDer);

		}
		else if(listFloat){
			float floatDer = ((FloatType)der).getValue();
			ArrayList<Type> listIzq = ((ListType)izq).getList();
			if (listIzq.isEmpty())
				res = new ListType(listIzq);
			else
				res = new FloatType(floatDer);

		}
		else if(listBoolean){
			boolean booleanDer = ((BooleanType)der).getValue();
			ArrayList<Type> listIzq = ((ListType)izq).getList();
			if (listIzq.isEmpty())
				res = new ListType(listIzq);
			else
				res = new BooleanType(booleanDer);

		}
		else if (listString){
			String stringDer = ((StringType)der).getText();
			ArrayList<Type> listIzq = ((ListType)izq).getList();
			if (listIzq.isEmpty())
				res = new ListType(listIzq);
			else
				res = new StringType(stringDer);
		}
		else if (listList){
			ArrayList<Type> listDer = ((ListType)der).getList();
			ArrayList<Type> listIzq = ((ListType)izq).getList();
			if (listIzq.isEmpty())
				res = new ListType(listIzq);
			else
				res = new ListType(listDer);
		}
		else if (listTuple){
			ArrayList<Type> tupleDer = ((TupleType)der).getTuple();
			ArrayList<Type> listIzq = ((ListType)izq).getList();
			if (listIzq.isEmpty())
				res = new ListType(listIzq);
			else
				res = new TupleType(tupleDer);
		}
	    else {
            throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion and");
	    }
		
		return res;
		
	}
	private Type or(Type izq, Type der) throws CompilerException{
		Type res = null;
		
		boolean intInt = (izq.getType() == 0) && (der.getType() == 0);
		boolean intString = (izq.getType() == 0) && (der.getType() == 3);
		boolean intLong = (izq.getType() == 0) && (der.getType() == 2);
		boolean intFloat = (izq.getType() == 0) && (der.getType() == 1);
		boolean intBoolean = (izq.getType() == 0) && (der.getType() == 5);
		boolean intTuple = (izq.getType() == 0) && (der.getType() == 11);
		boolean intList = (izq.getType() == 0) && (der.getType() == 6);
		
		boolean stringInt = (izq.getType() == 3) && (der.getType() == 0);
		boolean stringString = (izq.getType() == 3) && (der.getType() == 3);
		boolean stringLong = (izq.getType() == 3) && (der.getType() == 2);
		boolean stringBoolean = (izq.getType() == 3) && (der.getType() == 5);
		boolean stringFloat = (izq.getType() == 3) && (der.getType() == 1);
		boolean stringTuple = (izq.getType() == 3) && (der.getType() == 11);
		boolean stringList = (izq.getType() == 3) && (der.getType() == 6);
		
		boolean longInt = (izq.getType() == 2) && (der.getType() == 0);
		boolean longString = (izq.getType() == 2) && (der.getType() == 3);
		boolean longLong = (izq.getType() == 2) && (der.getType() == 3);
		boolean longFloat = (izq.getType() == 2) && (der.getType() == 1);
		boolean longBoolean = (izq.getType() == 2) && (der.getType() == 5);
		boolean longTuple = (izq.getType() == 2) && (der.getType() == 11);
		boolean longList = (izq.getType() == 2) && (der.getType() == 6);
		
		boolean floatInt = (izq.getType() == 1) && (der.getType() == 0);
		boolean floatString = (izq.getType() == 1) && (der.getType() == 3);
		boolean floatLong = (izq.getType() == 1) && (der.getType() == 2);
		boolean floatFloat = (izq.getType() == 1) && (der.getType() == 1);
		boolean floatBoolean = (izq.getType() == 1) && (der.getType() == 5);
		boolean floatTuple = (izq.getType() == 1) && (der.getType() == 11);
		boolean floatList = (izq.getType() == 1) && (der.getType() == 6);
		
		boolean booleanInt = (izq.getType() == 5) && (der.getType() == 0);
		boolean booleanString = (izq.getType() == 5) && (der.getType() == 3);
		boolean booleanLong = (izq.getType() == 5) && (der.getType() == 2);
		boolean booleanFloat = (izq.getType() == 5) && (der.getType() == 1);
		boolean booleanBoolean = (izq.getType() == 5) && (der.getType() == 5);
		boolean booleanTuple = (izq.getType() == 5) && (der.getType() == 11);
		boolean booleanList = (izq.getType() == 5) && (der.getType() == 6);
		
		boolean tupleInt = (izq.getType() == 11) && (der.getType() == 0);
		boolean tupleString = (izq.getType() == 11) && (der.getType() == 3);
		boolean tupleLong = (izq.getType() == 11) && (der.getType() == 2);
		boolean tupleBoolean = (izq.getType() == 11) && (der.getType() == 5);
		boolean tupleFloat = (izq.getType() == 11) && (der.getType() == 1);
		boolean tupleTuple = (izq.getType() == 11) && (der.getType() == 11);
		boolean tupleList = (izq.getType() == 11) && (der.getType() == 6);

		boolean listInt = (izq.getType() == 6) && (der.getType() == 0);
		boolean listString = (izq.getType() == 6) && (der.getType() == 3);
		boolean listLong = (izq.getType() == 6) && (der.getType() == 2);
		boolean listBoolean = (izq.getType() == 6) && (der.getType() == 5);
		boolean listFloat = (izq.getType() == 6) && (der.getType() == 1);
		boolean listTuple = (izq.getType() == 6) && (der.getType() == 11);
		boolean listList = (izq.getType() == 6) && (der.getType() == 6);
		
		if(intInt){
			int intDer = ((IntegerType)der).getValue();
			int intIzq = ((IntegerType)izq).getValue();
			if(intIzq != 0)
				res = new IntegerType(intIzq);
			else
				res = new IntegerType(intDer);
		}
		else if(intLong){
			long longDer = ((LongType)der).getValue();
			int intIzq = ((IntegerType)izq).getValue();
			if(intIzq != 0)
				res = new IntegerType(intIzq);
			else
				res = new LongType(longDer);
		}
		else if(intFloat){
			float floatDer = ((FloatType)der).getValue();
			int intIzq = ((IntegerType)izq).getValue();
			if(intIzq != 0)
				res = new IntegerType(intIzq);
			else
				res = new FloatType(floatDer);
		}
		else if(intBoolean){
			int intIzq = ((IntegerType)izq).getValue();
			boolean booleanDer = ((BooleanType)der).getValue();
			if(intIzq != 0)
				res = new IntegerType(intIzq);
			else
				res = new BooleanType(booleanDer);
	
		}
		else if(intString){
			int intIzq = ((IntegerType)izq).getValue();
			String stringDer = ((StringType)der).getText();
			if(intIzq != 0)
				res = new IntegerType(intIzq);
			else
				res = new StringType(stringDer);
			
		}
		else if(intTuple){
			int intIzq = ((IntegerType)izq).getValue();
			ArrayList<Type> tupleDer = ((TupleType)der).getTuple();
			if(intIzq != 0)
				res = new IntegerType(0);
			else
				res = new TupleType(tupleDer);
			
		}
		else if(intList){
			int intIzq = ((IntegerType)izq).getValue();
			ArrayList<Type> listDer = ((ListType)der).getList();
			if(intIzq != 0)
				res = new IntegerType(0);
			else
				res = new ListType(listDer);
			
		}
		else if(stringInt){
			int intDer = ((IntegerType)izq).getValue();
			String stringIzq = ((StringType)der).getText();
			if(!stringIzq.isEmpty())
				res = new StringType(stringIzq);
			else
				res = new IntegerType(intDer);
			
		}
		else if(stringBoolean){
			boolean booleanDer = ((BooleanType)izq).getValue();
			String stringIzq = ((StringType)der).getText();
			if(!stringIzq.isEmpty())
				res = new StringType(stringIzq);
			else
				res = new BooleanType(booleanDer);
		}
		else if(stringLong){
			long longDer = ((LongType)izq).getValue();
			String stringIzq = ((StringType)der).getText();
			if(!stringIzq.isEmpty())
				res = new StringType(stringIzq);
			else
				res = new LongType(longDer);
			
		}
		else if(stringFloat){
			float floatDer = ((FloatType)izq).getValue();
			String stringIzq = ((StringType)der).getText();
			if(!stringIzq.isEmpty())
				res = new StringType(stringIzq);
			else
				res = new FloatType(floatDer);
			
		}
		else if(stringString){
			String stringDer = ((StringType)izq).getText();
			String stringIzq = ((StringType)der).getText();
			if(!stringIzq.isEmpty())
				res = new StringType(stringIzq);
			else
				res = new StringType(stringDer);
			
		}
		else if(stringTuple){
			ArrayList<Type> tupleDer = ((TupleType)der).getTuple();
			String stringIzq = ((StringType)izq).getText();
			if(!stringIzq.isEmpty())
				res = new StringType(stringIzq);
			else
				res = new TupleType(tupleDer);
			
		}
		else if(stringList){
			ArrayList<Type> listDer = ((ListType)der).getList();
			String stringIzq = ((StringType)izq).getText();
			if(!stringIzq.isEmpty())
				res = new StringType(stringIzq);
			else
				res = new ListType(listDer);
			
		}
		else if(longInt){
			long longIzq = ((LongType)izq).getValue();
			int intDer = ((IntegerType)der).getValue();
			if (longIzq != 0)
				res = new LongType(longIzq);
			else
				res = new IntegerType(intDer);

		}
		else if(longLong){
			long longIzq = ((LongType)izq).getValue();
			long longDer = ((LongType)der).getValue();
			if(longDer != 0)
				res = new LongType(longIzq);
			else
				res = new LongType(longDer);

		}
		else if(longFloat){
			long longIzq = ((LongType)izq).getValue();
			long longDer = ((LongType)der).getValue();
			if(longIzq != 0)
				res = new LongType(longIzq);
			else
				res = new LongType(longDer);

		}
		else if(longBoolean){
			long longIzq = ((LongType)izq).getValue();
			boolean booleanDer = ((BooleanType)der).getValue();
			if(longIzq != 0)
				res = new LongType(longIzq);
			else
				res = new BooleanType(booleanDer);

		}
		else if(longString){
			long longIzq = ((LongType)izq).getValue();
			String stringDer = ((StringType)der).getText();
			if(longIzq != 0)
				res = new LongType(longIzq);
			else
				res = new StringType(stringDer);
		}
		else if(longTuple){
			long longIzq = ((LongType)izq).getValue();
			ArrayList<Type> tupleDer = ((TupleType)der).getTuple();
			if(longIzq != 0)
				res = new LongType(0);
			else
				res = new TupleType(tupleDer);
		}
		else if(longList){
			long longIzq = ((LongType)izq).getValue();
			ArrayList<Type> listDer = ((ListType)der).getList();
			if(longIzq != 0)
				res = new LongType(0);
			else
				res = new ListType(listDer);
		}
		else if(floatInt){
			float floatIzq = ((FloatType)izq).getValue();
			int intDer = ((IntegerType)der).getValue();
			if (floatIzq != 0)
				res = new FloatType(floatIzq);
			else
				res = new IntegerType(intDer);

		}
		else if(floatLong){
			float floatIzq = ((FloatType)izq).getValue();
			long longDer = ((LongType)der).getValue();
			if (floatIzq != 0)
				res = new FloatType(floatIzq);
			else
				res = new LongType(longDer);

		}
		else if(floatFloat){
			float floatIzq = ((FloatType)izq).getValue();
			float floatDer = ((FloatType)der).getValue();
			if (floatIzq != 0)
				res = new FloatType(floatIzq);
			else
				res = new FloatType(floatDer);

		}
		else if(floatBoolean){
			float floatIzq = ((FloatType)izq).getValue();
			boolean booleanDer = ((BooleanType)der).getValue();
			if (floatIzq != 0)
				res = new FloatType(floatIzq);
			else
				res = new BooleanType(booleanDer);

		}
		else if(floatString){
			float floatIzq = ((FloatType)izq).getValue();
			String stringDer = ((StringType)der).getText();
			if (floatIzq != 0)
				res = new FloatType(0);
			else
				res = new StringType(stringDer);
		}
		else if(floatTuple){
			float floatIzq = ((FloatType)izq).getValue();
			ArrayList<Type> tupleDer = ((TupleType)der).getTuple();
			if (floatIzq != 0)
				res = new FloatType(0);
			else
				res = new TupleType(tupleDer);
		}
		else if(floatList){
			float floatIzq = ((FloatType)izq).getValue();
			ArrayList<Type> listDer = ((ListType)der).getList();
			if (floatIzq != 0)
				res = new FloatType(0);
			else
				res = new ListType(listDer);
		}
		else if(booleanInt){
			int intDer = ((IntegerType)der).getValue();
			boolean booleanIzq = ((BooleanType)izq).getValue();
			if (booleanIzq)
				res = new BooleanType(true);
			else
				res = new IntegerType(intDer);

		}
		else if(booleanLong){
			long longDer = ((LongType)der).getValue();
			boolean booleanIzq = ((BooleanType)izq).getValue();
			if (booleanIzq)
				res = new BooleanType(true);
			else
				res = new LongType(longDer);

		}
		else if(booleanFloat){
			float floatDer = ((FloatType)der).getValue();
			boolean booleanIzq = ((BooleanType)izq).getValue();
			if (booleanIzq)
				res = new BooleanType(true);
			else
				res = new FloatType(floatDer);

		}
		else if(booleanBoolean){
			boolean booleanDer = ((BooleanType)der).getValue();
			boolean booleanIzq = ((BooleanType)izq).getValue();
			if (booleanIzq)
				res = new BooleanType(true);
			else
				res = new BooleanType(booleanDer);

		}
		else if (booleanString){
			String stringDer = ((StringType)der).getText();
			boolean booleanIzq = ((BooleanType)izq).getValue();
			if (booleanIzq)
				res = new BooleanType(true);
			else
				res = new StringType(stringDer);
		}
		else if (booleanTuple){
			ArrayList<Type> tupleDer = ((TupleType)der).getTuple();
			boolean booleanIzq = ((BooleanType)izq).getValue();
			if (booleanIzq)
				res = new BooleanType(true);
			else
				res = new TupleType(tupleDer);
		}
		else if (booleanList){
			ArrayList<Type> listDer = ((ListType)der).getList();
			boolean booleanIzq = ((BooleanType)izq).getValue();
			if (booleanIzq)
				res = new BooleanType(true);
			else
				res = new ListType(listDer);
		}
		else if(tupleInt){
			int intDer = ((IntegerType)der).getValue();
			ArrayList<Type> tupleIzq = ((TupleType)izq).getTuple();
			if (!tupleIzq.isEmpty())
				res = new TupleType(tupleIzq);
			else
				res = new IntegerType(intDer);

		}
		else if(tupleLong){
			long longDer = ((LongType)der).getValue();
			ArrayList<Type> tupleIzq = ((TupleType)izq).getTuple();
			if (!tupleIzq.isEmpty())
				res = new TupleType(tupleIzq);
			else
				res = new LongType(longDer);

		}
		else if(tupleFloat){
			float floatDer = ((FloatType)der).getValue();
			ArrayList<Type> tupleIzq = ((TupleType)izq).getTuple();
			if (!tupleIzq.isEmpty())
				res = new TupleType(tupleIzq);
			else
				res = new FloatType(floatDer);

		}
		else if(tupleBoolean){
			boolean booleanDer = ((BooleanType)der).getValue();
			ArrayList<Type> tupleIzq = ((TupleType)izq).getTuple();
			if (!tupleIzq.isEmpty())
				res = new TupleType(tupleIzq);
			else
				res = new BooleanType(booleanDer);

		}
		else if (tupleString){
			String stringDer = ((StringType)der).getText();
			ArrayList<Type> tupleIzq = ((TupleType)izq).getTuple();
			if (!tupleIzq.isEmpty())
				res = new TupleType(tupleIzq);
			else
				res = new StringType(stringDer);
		}
		else if (tupleTuple){
			ArrayList<Type> tupleDer = ((TupleType)der).getTuple();
			ArrayList<Type> tupleIzq = ((TupleType)izq).getTuple();
			if (!tupleIzq.isEmpty())
				res = new TupleType(tupleIzq);
			else
				res = new TupleType(tupleDer);
		}
		else if (tupleList){
			ArrayList<Type> listDer = ((ListType)der).getList();
			ArrayList<Type> tupleIzq = ((TupleType)izq).getTuple();
			if (!tupleIzq.isEmpty())
				res = new TupleType(tupleIzq);
			else
				res = new ListType(listDer);
		}
		else if(listInt){
			int intDer = ((IntegerType)der).getValue();
			ArrayList<Type> listIzq = ((ListType)izq).getList();
			if (!listIzq.isEmpty())
				res = new ListType(listIzq);
			else
				res = new IntegerType(intDer);

		}
		else if(listLong){
			long longDer = ((LongType)der).getValue();
			ArrayList<Type> listIzq = ((ListType)izq).getList();
			if (!listIzq.isEmpty())
				res = new ListType(listIzq);
			else
				res = new LongType(longDer);

		}
		else if(listFloat){
			float floatDer = ((FloatType)der).getValue();
			ArrayList<Type> listIzq = ((ListType)izq).getList();
			if (!listIzq.isEmpty())
				res = new ListType(listIzq);
			else
				res = new FloatType(floatDer);

		}
		else if(listBoolean){
			boolean booleanDer = ((BooleanType)der).getValue();
			ArrayList<Type> listIzq = ((ListType)izq).getList();
			if (!listIzq.isEmpty())
				res = new ListType(listIzq);
			else
				res = new BooleanType(booleanDer);

		}
		else if (listString){
			String stringDer = ((StringType)der).getText();
			ArrayList<Type> listIzq = ((ListType)izq).getList();
			if (!listIzq.isEmpty())
				res = new ListType(listIzq);
			else
				res = new StringType(stringDer);
		}
		else if (listList){
			ArrayList<Type> listDer = ((ListType)der).getList();
			ArrayList<Type> listIzq = ((ListType)izq).getList();
			if (!listIzq.isEmpty())
				res = new ListType(listIzq);
			else
				res = new ListType(listDer);
		}
		else if (listTuple){
			ArrayList<Type> tupleDer = ((TupleType)der).getTuple();
			ArrayList<Type> listIzq = ((ListType)izq).getList();
			if (!listIzq.isEmpty())
				res = new ListType(listIzq);
			else
				res = new TupleType(tupleDer);
		}
	    else {
            throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion or");
	    }
		
		return res;
	}
	private Type equals(Type izq, Type der) throws CompilerException{
		Type res = null;
		
		boolean intInt = (izq.getType() == 0) && (der.getType() == 0);
		boolean intString = (izq.getType() == 0) && (der.getType() == 3);
		boolean intLong = (izq.getType() == 0) && (der.getType() == 2);
		boolean intFloat = (izq.getType() == 0) && (der.getType() == 1);
		boolean intTuple = (izq.getType() == 0) && (der.getType() == 11);
		boolean intList = (izq.getType() == 0) && (der.getType() == 6);
		boolean intBoolean = (izq.getType() == 0) && (der.getType() == 5);
		
		boolean stringInt = (izq.getType() == 3) && (der.getType() == 0);
		boolean stringString = (izq.getType() == 3) && (der.getType() == 3);
		boolean stringLong = (izq.getType() == 3) && (der.getType() == 2);
		boolean stringBoolean = (izq.getType() == 3) && (der.getType() == 5);
		boolean stringFloat = (izq.getType() == 3) && (der.getType() == 1);
		boolean stringTuple = (izq.getType() == 3) && (der.getType() == 11);
		boolean stringList = (izq.getType() == 3) && (der.getType() == 6);
		
		boolean longInt = (izq.getType() == 2) && (der.getType() == 0);
		boolean longString = (izq.getType() == 2) && (der.getType() == 3);
		boolean longLong = (izq.getType() == 2) && (der.getType() == 3);
		boolean longFloat = (izq.getType() == 2) && (der.getType() == 1);
		boolean longTuple = (izq.getType() == 2) && (der.getType() == 11);
		boolean longList = (izq.getType() == 2) && (der.getType() == 6);
		boolean longBoolean = (izq.getType() == 2) && (der.getType() == 5);
		
		boolean floatInt = (izq.getType() == 1) && (der.getType() == 0);
		boolean floatString = (izq.getType() == 1) && (der.getType() == 3);
		boolean floatLong = (izq.getType() == 1) && (der.getType() == 2);
		boolean floatFloat = (izq.getType() == 1) && (der.getType() == 1);
		boolean floatTuple = (izq.getType() == 1) && (der.getType() == 11);
		boolean floatList = (izq.getType() == 1) && (der.getType() == 6);
		boolean floatBoolean = (izq.getType() == 1) && (der.getType() == 5);
		
		boolean booleanInt = (izq.getType() == 5) && (der.getType() == 0);
		boolean booleanString = (izq.getType() == 5) && (der.getType() == 3);
		boolean booleanLong = (izq.getType() == 5) && (der.getType() == 2);
		boolean booleanFloat = (izq.getType() == 5) && (der.getType() == 1);
		boolean booleanBoolean = (izq.getType() == 5) && (der.getType() == 5);
		boolean booleanTuple = (izq.getType() == 5) && (der.getType() == 11);
		boolean booleanList = (izq.getType() == 5) && (der.getType() == 6);
		
		boolean tupleInt = (izq.getType() == 11) && (der.getType() == 0);
		boolean tupleString = (izq.getType() == 11) && (der.getType() == 3);
		boolean tupleLong = (izq.getType() == 11) && (der.getType() == 2);
		boolean tupleBoolean = (izq.getType() == 11) && (der.getType() == 5);
		boolean tupleFloat = (izq.getType() == 11) && (der.getType() == 1);
		boolean tupleTuple = (izq.getType() == 11) && (der.getType() == 11);
		boolean tupleList = (izq.getType() == 11) && (der.getType() == 6);
		
		boolean listInt = (izq.getType() == 6) && (der.getType() == 0);
		boolean listString = (izq.getType() == 6) && (der.getType() == 3);
		boolean listLong = (izq.getType() == 6) && (der.getType() == 2);
		boolean listBoolean = (izq.getType() == 6) && (der.getType() == 5);
		boolean listFloat = (izq.getType() == 6) && (der.getType() == 1);
		boolean listTuple = (izq.getType() == 6) && (der.getType() == 11);
		boolean listList = (izq.getType() == 6) && (der.getType() == 6);
		
		if(intInt){
			boolean resultado = ((IntegerType)izq).getValue() == ((IntegerType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(intLong){
			boolean resultado = ((IntegerType)izq).getValue() == ((LongType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(intFloat){
			boolean resultado = ((IntegerType)izq).getValue() == ((FloatType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(intBoolean){
			boolean resultado = ((IntegerType)izq).getValue() == ((BooleanType)der).getEquivalentInt();
			res = new BooleanType(resultado);
		}
		else if(intString){
			res = new BooleanType(false);
		}
		else if(intTuple){
			res = new BooleanType(false);
		}
		else if((stringInt) || (stringBoolean) || (stringLong) || (stringFloat) || (stringTuple)) {
			res = new BooleanType(false);
		}
		else if(stringString){
			boolean resultado = ((StringType)izq).getText().equals(((StringType)der).getText());
			res = new BooleanType(resultado);					
		}
		else if(longInt){
			boolean resultado = ((LongType)izq).getValue() == ((IntegerType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(longLong){
			boolean resultado = ((LongType)izq).getValue() == ((LongType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(longFloat){
			boolean resultado = ((LongType)izq).getValue() == ((FloatType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(longBoolean){
			boolean resultado = ((LongType)izq).getValue() == ((BooleanType)der).getEquivalentInt();
			res = new BooleanType(resultado);
		}
		else if(longString){
			res = new BooleanType(false);
		}
		else if(longTuple){
			res = new BooleanType(false);
		}
		else if(floatInt){
			boolean resultado = ((FloatType)izq).getValue() == ((IntegerType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(floatLong){
			boolean resultado = ((FloatType)izq).getValue() == ((LongType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(floatFloat){
			boolean resultado = ((FloatType)izq).getValue() == ((FloatType)der).getValue();
			res = new BooleanType(resultado);					
		}
		else if(floatBoolean){
			boolean resultado = ((FloatType)izq).getValue() == ((BooleanType)der).getEquivalentInt();
			res = new BooleanType(resultado);
		}
		else if(floatString){
			res = new BooleanType(false);					
		}
		else if(floatTuple){
			res = new BooleanType(false);					
		}
		else if(booleanInt){
			boolean resultado = ((BooleanType)izq).getEquivalentInt() == ((IntegerType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(booleanLong){
			boolean resultado = ((BooleanType)izq).getEquivalentInt() == ((LongType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(booleanFloat){
			boolean resultado = ((BooleanType)izq).getEquivalentInt() == ((FloatType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(booleanBoolean){
			boolean resultado = ((BooleanType)izq).getValue() == ((BooleanType)der).getValue();
			res = new BooleanType(resultado);
			
		}
		else if (booleanString){
			res = new BooleanType(false);
		}
		else if (booleanTuple){
			res = new BooleanType(false);
		}
		else if((tupleInt) || (tupleBoolean) || (tupleLong) || (tupleFloat) || (tupleString)) {
			res = new BooleanType(false);
		}
		else if(tupleTuple){
			ArrayList<Type> tupleIzq = ((TupleType)izq).getTuple();
			ArrayList<Type> tupleDer = ((TupleType)der).getTuple();
			boolean resultado = tupleIzq.equals(tupleDer);
			res = new BooleanType(resultado);					
		}
	    else {
            throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion or");
	    }
		
		return res;
	}
	private Type notEquals(Type izq, Type der) throws CompilerException{
		Type res = null;
		
		boolean intInt = (izq.getType() == 0) && (der.getType() == 0);
		boolean intString = (izq.getType() == 0) && (der.getType() == 3);
		boolean intLong = (izq.getType() == 0) && (der.getType() == 2);
		boolean intFloat = (izq.getType() == 0) && (der.getType() == 1);
		boolean intBoolean = (izq.getType() == 0) && (der.getType() == 5);
		boolean intTuple = (izq.getType() == 0) && (der.getType() == 11);
		
		boolean stringInt = (izq.getType() == 3) && (der.getType() == 0);
		boolean stringString = (izq.getType() == 3) && (der.getType() == 3);
		boolean stringLong = (izq.getType() == 3) && (der.getType() == 2);
		boolean stringBoolean = (izq.getType() == 3) && (der.getType() == 5);
		boolean stringFloat = (izq.getType() == 3) && (der.getType() == 1);
		boolean stringTuple = (izq.getType() == 3) && (der.getType() == 11);
		
		boolean longInt = (izq.getType() == 2) && (der.getType() == 0);
		boolean longString = (izq.getType() == 2) && (der.getType() == 3);
		boolean longLong = (izq.getType() == 2) && (der.getType() == 3);
		boolean longFloat = (izq.getType() == 2) && (der.getType() == 1);
		boolean longBoolean = (izq.getType() == 2) && (der.getType() == 5);
		boolean longTuple = (izq.getType() == 2) && (der.getType() == 11);
		
		
		boolean floatInt = (izq.getType() == 1) && (der.getType() == 0);
		boolean floatString = (izq.getType() == 1) && (der.getType() == 3);
		boolean floatLong = (izq.getType() == 1) && (der.getType() == 2);
		boolean floatFloat = (izq.getType() == 1) && (der.getType() == 1);
		boolean floatBoolean = (izq.getType() == 1) && (der.getType() == 5);
		boolean floatTuple = (izq.getType() == 1) && (der.getType() == 11);
		
		boolean booleanInt = (izq.getType() == 5) && (der.getType() == 0);
		boolean booleanString = (izq.getType() == 5) && (der.getType() == 3);
		boolean booleanLong = (izq.getType() == 5) && (der.getType() == 2);
		boolean booleanFloat = (izq.getType() == 5) && (der.getType() == 1);
		boolean booleanBoolean = (izq.getType() == 5) && (der.getType() == 5);
		boolean booleanTuple = (izq.getType() == 5) && (der.getType() == 11);
		
		boolean tupleInt = (izq.getType() == 11) && (der.getType() == 0);
		boolean tupleString = (izq.getType() == 11) && (der.getType() == 3);
		boolean tupleLong = (izq.getType() == 11) && (der.getType() == 2);
		boolean tupleBoolean = (izq.getType() == 11) && (der.getType() == 5);
		boolean tupleFloat = (izq.getType() == 11) && (der.getType() == 1);
		boolean tupleTuple = (izq.getType() == 11) && (der.getType() == 11);

		
		
		if(intInt){
			boolean resultado = ((IntegerType)izq).getValue() != ((IntegerType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(intLong){
			boolean resultado = ((IntegerType)izq).getValue() != ((LongType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(intFloat){
			boolean resultado = ((IntegerType)izq).getValue() != ((FloatType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(intBoolean){
			boolean resultado = ((IntegerType)izq).getValue() != ((BooleanType)der).getEquivalentInt();
			res = new BooleanType(resultado);
		}
		else if((intString) || (intTuple)){
			res = new BooleanType(true);
		}
		else if((stringInt) || (stringBoolean) || (stringLong) || (stringFloat) || (stringTuple)){
			res = new BooleanType(true);
		}
		else if(stringString){
			boolean resultado = !((StringType)izq).getText().equals(((StringType)der).getText());
			res = new BooleanType(resultado);					
		}
		else if(longInt){
			boolean resultado = ((LongType)izq).getValue() != ((IntegerType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(longLong){
			boolean resultado = ((LongType)izq).getValue() != ((LongType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(longFloat){
			boolean resultado = ((LongType)izq).getValue() != ((FloatType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(longBoolean){
			boolean resultado = ((LongType)izq).getValue() != ((BooleanType)der).getEquivalentInt();
			res = new BooleanType(resultado);
		}
		else if((longString) || (longTuple)){
			res = new BooleanType(true);
		}
		else if(floatInt){
			boolean resultado = ((FloatType)izq).getValue() != ((IntegerType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(floatLong){
			boolean resultado = ((FloatType)izq).getValue() != ((LongType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(floatFloat){
			boolean resultado = ((FloatType)izq).getValue() != ((FloatType)der).getValue();
			res = new BooleanType(resultado);					
		}
		else if(floatBoolean){
			boolean resultado = ((FloatType)izq).getValue() != ((BooleanType)der).getEquivalentInt();
			res = new BooleanType(resultado);
		}
		else if((floatString) || (floatTuple)){
			res = new BooleanType(true);					
		}
		else if(booleanInt){
			boolean resultado = ((BooleanType)izq).getEquivalentInt() != ((IntegerType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(booleanLong){
			boolean resultado = ((BooleanType)izq).getEquivalentInt() != ((LongType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(booleanFloat){
			boolean resultado = ((BooleanType)izq).getEquivalentInt() != ((FloatType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(booleanBoolean){
			boolean resultado = ((BooleanType)izq).getValue() != ((BooleanType)der).getValue();
			res = new BooleanType(resultado);
			
		}
		else if ((booleanString) || (booleanTuple)){
			res = new BooleanType(true);
		}
		else if((tupleInt) || (tupleBoolean) || (tupleLong) || (tupleFloat) || (tupleString)) {
			res = new BooleanType(true);
		}
		else if(tupleTuple){
			ArrayList<Type> tupleIzq = ((TupleType)izq).getTuple();
			ArrayList<Type> tupleDer = ((TupleType)der).getTuple();
			boolean resultado = !tupleIzq.equals(tupleDer);

			res = new BooleanType(resultado);					
		}
	    else {
            throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion or");
	    }
		
		return res;
	}
	private Type menor(Type izq, Type der) throws CompilerException{
		Type res = null;
		
		boolean intInt = (izq.getType() == 0) && (der.getType() == 0);
		boolean intString = (izq.getType() == 0) && (der.getType() == 3);
		boolean intLong = (izq.getType() == 0) && (der.getType() == 2);
		boolean intFloat = (izq.getType() == 0) && (der.getType() == 1);
		boolean intTuple = (izq.getType() == 0) && (der.getType() == 11);
		boolean intBoolean = (izq.getType() == 0) && (der.getType() == 5);
		
		boolean stringInt = (izq.getType() == 3) && (der.getType() == 0);
		boolean stringString = (izq.getType() == 3) && (der.getType() == 3);
		boolean stringLong = (izq.getType() == 3) && (der.getType() == 2);
		boolean stringBoolean = (izq.getType() == 3) && (der.getType() == 5);
		boolean stringFloat = (izq.getType() == 3) && (der.getType() == 1);
		boolean stringTuple = (izq.getType() == 3) && (der.getType() == 11);
		
		boolean longInt = (izq.getType() == 2) && (der.getType() == 0);
		boolean longString = (izq.getType() == 2) && (der.getType() == 3);
		boolean longLong = (izq.getType() == 2) && (der.getType() == 3);
		boolean longFloat = (izq.getType() == 2) && (der.getType() == 1);
		boolean longTuple = (izq.getType() == 2) && (der.getType() == 11);
		boolean longBoolean = (izq.getType() == 2) && (der.getType() == 5);
		
		boolean floatInt = (izq.getType() == 1) && (der.getType() == 0);
		boolean floatString = (izq.getType() == 1) && (der.getType() == 3);
		boolean floatLong = (izq.getType() == 1) && (der.getType() == 2);
		boolean floatFloat = (izq.getType() == 1) && (der.getType() == 1);
		boolean floatTuple = (izq.getType() == 1) && (der.getType() == 11);
		boolean floatBoolean = (izq.getType() == 1) && (der.getType() == 5);
		
		boolean booleanInt = (izq.getType() == 5) && (der.getType() == 0);
		boolean booleanString = (izq.getType() == 5) && (der.getType() == 3);
		boolean booleanLong = (izq.getType() == 5) && (der.getType() == 2);
		boolean booleanFloat = (izq.getType() == 5) && (der.getType() == 1);
		boolean booleanTuple = (izq.getType() == 5) && (der.getType() == 11);
		boolean booleanBoolean = (izq.getType() == 5) && (der.getType() == 5);

		boolean tupleInt = (izq.getType() == 11) && (der.getType() == 0);
		boolean tupleString = (izq.getType() == 11) && (der.getType() == 3);
		boolean tupleLong = (izq.getType() == 11) && (der.getType() == 2);
		boolean tupleBoolean = (izq.getType() == 11) && (der.getType() == 5);
		boolean tupleFloat = (izq.getType() == 11) && (der.getType() == 1);
		boolean tupleTuple = (izq.getType() == 11) && (der.getType() == 11);
		
		
		
		if(intInt){
			boolean resultado = ((IntegerType)izq).getValue() < ((IntegerType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(intLong){
			boolean resultado = ((IntegerType)izq).getValue() < ((LongType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(intFloat){
			boolean resultado = ((IntegerType)izq).getValue() < ((FloatType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(intBoolean){
			boolean resultado = ((IntegerType)izq).getValue() < ((BooleanType)der).getEquivalentInt();
			res = new BooleanType(resultado);
	
		}
		else if((intString) || (intTuple)){
			//TODO revisar
			res = new BooleanType(true);
			
		}
		else if(stringInt){
			res = new BooleanType(false);			
		}
		else if(stringBoolean){
			res = new BooleanType(false);
		}
		else if(stringLong){
			res = new BooleanType(false);
		}
		else if(stringFloat){
			res = new BooleanType(false);
		}
		else if(stringTuple){
			res = new BooleanType(true);
		}
		else if(stringString){
			String stringIzq = ((StringType)izq).getText();
			String stringDer = ((StringType)der).getText();
			boolean resultado = stringIzq.compareTo(stringDer) < 0;
			res = new BooleanType(resultado);
		}
		else if(longInt){
			boolean resultado = ((LongType)izq).getValue() < ((IntegerType)der).getValue();
			res = new BooleanType(resultado);

		}
		else if(longLong){
			boolean resultado = ((LongType)izq).getValue() < ((LongType)der).getValue();
			res = new BooleanType(resultado);

		}
		else if(longFloat){
			boolean resultado = ((LongType)izq).getValue() < ((FloatType)der).getValue();
			res = new BooleanType(resultado);

		}
		else if(longBoolean){
			boolean resultado = ((LongType)izq).getValue() < ((BooleanType)der).getEquivalentInt();
			res = new BooleanType(resultado);

		}
		else if((longString) || (longTuple)){
			res = new BooleanType(true);
		}
		else if(floatInt){
			boolean resultado = ((FloatType)izq).getValue() < ((IntegerType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(floatLong){
			boolean resultado = ((FloatType)izq).getValue() < ((LongType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(floatFloat){
			boolean resultado = ((FloatType)izq).getValue() < ((FloatType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(floatBoolean){
			boolean resultado = ((FloatType)izq).getValue() < ((BooleanType)der).getEquivalentInt();
			res = new BooleanType(resultado);
		}
		else if((floatString) || (floatTuple)){
			res = new BooleanType(true);
		}
		else if(booleanInt){
			boolean resultado = ((BooleanType)izq).getEquivalentInt() < ((IntegerType)der).getValue();
			res = new BooleanType(resultado);

		}
		else if(booleanLong){
			boolean resultado = ((BooleanType)izq).getEquivalentInt() < ((LongType)der).getValue();
			res = new BooleanType(resultado);

		}
		else if(booleanFloat){
			boolean resultado = ((BooleanType)izq).getEquivalentInt() < ((FloatType)der).getValue();
			res = new BooleanType(resultado);

		}
		else if(booleanBoolean){
			boolean resultado = ((BooleanType)izq).getEquivalentInt() < ((BooleanType)der).getEquivalentInt();
			res = new BooleanType(resultado);

		}
		else if ((booleanString) || (booleanTuple)){
			res = new BooleanType(true);
		}
		else if((tupleInt) || (tupleBoolean) || (tupleLong) || (tupleFloat) || (tupleString)) {
			res = new BooleanType(false);
		}
		else if(tupleTuple){
			ArrayList<Type> tupleIzq = ((TupleType)izq).getTuple();
			ArrayList<Type> tupleDer = ((TupleType)der).getTuple();
			boolean resultado = false;
			if(tupleIzq.size() < tupleDer.size())
				resultado = true;
			else if (tupleDer.size() < tupleIzq.size())
				resultado = false;
			else
				for(int i = 0; i < tupleIzq.size(); i++){
					boolean menorIzq = ((BooleanType)menor(tupleIzq.get(i), tupleDer.get(i))).getBooleanValue();
					boolean menorDer = ((BooleanType)menor(tupleDer.get(i), tupleIzq.get(i))).getBooleanValue();
					if (menorIzq){
						resultado = true;
						break;
					}else if(menorDer){
						resultado = false;
						break;
					}
				}
			res = new BooleanType(resultado);					
		}
	    else {
            throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion menor");
	    }
				
		return res;
	}
	private Type mayor(Type izq, Type der) throws CompilerException{
		Type res = null;
		
		boolean intInt = (izq.getType() == 0) && (der.getType() == 0);
		boolean intString = (izq.getType() == 0) && (der.getType() == 3);
		boolean intLong = (izq.getType() == 0) && (der.getType() == 2);
		boolean intFloat = (izq.getType() == 0) && (der.getType() == 1);
		boolean intBoolean = (izq.getType() == 0) && (der.getType() == 5);
		boolean intTuple = (izq.getType() == 0) && (der.getType() == 11);
		
		boolean stringInt = (izq.getType() == 3) && (der.getType() == 0);
		boolean stringString = (izq.getType() == 3) && (der.getType() == 3);
		boolean stringLong = (izq.getType() == 3) && (der.getType() == 2);
		boolean stringBoolean = (izq.getType() == 3) && (der.getType() == 5);
		boolean stringFloat = (izq.getType() == 3) && (der.getType() == 1);
		boolean stringTuple = (izq.getType() == 3) && (der.getType() == 11);
		
		boolean longInt = (izq.getType() == 2) && (der.getType() == 0);
		boolean longString = (izq.getType() == 2) && (der.getType() == 3);
		boolean longLong = (izq.getType() == 2) && (der.getType() == 3);
		boolean longFloat = (izq.getType() == 2) && (der.getType() == 1);
		boolean longBoolean = (izq.getType() == 2) && (der.getType() == 5);
		boolean longTuple = (izq.getType() == 2) && (der.getType() == 11);
		
		boolean floatInt = (izq.getType() == 1) && (der.getType() == 0);
		boolean floatString = (izq.getType() == 1) && (der.getType() == 3);
		boolean floatLong = (izq.getType() == 1) && (der.getType() == 2);
		boolean floatFloat = (izq.getType() == 1) && (der.getType() == 1);
		boolean floatBoolean = (izq.getType() == 1) && (der.getType() == 5);
		boolean floatTuple = (izq.getType() == 1) && (der.getType() == 11);
		
		boolean booleanInt = (izq.getType() == 5) && (der.getType() == 0);
		boolean booleanString = (izq.getType() == 5) && (der.getType() == 3);
		boolean booleanLong = (izq.getType() == 5) && (der.getType() == 2);
		boolean booleanFloat = (izq.getType() == 5) && (der.getType() == 1);
		boolean booleanBoolean = (izq.getType() == 5) && (der.getType() == 5);
		boolean booleanTuple = (izq.getType() == 5) && (der.getType() == 11);
		
		boolean tupleInt = (izq.getType() == 11) && (der.getType() == 0);
		boolean tupleString = (izq.getType() == 11) && (der.getType() == 3);
		boolean tupleLong = (izq.getType() == 11) && (der.getType() == 2);
		boolean tupleBoolean = (izq.getType() == 11) && (der.getType() == 5);
		boolean tupleFloat = (izq.getType() == 11) && (der.getType() == 1);
		boolean tupleTuple = (izq.getType() == 11) && (der.getType() == 11);
		
		
		if(intInt){
			boolean resultado = ((IntegerType)izq).getValue() > ((IntegerType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(intLong){
			boolean resultado = ((IntegerType)izq).getValue() > ((LongType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(intFloat){
			boolean resultado = ((IntegerType)izq).getValue() > ((FloatType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(intBoolean){
			boolean resultado = ((IntegerType)izq).getValue() > ((BooleanType)der).getEquivalentInt();
			res = new BooleanType(resultado);
	
		}
		else if((intString) || (intTuple)){
			//TODO revisar
			res = new BooleanType(false);
			
		}
		else if(stringInt){
			res = new BooleanType(true);			
		}
		else if(stringBoolean){
			res = new BooleanType(true);
		}
		else if(stringLong){
			res = new BooleanType(true);
		}
		else if(stringFloat){
			res = new BooleanType(true);
		}
		else if(stringString){
			String stringIzq = ((StringType)izq).getText();
			String stringDer = ((StringType)der).getText();
			boolean resultado = stringIzq.compareTo(stringDer) > 0;
			res = new BooleanType(resultado);
		}

		else if(stringTuple){
			res = new BooleanType(false);
		}
		else if(longInt){
			boolean resultado = ((LongType)izq).getValue() > ((IntegerType)der).getValue();
			res = new BooleanType(resultado);

		}
		else if(longLong){
			boolean resultado = ((LongType)izq).getValue() > ((LongType)der).getValue();
			res = new BooleanType(resultado);

		}
		else if(longFloat){
			boolean resultado = ((LongType)izq).getValue() > ((FloatType)der).getValue();
			res = new BooleanType(resultado);

		}
		else if(longBoolean){
			boolean resultado = ((LongType)izq).getValue() > ((BooleanType)der).getEquivalentInt();
			res = new BooleanType(resultado);

		}
		else if((longString) || (longTuple)){
			res = new BooleanType(false);
		}
		else if(floatInt){
			boolean resultado = ((FloatType)izq).getValue() > ((IntegerType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(floatLong){
			boolean resultado = ((FloatType)izq).getValue() > ((LongType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(floatFloat){
			boolean resultado = ((FloatType)izq).getValue() > ((FloatType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(floatBoolean){
			boolean resultado = ((FloatType)izq).getValue() > ((BooleanType)der).getEquivalentInt();
			res = new BooleanType(resultado);
		}
		else if((floatString) || (floatTuple)){
			res = new BooleanType(false);
		}
		else if(booleanInt){
			boolean resultado = ((BooleanType)izq).getEquivalentInt() > ((IntegerType)der).getValue();
			res = new BooleanType(resultado);

		}
		else if(booleanLong){
			boolean resultado = ((BooleanType)izq).getEquivalentInt() > ((LongType)der).getValue();
			res = new BooleanType(resultado);

		}
		else if(booleanFloat){
			boolean resultado = ((BooleanType)izq).getEquivalentInt() > ((FloatType)der).getValue();
			res = new BooleanType(resultado);

		}
		else if(booleanBoolean){
			boolean resultado = ((BooleanType)izq).getEquivalentInt() > ((BooleanType)der).getEquivalentInt();
			res = new BooleanType(resultado);

		}
		else if ((booleanString) || (booleanTuple)){
			res = new BooleanType(false);
		}
		else if((tupleInt) || (tupleBoolean) || (tupleLong) || (tupleFloat) || (tupleString)) {
			res = new BooleanType(true);
		}
		else if(tupleTuple){
			ArrayList<Type> tupleIzq = ((TupleType)izq).getTuple();
			ArrayList<Type> tupleDer = ((TupleType)der).getTuple();
			boolean resultado = false;
			if(tupleIzq.size() > tupleDer.size())
				resultado = true;
			else if (tupleDer.size() > tupleIzq.size())
				resultado = false;
			else
				for(int i = 0; i < tupleIzq.size(); i++){
					boolean mayorIzq = ((BooleanType)mayor(tupleIzq.get(i), tupleDer.get(i))).getBooleanValue();
					boolean mayorDer = ((BooleanType)mayor(tupleDer.get(i), tupleIzq.get(i))).getBooleanValue();
					if (mayorIzq){
						resultado = true;
						break;
					}else if(mayorDer){
						resultado = false;
						break;
					}
				}
			res = new BooleanType(resultado);					
		}
	    else {
            throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion mayor");
	    }
		
		
		return res;
	}
	private Type menorIgual(Type izq, Type der) throws CompilerException{
		Type res = null;
		
		boolean intInt = (izq.getType() == 0) && (der.getType() == 0);
		boolean intString = (izq.getType() == 0) && (der.getType() == 3);
		boolean intLong = (izq.getType() == 0) && (der.getType() == 2);
		boolean intFloat = (izq.getType() == 0) && (der.getType() == 1);
		boolean intTuple = (izq.getType() == 0) && (der.getType() == 11);
		boolean intBoolean = (izq.getType() == 0) && (der.getType() == 5);
		
		boolean stringInt = (izq.getType() == 3) && (der.getType() == 0);
		boolean stringString = (izq.getType() == 3) && (der.getType() == 3);
		boolean stringLong = (izq.getType() == 3) && (der.getType() == 2);
		boolean stringBoolean = (izq.getType() == 3) && (der.getType() == 5);
		boolean stringFloat = (izq.getType() == 3) && (der.getType() == 1);
		boolean stringTuple = (izq.getType() == 3) && (der.getType() == 11);
		
		boolean longInt = (izq.getType() == 2) && (der.getType() == 0);
		boolean longString = (izq.getType() == 2) && (der.getType() == 3);
		boolean longLong = (izq.getType() == 2) && (der.getType() == 3);
		boolean longFloat = (izq.getType() == 2) && (der.getType() == 1);
		boolean longTuple = (izq.getType() == 2) && (der.getType() == 11);
		boolean longBoolean = (izq.getType() == 2) && (der.getType() == 5);
		
		boolean floatInt = (izq.getType() == 1) && (der.getType() == 0);
		boolean floatString = (izq.getType() == 1) && (der.getType() == 3);
		boolean floatLong = (izq.getType() == 1) && (der.getType() == 2);
		boolean floatFloat = (izq.getType() == 1) && (der.getType() == 1);
		boolean floatTuple = (izq.getType() == 1) && (der.getType() == 11);
		boolean floatBoolean = (izq.getType() == 1) && (der.getType() == 5);
		
		boolean booleanInt = (izq.getType() == 5) && (der.getType() == 0);
		boolean booleanString = (izq.getType() == 5) && (der.getType() == 3);
		boolean booleanLong = (izq.getType() == 5) && (der.getType() == 2);
		boolean booleanFloat = (izq.getType() == 5) && (der.getType() == 1);
		boolean booleanTuple = (izq.getType() == 5) && (der.getType() == 11);
		boolean booleanBoolean = (izq.getType() == 5) && (der.getType() == 5);

		boolean tupleInt = (izq.getType() == 11) && (der.getType() == 0);
		boolean tupleString = (izq.getType() == 11) && (der.getType() == 3);
		boolean tupleLong = (izq.getType() == 11) && (der.getType() == 2);
		boolean tupleBoolean = (izq.getType() == 11) && (der.getType() == 5);
		boolean tupleFloat = (izq.getType() == 11) && (der.getType() == 1);
		boolean tupleTuple = (izq.getType() == 11) && (der.getType() == 11);
		
		
		
		if(intInt){
			boolean resultado = ((IntegerType)izq).getValue() <= ((IntegerType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(intLong){
			boolean resultado = ((IntegerType)izq).getValue() <= ((LongType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(intFloat){
			boolean resultado = ((IntegerType)izq).getValue() <= ((FloatType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(intBoolean){
			boolean resultado = ((IntegerType)izq).getValue() <= ((BooleanType)der).getEquivalentInt();
			res = new BooleanType(resultado);
	
		}
		else if((intString) || (intTuple)){
			//TODO revisar
			res = new BooleanType(true);
			
		}
		else if(stringInt){
			res = new BooleanType(false);			
		}
		else if(stringBoolean){
			res = new BooleanType(false);
		}
		else if(stringLong){
			res = new BooleanType(false);
		}
		else if(stringFloat){
			res = new BooleanType(false);
		}
		else if(stringTuple){
			res = new BooleanType(true);
		}
		else if(stringString){
			String stringIzq = ((StringType)izq).getText();
			String stringDer = ((StringType)der).getText();
			boolean resultado = stringIzq.compareTo(stringDer) <= 0;
			res = new BooleanType(resultado);
		}
		else if(longInt){
			boolean resultado = ((LongType)izq).getValue() <= ((IntegerType)der).getValue();
			res = new BooleanType(resultado);

		}
		else if(longLong){
			boolean resultado = ((LongType)izq).getValue() <= ((LongType)der).getValue();
			res = new BooleanType(resultado);

		}
		else if(longFloat){
			boolean resultado = ((LongType)izq).getValue() <= ((FloatType)der).getValue();
			res = new BooleanType(resultado);

		}
		else if(longBoolean){
			boolean resultado = ((LongType)izq).getValue() <= ((BooleanType)der).getEquivalentInt();
			res = new BooleanType(resultado);

		}
		else if((longString) || (longTuple)){
			res = new BooleanType(true);
		}
		else if(floatInt){
			boolean resultado = ((FloatType)izq).getValue() <= ((IntegerType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(floatLong){
			boolean resultado = ((FloatType)izq).getValue() <= ((LongType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(floatFloat){
			boolean resultado = ((FloatType)izq).getValue() <= ((FloatType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(floatBoolean){
			boolean resultado = ((FloatType)izq).getValue() <= ((BooleanType)der).getEquivalentInt();
			res = new BooleanType(resultado);
		}
		else if((floatString) || (floatTuple)){
			res = new BooleanType(true);
		}
		else if(booleanInt){
			boolean resultado = ((BooleanType)izq).getEquivalentInt() <= ((IntegerType)der).getValue();
			res = new BooleanType(resultado);

		}
		else if(booleanLong){
			boolean resultado = ((BooleanType)izq).getEquivalentInt() <= ((LongType)der).getValue();
			res = new BooleanType(resultado);

		}
		else if(booleanFloat){
			boolean resultado = ((BooleanType)izq).getEquivalentInt() <= ((FloatType)der).getValue();
			res = new BooleanType(resultado);

		}
		else if(booleanBoolean){
			boolean resultado = ((BooleanType)izq).getEquivalentInt() <= ((BooleanType)der).getEquivalentInt();
			res = new BooleanType(resultado);

		}
		else if ((booleanString) || (booleanTuple)){
			res = new BooleanType(true);
		}
		else if((tupleInt) || (tupleBoolean) || (tupleLong) || (tupleFloat) || (tupleString)) {
			res = new BooleanType(false);
		}
		else if(tupleTuple){
			ArrayList<Type> tupleIzq = ((TupleType)izq).getTuple();
			ArrayList<Type> tupleDer = ((TupleType)der).getTuple();
			boolean resultado = false;
			if(tupleIzq.size() < tupleDer.size())
				resultado = true;
			else if (tupleDer.size() < tupleIzq.size())
				resultado = false;
			else
				for(int i = 0; i < tupleIzq.size(); i++){
					boolean menorIzq = ((BooleanType)menorIgual(tupleIzq.get(i), tupleDer.get(i))).getBooleanValue();
					boolean menorDer = ((BooleanType)menorIgual(tupleDer.get(i), tupleIzq.get(i))).getBooleanValue();
					if (menorIzq){
						resultado = true;
						break;
					}else if(menorDer){
						resultado = false;
						break;
					}
				}
			res = new BooleanType(resultado);					
		}
	    else {
            throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion menor o igual");
	    }
		
		return res;
	}
	private Type mayorIgual(Type izq, Type der) throws CompilerException{
		Type res = null;
		
		boolean intInt = (izq.getType() == 0) && (der.getType() == 0);
		boolean intString = (izq.getType() == 0) && (der.getType() == 3);
		boolean intLong = (izq.getType() == 0) && (der.getType() == 2);
		boolean intFloat = (izq.getType() == 0) && (der.getType() == 1);
		boolean intBoolean = (izq.getType() == 0) && (der.getType() == 5);
		boolean intTuple = (izq.getType() == 0) && (der.getType() == 11);
		
		boolean stringInt = (izq.getType() == 3) && (der.getType() == 0);
		boolean stringString = (izq.getType() == 3) && (der.getType() == 3);
		boolean stringLong = (izq.getType() == 3) && (der.getType() == 2);
		boolean stringBoolean = (izq.getType() == 3) && (der.getType() == 5);
		boolean stringFloat = (izq.getType() == 3) && (der.getType() == 1);
		boolean stringTuple = (izq.getType() == 3) && (der.getType() == 11);
		
		boolean longInt = (izq.getType() == 2) && (der.getType() == 0);
		boolean longString = (izq.getType() == 2) && (der.getType() == 3);
		boolean longLong = (izq.getType() == 2) && (der.getType() == 3);
		boolean longFloat = (izq.getType() == 2) && (der.getType() == 1);
		boolean longBoolean = (izq.getType() == 2) && (der.getType() == 5);
		boolean longTuple = (izq.getType() == 2) && (der.getType() == 11);
		
		boolean floatInt = (izq.getType() == 1) && (der.getType() == 0);
		boolean floatString = (izq.getType() == 1) && (der.getType() == 3);
		boolean floatLong = (izq.getType() == 1) && (der.getType() == 2);
		boolean floatFloat = (izq.getType() == 1) && (der.getType() == 1);
		boolean floatBoolean = (izq.getType() == 1) && (der.getType() == 5);
		boolean floatTuple = (izq.getType() == 1) && (der.getType() == 11);
		
		boolean booleanInt = (izq.getType() == 5) && (der.getType() == 0);
		boolean booleanString = (izq.getType() == 5) && (der.getType() == 3);
		boolean booleanLong = (izq.getType() == 5) && (der.getType() == 2);
		boolean booleanFloat = (izq.getType() == 5) && (der.getType() == 1);
		boolean booleanBoolean = (izq.getType() == 5) && (der.getType() == 5);
		boolean booleanTuple = (izq.getType() == 5) && (der.getType() == 11);
		
		boolean tupleInt = (izq.getType() == 11) && (der.getType() == 0);
		boolean tupleString = (izq.getType() == 11) && (der.getType() == 3);
		boolean tupleLong = (izq.getType() == 11) && (der.getType() == 2);
		boolean tupleBoolean = (izq.getType() == 11) && (der.getType() == 5);
		boolean tupleFloat = (izq.getType() == 11) && (der.getType() == 1);
		boolean tupleTuple = (izq.getType() == 11) && (der.getType() == 11);
		
		
		if(intInt){
			boolean resultado = ((IntegerType)izq).getValue() > ((IntegerType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(intLong){
			boolean resultado = ((IntegerType)izq).getValue() > ((LongType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(intFloat){
			boolean resultado = ((IntegerType)izq).getValue() > ((FloatType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(intBoolean){
			boolean resultado = ((IntegerType)izq).getValue() > ((BooleanType)der).getEquivalentInt();
			res = new BooleanType(resultado);
	
		}
		else if((intString) || (intTuple)){
			//TODO revisar
			res = new BooleanType(false);
			
		}
		else if(stringInt){
			res = new BooleanType(true);			
		}
		else if(stringBoolean){
			res = new BooleanType(true);
		}
		else if(stringLong){
			res = new BooleanType(true);
		}
		else if(stringFloat){
			res = new BooleanType(true);
		}
		else if(stringString){
			String stringIzq = ((StringType)izq).getText();
			String stringDer = ((StringType)der).getText();
			boolean resultado = stringIzq.compareTo(stringDer) > 0;
			res = new BooleanType(resultado);
		}

		else if(stringTuple){
			res = new BooleanType(false);
		}
		else if(longInt){
			boolean resultado = ((LongType)izq).getValue() >= ((IntegerType)der).getValue();
			res = new BooleanType(resultado);

		}
		else if(longLong){
			boolean resultado = ((LongType)izq).getValue() >= ((LongType)der).getValue();
			res = new BooleanType(resultado);

		}
		else if(longFloat){
			boolean resultado = ((LongType)izq).getValue() >= ((FloatType)der).getValue();
			res = new BooleanType(resultado);

		}
		else if(longBoolean){
			boolean resultado = ((LongType)izq).getValue() >= ((BooleanType)der).getEquivalentInt();
			res = new BooleanType(resultado);

		}
		else if((longString) || (longTuple)){
			res = new BooleanType(false);
		}
		else if(floatInt){
			boolean resultado = ((FloatType)izq).getValue() >= ((IntegerType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(floatLong){
			boolean resultado = ((FloatType)izq).getValue() >= ((LongType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(floatFloat){
			boolean resultado = ((FloatType)izq).getValue() >= ((FloatType)der).getValue();
			res = new BooleanType(resultado);
		}
		else if(floatBoolean){
			boolean resultado = ((FloatType)izq).getValue() >= ((BooleanType)der).getEquivalentInt();
			res = new BooleanType(resultado);
		}
		else if((floatString) || (floatTuple)){
			res = new BooleanType(false);
		}
		else if(booleanInt){
			boolean resultado = ((BooleanType)izq).getEquivalentInt() >= ((IntegerType)der).getValue();
			res = new BooleanType(resultado);

		}
		else if(booleanLong){
			boolean resultado = ((BooleanType)izq).getEquivalentInt() >= ((LongType)der).getValue();
			res = new BooleanType(resultado);

		}
		else if(booleanFloat){
			boolean resultado = ((BooleanType)izq).getEquivalentInt() >= ((FloatType)der).getValue();
			res = new BooleanType(resultado);

		}
		else if(booleanBoolean){
			boolean resultado = ((BooleanType)izq).getEquivalentInt() >= ((BooleanType)der).getEquivalentInt();
			res = new BooleanType(resultado);

		}
		else if ((booleanString) || (booleanTuple)){
			res = new BooleanType(false);
		}
		else if((tupleInt) || (tupleBoolean) || (tupleLong) || (tupleFloat) || (tupleString)) {
			res = new BooleanType(true);
		}
		else if(tupleTuple){
			ArrayList<Type> tupleIzq = ((TupleType)izq).getTuple();
			ArrayList<Type> tupleDer = ((TupleType)der).getTuple();
			boolean resultado = false;
			if(tupleIzq.size() > tupleDer.size())
				resultado = true;
			else if (tupleDer.size() > tupleIzq.size())
				resultado = false;
			else
				for(int i = 0; i < tupleIzq.size(); i++){
					boolean mayorIzq = ((BooleanType)mayorIgual(tupleIzq.get(i), tupleDer.get(i))).getBooleanValue();
					boolean mayorDer = ((BooleanType)mayorIgual(tupleDer.get(i), tupleIzq.get(i))).getBooleanValue();
					if (mayorIzq){
						resultado = true;
						break;
					}else if(mayorDer){
						resultado = false;
						break;
					}
				}
			res = new BooleanType(resultado);					
		}
	    else {
            throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion mayor o igual");
	    }
		
		
		return res;
	}
	private Type andBit(Type izq, Type der) throws CompilerException{
		Type res = null;
		
		boolean intInt = (izq.getType() == 0) && (der.getType() == 0);
		boolean intLong = (izq.getType() == 0) && (der.getType() == 2);
		boolean intBoolean = (izq.getType() == 0) && (der.getType() == 5);
		
		boolean longInt = (izq.getType() == 2) && (der.getType() == 0);
		boolean longLong = (izq.getType() == 2) && (der.getType() == 3);
		boolean longBoolean = (izq.getType() == 2) && (der.getType() == 5);
		
		boolean booleanInt = (izq.getType() == 5) && (der.getType() == 0);
		boolean booleanLong = (izq.getType() == 5) && (der.getType() == 2);
		boolean booleanBoolean = (izq.getType() == 5) && (der.getType() == 5);
		
		
		if(intInt){
			int resultado = ((IntegerType)izq).getValue() & ((IntegerType)der).getValue();
			res = new IntegerType(resultado);
		}
		else if(intLong){
			long resultado = ((IntegerType)izq).getValue() & ((LongType)der).getValue();
			res = new LongType(resultado);
		}
		else if(intBoolean){
			int resultado = ((IntegerType)izq).getValue() & ((BooleanType)der).getEquivalentInt();
			res = new IntegerType(resultado);
	
		}
		else if(longInt){
			long resultado = ((LongType)izq).getValue() & ((IntegerType)der).getValue();
			res = new LongType(resultado);

		}
		else if(longLong){
			long resultado = ((LongType)izq).getValue() & ((LongType)der).getValue();
			res = new LongType(resultado);

		}
		else if(longBoolean){
			long resultado = ((LongType)izq).getValue() & ((BooleanType)der).getEquivalentInt();
			res = new LongType(resultado);

		}
		else if(booleanInt){
			int resultado = ((BooleanType)izq).getEquivalentInt() & ((IntegerType)der).getValue();
			res = new IntegerType(resultado);

		}
		else if(booleanLong){
			long resultado = ((BooleanType)izq).getEquivalentInt() & ((LongType)der).getValue();
			res = new LongType(resultado);
		}
		else if(booleanBoolean){
			boolean resultado = ((BooleanType)izq).getValue() & ((BooleanType)der).getValue();
			res = new BooleanType(resultado);
		}
		else {
            throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion and bitwise");
	    }
		
		return res;
	}
	private Type orBit(Type izq, Type der) throws CompilerException{
		Type res = null;
		
		boolean intInt = (izq.getType() == 0) && (der.getType() == 0);
		boolean intLong = (izq.getType() == 0) && (der.getType() == 2);
		boolean intBoolean = (izq.getType() == 0) && (der.getType() == 5);
		
		boolean longInt = (izq.getType() == 2) && (der.getType() == 0);
		boolean longLong = (izq.getType() == 2) && (der.getType() == 3);
		boolean longBoolean = (izq.getType() == 2) && (der.getType() == 5);
		
		boolean booleanInt = (izq.getType() == 5) && (der.getType() == 0);
		boolean booleanLong = (izq.getType() == 5) && (der.getType() == 2);
		boolean booleanBoolean = (izq.getType() == 5) && (der.getType() == 5);
		
		
		if(intInt){
			int resultado = ((IntegerType)izq).getValue() | ((IntegerType)der).getValue();
			res = new IntegerType(resultado);
		}
		else if(intLong){
			long resultado = ((IntegerType)izq).getValue() | ((LongType)der).getValue();
			res = new LongType(resultado);
		}
		else if(intBoolean){
			int resultado = ((IntegerType)izq).getValue() | ((BooleanType)der).getEquivalentInt();
			res = new IntegerType(resultado);
	
		}
		else if(longInt){
			long resultado = ((LongType)izq).getValue() | ((IntegerType)der).getValue();
			res = new LongType(resultado);

		}
		else if(longLong){
			long resultado = ((LongType)izq).getValue() | ((LongType)der).getValue();
			res = new LongType(resultado);

		}
		else if(longBoolean){
			long resultado = ((LongType)izq).getValue() | ((BooleanType)der).getEquivalentInt();
			res = new LongType(resultado);

		}
		else if(booleanInt){
			int resultado = ((BooleanType)izq).getEquivalentInt() | ((IntegerType)der).getValue();
			res = new IntegerType(resultado);

		}
		else if(booleanLong){
			long resultado = ((BooleanType)izq).getEquivalentInt() | ((LongType)der).getValue();
			res = new LongType(resultado);
		}
		else if(booleanBoolean){
			boolean resultado = ((BooleanType)izq).getValue() | ((BooleanType)der).getValue();
			res = new BooleanType(resultado);
		}
		else {
            throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion or bitwise");
	    }
		
		return res;
	}
	private Type xorBit(Type izq, Type der) throws CompilerException{
		Type res = null;
		
		boolean intInt = (izq.getType() == 0) && (der.getType() == 0);
		boolean intLong = (izq.getType() == 0) && (der.getType() == 2);
		boolean intBoolean = (izq.getType() == 0) && (der.getType() == 5);
		
		boolean longInt = (izq.getType() == 2) && (der.getType() == 0);
		boolean longLong = (izq.getType() == 2) && (der.getType() == 3);
		boolean longBoolean = (izq.getType() == 2) && (der.getType() == 5);
		
		boolean booleanInt = (izq.getType() == 5) && (der.getType() == 0);
		boolean booleanLong = (izq.getType() == 5) && (der.getType() == 2);
		boolean booleanBoolean = (izq.getType() == 5) && (der.getType() == 5);
		
		
		if(intInt){
			int resultado = ((IntegerType)izq).getValue() ^ ((IntegerType)der).getValue();
			res = new IntegerType(resultado);
		}
		else if(intLong){
			long resultado = ((IntegerType)izq).getValue() ^ ((LongType)der).getValue();
			res = new LongType(resultado);
		}
		else if(intBoolean){
			int resultado = ((IntegerType)izq).getValue() ^ ((BooleanType)der).getEquivalentInt();
			res = new IntegerType(resultado);
	
		}
		else if(longInt){
			long resultado = ((LongType)izq).getValue() ^ ((IntegerType)der).getValue();
			res = new LongType(resultado);

		}
		else if(longLong){
			long resultado = ((LongType)izq).getValue() ^ ((LongType)der).getValue();
			res = new LongType(resultado);

		}
		else if(longBoolean){
			long resultado = ((LongType)izq).getValue() ^ ((BooleanType)der).getEquivalentInt();
			res = new LongType(resultado);

		}
		else if(booleanInt){
			int resultado = ((BooleanType)izq).getEquivalentInt() ^ ((IntegerType)der).getValue();
			res = new IntegerType(resultado);

		}
		else if(booleanLong){
			long resultado = ((BooleanType)izq).getEquivalentInt() ^ ((LongType)der).getValue();
			res = new LongType(resultado);
		}
		else if(booleanBoolean){
			boolean resultado = ((BooleanType)izq).getValue() ^ ((BooleanType)der).getValue();
			res = new BooleanType(resultado);
		}
		else {
            throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion xor");
	    }
		
		
		return res;
	}
	private Type rigthShiftBit(Type izq, Type der) throws CompilerException{
		Type res = null;
		
		boolean intInt = (izq.getType() == 0) && (der.getType() == 0);
		boolean intLong = (izq.getType() == 0) && (der.getType() == 2);
		boolean intBoolean = (izq.getType() == 0) && (der.getType() == 5);
		
		boolean longInt = (izq.getType() == 2) && (der.getType() == 0);
		boolean longLong = (izq.getType() == 2) && (der.getType() == 3);
		boolean longBoolean = (izq.getType() == 2) && (der.getType() == 5);
		
		boolean booleanInt = (izq.getType() == 5) && (der.getType() == 0);
		boolean booleanLong = (izq.getType() == 5) && (der.getType() == 2);
		boolean booleanBoolean = (izq.getType() == 5) && (der.getType() == 5);
		
		
		if(intInt){
			int resultado = ((IntegerType)izq).getValue() >> ((IntegerType)der).getValue();
			res = new IntegerType(resultado);
		}
		else if(intLong){
			long resultado = ((IntegerType)izq).getValue() >> ((LongType)der).getValue();
			res = new LongType(resultado);
		}
		else if(intBoolean){
			int resultado = ((IntegerType)izq).getValue() >> ((BooleanType)der).getEquivalentInt();
			res = new IntegerType(resultado);
	
		}
		else if(longInt){
			long resultado = ((LongType)izq).getValue() >> ((IntegerType)der).getValue();
			res = new LongType(resultado);

		}
		else if(longLong){
			long resultado = ((LongType)izq).getValue() >> ((LongType)der).getValue();
			res = new LongType(resultado);

		}
		else if(longBoolean){
			long resultado = ((LongType)izq).getValue() >> ((BooleanType)der).getEquivalentInt();
			res = new LongType(resultado);

		}
		else if(booleanInt){
			int resultado = ((BooleanType)izq).getEquivalentInt() >> ((IntegerType)der).getValue();
			res = new IntegerType(resultado);

		}
		else if(booleanLong){
			long resultado = ((BooleanType)izq).getEquivalentInt() >> ((LongType)der).getValue();
			res = new LongType(resultado);
		}
		else if(booleanBoolean){
			int resultado = ((BooleanType)izq).getEquivalentInt() >> ((BooleanType)der).getEquivalentInt();
			res = new IntegerType(resultado);
		}
		else {
            throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion desplazamiento derecho");
	    }
		
		return res;
	}
	private Type leftShiftBit(Type izq, Type der) throws CompilerException{
		Type res = null;
		
		boolean intInt = (izq.getType() == 0) && (der.getType() == 0);
		boolean intLong = (izq.getType() == 0) && (der.getType() == 2);
		boolean intBoolean = (izq.getType() == 0) && (der.getType() == 5);
		
		boolean longInt = (izq.getType() == 2) && (der.getType() == 0);
		boolean longLong = (izq.getType() == 2) && (der.getType() == 3);
		boolean longBoolean = (izq.getType() == 2) && (der.getType() == 5);
		
		boolean booleanInt = (izq.getType() == 5) && (der.getType() == 0);
		boolean booleanLong = (izq.getType() == 5) && (der.getType() == 2);
		boolean booleanBoolean = (izq.getType() == 5) && (der.getType() == 5);
		
		
		if(intInt){
			int resultado = ((IntegerType)izq).getValue() << ((IntegerType)der).getValue();
			res = new IntegerType(resultado);
		}
		else if(intLong){
			long resultado = ((IntegerType)izq).getValue() << ((LongType)der).getValue();
			res = new LongType(resultado);
		}
		else if(intBoolean){
			int resultado = ((IntegerType)izq).getValue() << ((BooleanType)der).getEquivalentInt();
			res = new IntegerType(resultado);
	
		}
		else if(longInt){
			long resultado = ((LongType)izq).getValue() << ((IntegerType)der).getValue();
			res = new LongType(resultado);

		}
		else if(longLong){
			long resultado = ((LongType)izq).getValue() << ((LongType)der).getValue();
			res = new LongType(resultado);

		}
		else if(longBoolean){
			long resultado = ((LongType)izq).getValue() << ((BooleanType)der).getEquivalentInt();
			res = new LongType(resultado);

		}
		else if(booleanInt){
			int resultado = ((BooleanType)izq).getEquivalentInt() << ((IntegerType)der).getValue();
			res = new IntegerType(resultado);

		}
		else if(booleanLong){
			long resultado = ((BooleanType)izq).getEquivalentInt() << ((LongType)der).getValue();
			res = new LongType(resultado);
		}
		else if(booleanBoolean){
			int resultado = ((BooleanType)izq).getEquivalentInt() << ((BooleanType)der).getEquivalentInt();
			res = new IntegerType(resultado);
		}
		else {
            throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion desplazamiento izquierdo");
	    }
		
		return res;
	}
	
	
	
	
	public Type evaluate() throws CompilerException {

        Type res = null;
		Type izq =left.evaluate();
		Type der = right.evaluate();

		if (izq == null || der == null){
		    throw new CompilerException(lineNumber, "La funcion evaluada no tiene valor de retorno");
		} else {
			if (op.equals("+")) { // SUMA
				res = suma(izq, der);		    
			} else if (op.equals("-")) { // RESTA
				res = resta(izq, der);				
			} else if (op.equals("*")) { // MULTIPLICACION
				res = multiplicacion(izq, der);
			} else if (op.equals("/")) { // DIVISION
				res = division(izq, der);
			} else if (op.equals("//")) { // DIVISION ENTERA
				res = divisionEntera(izq, der);
			}else if (op.equals("%")) { // MODULO
				res = modulo(izq, der);
			} else if (op.equals("**")) { // EXPONENTE
				res = exponente(izq, der);
			} else if (op.equals("and")) { // AND
				res = and(izq, der);
			} else if (op.equals("or")) { // OR
				res = or(izq, der);
			} else if (op.equals("==")) { // EQUALS
				res = equals(izq, der);
			} else if (op.equals("!=")) { // NOT EQUALS
				res = notEquals(izq, der);
			} else if (op.equals("<")) { // MENOR QUE
				res = menor(izq, der);
			} else if (op.equals(">")){ // MAYOR QUE
				res = mayor(izq, der);
			} else if (op.equals("<=")){ // MENOR O IGUAL
				res = menorIgual(izq, der);
			} else if (op.equals(">=")){ // MAYOR O IGUAL
				res = mayorIgual(izq, der);
			} else if (op.equals("&")){ // AND BIT
				res = andBit(izq, der);
			} else if (op.equals("|")){ // OR BIT
				res = orBit(izq, der);
			} else if (op.equals("^")){ // XOR BIT
				res = xorBit(izq, der);
			} else if (op.equals("<<")){ // LEFT SHIFT
				res = leftShiftBit(izq, der);
			} else if (op.equals(">>")){ // RIGHT SHIFT
				res = rigthShiftBit(izq, der);
			}
			
		}
		return res;
    }
	
	
	
}
