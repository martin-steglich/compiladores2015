package com.language.parser;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import com.language.model.program.Program;
import com.language.model.stack.StackHandler;

;

public class ProgramParser {

	public static void parse(String fileName) {
		System.out.println("\n/*************** " + fileName
				+ " *****************/");
		//readAux(fileName);
		File file = new File(fileName);
		byte[] expbytes;
		try {
			expbytes = read(file);

			ByteArrayInputStream bais = new ByteArrayInputStream(expbytes);
			
			Parser parser = new Parser(new Scanner(bais));
			Object result = parser.parse().value;
			
			StackHandler stackHandler = StackHandler.getInstance();
			stackHandler.reset();
			stackHandler.getStack().openScope();
			
			((Program)result).execute();
			
			stackHandler.getStack().closeScope();

		} catch (Throwable ex) {
			System.out.println(ex.getMessage());
		}
		
		System.out.println("/*************** "+ fileName +" *****************/");

	}

	public static byte[] read(File file) throws IOException {

		ByteArrayOutputStream ous = null;
		InputStream ios = null;
		try {
			
			byte[] buffer = new byte[4096];
			ous = new ByteArrayOutputStream();
			ios = new FileInputStream(file);
			int read = 0;
			while ((read = ios.read(buffer)) != -1) {
				ous.write(buffer, 0, read);
				
			}
			String s = "\n";
			ous.write(s.getBytes());
		} finally {
			try {
				if (ous != null)
					ous.close();
			} catch (IOException e) {
			}

			try {
				if (ios != null)
					ios.close();
			} catch (IOException e) {
			}
		}
		return ous.toByteArray();
	}
	
	public static void readAux(String fileName){
		
	}
}
