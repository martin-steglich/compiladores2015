package com.language.model.expression;

import com.language.exceptions.CompilerException;
import com.language.model.expression.Expression;
import com.language.model.type.*;


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
		return "Type EBinaryOperation";
	}

	public String Convert (float number){
      return Float.toString(number);
    }

	public Type evaluate() throws CompilerException {

        Type res = null;
		Type izq =left.evaluate();
		Type der = right.evaluate();

		if (izq == null || der == null){
		    throw new CompilerException(lineNumber, "La funcion evaluada no tiene valor de retorno");
		} else {
			boolean intInt = (izq.getType() == 0) && (der.getType() == 0);
			boolean intString = (izq.getType() == 0) && (der.getType() == 4);
			boolean intLong = (izq.getType() == 0) && (der.getType() == 2);
			boolean intFloat = (izq.getType() == 0) && (der.getType() == 1);
			boolean intBoolean = (izq.getType() == 0) && (der.getType() == 5);
			
			boolean stringInt = (izq.getType() == 3) && (der.getType() == 0);
			boolean stringString = (izq.getType() == 3) && (der.getType() == 3);
			boolean stringLong = (izq.getType() == 3) && (der.getType() == 2);
			boolean stringBoolean = (izq.getType() == 3) && (der.getType() == 5);
			
			boolean longInt = (izq.getType() == 2) && (der.getType() == 0);
			boolean longString = (izq.getType() == 2) && (der.getType() == 4);
			boolean longLong = (izq.getType() == 2) && (der.getType() == 3);
			boolean longFloat = (izq.getType() == 2) && (der.getType() == 1);
			boolean longBoolean = (izq.getType() == 2) && (der.getType() == 5);
			
			boolean floatInt = (izq.getType() == 1) && (der.getType() == 0);
//			boolean floatString = (izq.getType() == 1) && (der.getType() == 4);
			boolean floatLong = (izq.getType() == 1) && (der.getType() == 3);
			boolean floatFloat = (izq.getType() == 1) && (der.getType() == 1);
			boolean floatBoolean = (izq.getType() == 1) && (der.getType() == 5);
			
			boolean booleanInt = (izq.getType() == 5) && (der.getType() == 0);
			boolean booleanString = (izq.getType() == 5) && (der.getType() == 4);
			boolean booleanLong = (izq.getType() == 5) && (der.getType() == 3);
			boolean booleanFloat = (izq.getType() == 5) && (der.getType() == 1);
			boolean booleanBoolean = (izq.getType() == 5) && (der.getType() == 5);

			if (op.equals("+")) { // SUMA
				
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
				}
				else{
					throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion suma");
				}
			    
			} else if (op.equals("-")) { // RESTA
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
			} else if (op.equals("*")) { // MULTIPLICACION
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
				}
				else if(stringString){
					String resultado = ((StringType)izq).getText() + ((StringType)der).getText();
					res = new StringType(resultado);
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
			    else {
                    throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion multiplicacion");
				}
			} else if (op.equals("/")) { // DIVISION
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
			}
			/*} else if (op.equals(">")){ // MAYOR
			    if (floatAndFloat) {
                    res = new TBoolean(((TFloat)izq).getValue() > ((TFloat)der).getValue());
			    }
			    else if (intAndInt) {
                    res = new TBoolean(((TInteger)izq).getValue() > ((TInteger)der).getValue());
			    }
			    else if (intAndFloat) {
                    res = new TBoolean(((TInteger)izq).getValue() > ((TFloat)der).getValue());
			    }
			    else if (floatAndInt) {
                    res = new TBoolean(((TFloat)izq).getValue() > ((TInteger)der).getValue());
			    }
			    else if(izq.getType()==3&&der.getType()==3){
					boolean izqB = ((TBoolean)izq).getValue();
					boolean derB = ((TBoolean)der).getValue();
					if(izqB){
						if(derB){
							res = new TBoolean(false);
						}
						else{
							res = new TBoolean(true);
						}
					}
					else{
						res = new TBoolean(false);
					}
			   }
			    else {
                    throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion mayor");
				}
			} else if (op.equals(">=")) { // MAYOR IGUAL
				if (floatAndFloat) {
                    res = new TBoolean(((TFloat)izq).getValue() >= ((TFloat)der).getValue());
			    }
				else if (intAndInt) {
                    res = new TBoolean(((TInteger)izq).getValue() >= ((TInteger)der).getValue());
			    }
				else if (intAndFloat) {
                    res = new TBoolean(((TInteger)izq).getValue() >= ((TFloat)der).getValue());
			    }
				else if (floatAndInt) {
                    res = new TBoolean(((TFloat)izq).getValue() >= ((TInteger)der).getValue());
			    }
				else if(izq.getType()==3&&der.getType()==3){
					boolean izqB = ((TBoolean)izq).getValue();
					boolean derB = ((TBoolean)der).getValue();
					if(izqB){
						res = new TBoolean(true);
					}
					else{
						if(derB){
							res = new TBoolean(false);
						}
						else{
							res = new TBoolean(true);
						}
					}
			   }
			    else {
                    throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion mayor o igual");
				}
			} else if (op.equals("<")){ // MENOR
				if (floatAndFloat) {
                    res = new TBoolean(((TFloat)izq).getValue() < ((TFloat)der).getValue());
			    }
				else if (intAndInt) {
                    res = new TBoolean(((TInteger)izq).getValue() < ((TInteger)der).getValue());
			    }
				else  if (intAndFloat) {
                    res = new TBoolean(((TInteger)izq).getValue() < ((TFloat)der).getValue());
			    }
				else  if (floatAndInt) {
                    res = new TBoolean(((TFloat)izq).getValue() < ((TInteger)der).getValue());
			    }
				else if(izq.getType()==3&&der.getType()==3){
					boolean izqB = ((TBoolean)izq).getValue();
					boolean derB = ((TBoolean)der).getValue();
					if(izqB){
						res = new TBoolean(false);
					}
					else{
						if(derB){
							res = new TBoolean(true);
						}
						else{
							res = new TBoolean(false);
						}
				  }
			   }
			   else {
                    throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion menor");
			   }
			} else if (op.equals("<=")){ // MENOR IGUAL
				if (floatAndFloat) {
                    res = new TBoolean(((TFloat)izq).getValue() <= ((TFloat)der).getValue());
			    }
				else if (intAndInt) {
                    res = new TBoolean(((TInteger)izq).getValue() <= ((TInteger)der).getValue());
			    }
				else  if (intAndFloat) {
                    res = new TBoolean(((TInteger)izq).getValue() <= ((TFloat)der).getValue());
			    }
				else if (floatAndInt) {
                    res = new TBoolean(((TFloat)izq).getValue() <= ((TInteger)der).getValue());
			    }
				else if(izq.getType()==3&&der.getType()==3){
					boolean izqB = ((TBoolean)izq).getValue();
					boolean derB = ((TBoolean)der).getValue();
					if(izqB){
						if(derB){
							res = new TBoolean(true);
						}
						else{
							res = new TBoolean(false);
						}
					}
					else{
						res = new TBoolean(true);
					}
				}
				else {
                    throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion menor o igual");
				}
			} else if (op.equals("==")){ // IGUAL
			    if (floatAndFloat) {
                    res = new TBoolean(((TFloat)izq).getValue() == ((TFloat)der).getValue());
                }
			    else  if (intAndInt) {
                    res = new TBoolean(((TInteger)izq).getValue() == ((TInteger)der).getValue());
                }
			    else if ((izq.getType() == 2) && (der.getType() == 2)) {
				    res = new TBoolean(((TString)izq).getValue() == ((TString)der).getValue());
			    }
			    else if ((izq.getType() == 3) && (der.getType() == 3)) {
				    res = new TBoolean(((TBoolean)izq).getValue() == ((TBoolean)der).getValue());
			    }
			    else if ((izq.getType() == 4) && (der.getType() == 4)) {
				    res = new TBoolean(null == null);
			    }
			    else {
			    	res = new TBoolean(false);
			    }
            } else if (op.equals("!=")){ // DISTINTO
            	if (floatAndFloat) {
                    res = new TBoolean(((TFloat)izq).getValue() != ((TFloat)der).getValue());
                }
            	else  if (intAndInt) {
                    res = new TBoolean(((TInteger)izq).getValue() != ((TInteger)der).getValue());
                }
			    else if ((izq.getType() == 2) && (der.getType() == 2)) {
				    res = new TBoolean(((TString)izq).getValue() != ((TString)der).getValue());
			    }
			    else if ((izq.getType() == 3) && (der.getType() == 3)) {
				    res = new TBoolean(((TBoolean)izq).getValue() != ((TBoolean)der).getValue());
			    }
			    else if ((izq.getType() == 4) && (der.getType() == 4)) {
				    res = new TBoolean(null != null);
			    }
			    else {
			    	res = new TBoolean(true);
			    }
			} else if (op.equals("&&")) { //AND
				boolean izqB = returnBooleanValue(izq,"AND");
				if(!izqB){
					return new TBoolean (false);
				}
				else{
					boolean derB = returnBooleanValue(der,"AND");
					res = new TBoolean (izqB&&derB);
				}
				
                
			} else if (op.equals("||")) { // OR
				boolean izqB = returnBooleanValue(izq,"OR");
				if(izqB){
					return new TBoolean (true);
				}
				else{
					boolean derB = returnBooleanValue(der,"OR");
					res = new TBoolean (izqB||derB);
				}
			}*/
		}
		return res;
    }

/*	private boolean returnBooleanValue(Type izq,String op) throws CompilerException{
		boolean res = false;
		if(izq.getType() == 0){
			TInteger izqI = (TInteger)izq;
			int i = izqI.getValue();
			if(i!=0){
				res = true;
			}
		}
		else if(izq.getType() == 1){
			TFloat izqI = (TFloat)izq;
			float i = izqI.getValue();
			if(i!=0f){
				res = true;
			}
		}
		else if(izq.getType() == 2){
			res = true;
		}
		else if(izq.getType() == 3){
			TBoolean izqI = (TBoolean)izq;
			res = izqI.getValue();
		}
		else if(izq.getType() != 4){
			throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion " + op);
		}
		return res;
	}*/

	
	
	
}
