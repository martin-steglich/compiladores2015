package com.language.model;

import com.language.exceptions.CompilerException;

public class EOperation extends Expression {

	Expression  exp;
	String op;
    int lineNumber;

	public EOperation(Expression eop, String type, int linenum) {
		exp = eop;
		op = type;
		lineNumber = linenum;
	};

	public void printExp(){
		System.out.print("Op : " + op + " " );
		exp.printExp();
		System.out.println();
	}

	public String getType(){
		return "Type EOperation";
	}

	public String Convert (float number){
      return Float.toString(number);
    }

	public Type evaluate() throws CompilerException {

        Type res = null;
		Type e = exp.evaluate();

		if (e == null){
		    throw new CompilerException(lineNumber, "La funcion evaluada no tiene valor de retorno");
		} else {
			boolean Tfloat = (e.getType() == 1);
			boolean Tint = (izq.getType() == 0);

			if (op.equals("-")) { // NEGACION

			    if (Tfloat) {
                    res = new TFloat(0.0 - ((TFloat)e).getValue());
			    }
			    else if (Tint) {
                    res = new TInteger(0 - ((TInteger)der).getValue());
			    }
			    else {
                    throw new CompilerException(lineNumber, "Tipos de datos no esperados para la operacion negación");
				}
			} else if (op.equals("not")) { // NOT
			    boolean eB = returnBooleanValue(e,"OR");
			    if(eB){
                    res = new TBoolean(false);
                }
                else{
                    res = new TBoolean(true);
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
