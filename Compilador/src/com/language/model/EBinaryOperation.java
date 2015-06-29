package com.language.model;

import com.language.exceptions.CompilerException;

public class EBinaryOperation extends Expression {

	Expression  left;
	Expression right;
	String op;
    int lineNumber;

	public EBinaryOperation(Expression lop, String type, Expression rop, int linenum) {
		left = lop;
		right = rop;
		op = type;
		lineNumber = linenum;
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
		Type izq = left.evaluate();
		Type der = right.evaluate();

		if (izq == null || der == null){
		    throw new CompilerException(lineNumber, "La funcion evaluada no tiene valor de retorno");
		} else {
			boolean floatAndFloat = (izq.getType() == 1) && (der.getType() == 1);
			boolean intAndFloat = (izq.getType() == 0) && (der.getType() == 1);
			boolean floatAndInt = (izq.getType() == 1) && (der.getType() == 0);
			boolean intAndInt = (izq.getType() == 0) && (der.getType() == 0);
			if (op.equals("+")) { // SUMA
			    if (floatAndFloat) {
                    res = new TFloat(((TFloat)izq).getValue() + ((TFloat)der).getValue());
			    }
			    else if (intAndInt) {
                    res = new TInteger(((TInteger)izq).getValue() + ((TInteger)der).getValue());
			    }
			    else if (intAndFloat) {
                    res = new TFloat(((TInteger)izq).getValue() + ((TFloat)der).getValue());
			    }
			    else if (floatAndInt) {
                    res = new TFloat(((TFloat)izq).getValue() + ((TInteger)der).getValue());
			    }
			    else {
                    throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion suma");
				}
			} else if (op.equals("-")) { // RESTA
			    if (floatAndFloat) {
                    res = new TFloat(((TFloat)izq).getValue() - ((TFloat)der).getValue());
			    }
			    else if (intAndInt) {
                    res = new TInteger(((TInteger)izq).getValue() - ((TInteger)der).getValue());
			    }
			    else if (intAndFloat) {
                    res = new TFloat(((TInteger)izq).getValue() - ((TFloat)der).getValue());
			    }
			    else if (floatAndInt) {
                    res = new TFloat(((TFloat)izq).getValue() - ((TInteger)der).getValue());
			    }
			    else {
                    throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion resta");
				}
			} else if (op.equals("*")) { // MULTIPLICACION
			    if (floatAndFloat) {
                    res = new TFloat(((TFloat)izq).getValue() * ((TFloat)der).getValue());
			    }
			    else if (intAndInt) {
                    res = new TInteger(((TInteger)izq).getValue() * ((TInteger)der).getValue());
			    }
			    else if (intAndFloat) {
                    res = new TFloat(((TInteger)izq).getValue() * ((TFloat)der).getValue());
			    }
			    else if (floatAndInt) {
                    res = new TFloat(((TFloat)izq).getValue() * ((TInteger)der).getValue());
			    }
			    else {
                    throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion multiplicacion");
				}
			} else if (op.equals("/")) { // DIVISION
					if (floatAndFloat) {
						float derF = ((TFloat)der).getValue();
						if(derF==0f){
							throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
						}
	                    res = new TFloat(((TFloat)izq).getValue() / ((TFloat)der).getValue());
				    }
				    else if (intAndInt) {
				    	int derI = ((TInteger)der).getValue();
						if(derI==0){
							throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
						}
	                    res = new TInteger(((TInteger)izq).getValue() / ((TInteger)der).getValue());
				    }
				    else if (intAndFloat) {
				    	float derF = ((TFloat)der).getValue();
						if(derF==0f){
							throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
						}
	                    res = new TFloat(((TInteger)izq).getValue() / ((TFloat)der).getValue());
				    }
				    else if (floatAndInt) {
				    	float derI = ((TInteger)der).getValue();
						if(derI==0){
							throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
						}
	                    res = new TFloat(((TFloat)izq).getValue() / ((TInteger)der).getValue());
				    }
			    else {
                    throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion division");
			    }
            } else if (op.equals("//")) { // DIVISION ENTERA
                if (floatAndFloat) {
                    float derF = ((TFloat)der).getValue();
                    if(derF==0f){
                        throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
                    }
                    res = new TFloat(TInteger(((TFloat)izq).getValue() / ((TFloat)der).getValue()));
                }
                else if (intAndInt) {
                    int derI = ((TInteger)der).getValue();
                    if(derI==0){
                        throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
                    }
                    res = new TInteger(((TInteger)izq).getValue() / ((TInteger)der).getValue());
                }
                else if (intAndFloat) {
                    float derF = ((TFloat)der).getValue();
                    if(derF==0f){
                        throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
                    }
                    res = new TFloat(TInteger(((TInteger)izq).getValue() / ((TFloat)der).getValue()));
                }
                else if (floatAndInt) {
                    float derI = ((TInteger)der).getValue();
                    if(derI==0){
                        throw new CompilerException(lineNumber, "No es posible realizar una division por cero");
                    }
                    res = new TFloat(TInteger(((TFloat)izq).getValue() / ((TInteger)der).getValue()));
                }
			    else {
                    throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion division entera");
			    }
            } else if (op.equals("%")) { // MODULO
                if (floatAndFloat) {
                    float derF = ((TFloat)der).getValue();
                    if(derF==0f){
                        throw new CompilerException(lineNumber, "No es posible realizar la operacion modulo cero");
                    }
                    res = new TFloat(((TFloat)izq).getValue() % ((TFloat)der).getValue());
                }
                else if (intAndInt) {
                    int derI = ((TInteger)der).getValue();
                    if(derI==0){
                        throw new CompilerException(lineNumber, "No es posible realizar la operacion modulo cero");
                    }
                    res = new TInteger(((TInteger)izq).getValue() % ((TInteger)der).getValue());
                }
                else if (intAndFloat) {
                    float derF = ((TFloat)der).getValue();
                    if(derF==0f){
                        throw new CompilerException(lineNumber, "No es posible realizar la operacion modulo cero");
                    }
                    res = new TFloat(((TInteger)izq).getValue() % ((TFloat)der).getValue());
                }
                else if (floatAndInt) {
                    float derI = ((TInteger)der).getValue();
                    if(derI==0){
                        throw new CompilerException(lineNumber, "No es posible realizar la operacion modulo cero");
                    }
                    res = new TFloat(((TFloat)izq).getValue() % ((TInteger)der).getValue());
                }
			    else {
                    throw new CompilerException(lineNumber, "Tipos de datos no esperados para el modulo");
			    }
            } else if (op.equals("**")) { // EXPONENTE
			    if (floatAndFloat) {
                    res = new TFloat(Math.pow(((TFloat)izq).getValue(),((TFloat)der).getValue()));
			    }
			    else if (intAndInt) {
                    res = new TInteger(Math.pow(((TInteger)izq).getValue(),((TInteger)der).getValue()));
			    }
			    else if (intAndFloat) {
                    res = new TFloat(Math.pow(((TInteger)izq).getValue(),((TFloat)der).getValue());
			    }
			    else if (floatAndInt) {
                    res = new TFloat(Math.pow(((TFloat)izq).getValue(),((TInteger)der).getValue());
			    }
			    else {
                    throw new CompilerException(lineNumber, "Tipos de datos no esperados para la exponente");
				}
			} else if (op.equals(">")){ // MAYOR
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
			   /* else if(izq.getType()==3 && der.getType()==3){
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
			   }*/
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
				/*else if(izq.getType()==3&&der.getType()==3){
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
			   }*/
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
				/*else if(izq.getType()==3&&der.getType()==3){
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
			   }*/
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
			/*	else if(izq.getType()==3&&der.getType()==3){
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
				} */
				else {
                    throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion menor o igual");
				}
			} else if (op.equals("==")){ // IGUAL
			  /*  if (floatAndFloat) {
                    res = new TBoolean(((TFloat)izq).getValue() == ((TFloat)der).getValue());
                }
			    else  if (intAndInt) {
                    res = new TBoolean(((TInteger)izq).getValue() == ((TInteger)der).getValue());
                }
			    else */if ((izq.getType() == 2) && (der.getType() == 2)) {
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
			    else if ((izq.getType() == 2) && (der.getType() == 2)) { //String
				    res = new TBoolean(((TString)izq).getValue() != ((TString)der).getValue());
			    }
			    else if ((izq.getType() == 3) && (der.getType() == 3)) { //Bool
				    res = new TBoolean(((TBoolean)izq).getValue() != ((TBoolean)der).getValue());
			    }
			    else if ((izq.getType() == 4) && (der.getType() == 4)) {
				    res = new TBoolean(null != null);
			    }
			    else {
			    	res = new TBoolean(true);
			    }
			} else if (op.equals("and")) { //AND
				boolean izqB = returnBooleanValue(izq,"AND");
				if(!izqB){
					return new TBoolean (false);
				}
				else{
					boolean derB = returnBooleanValue(der,"AND");
					res = new TBoolean (izqB&&derB);
				}


			} else if (op.equals("or")) { // OR
				boolean izqB = returnBooleanValue(izq,"OR");
				if(izqB){
					return new TBoolean (true);
				}
				else{
					boolean derB = returnBooleanValue(der,"OR");
					res = new TBoolean (izqB||derB);
				}
			}
		}
		return res;
    }

	private boolean returnBooleanValue(Type izq,String op) throws CompilerException{
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
	}
};
