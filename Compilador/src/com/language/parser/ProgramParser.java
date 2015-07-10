package com.language.parser;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.language.exceptions.ParsingException;
import com.language.model.program.*;

;

public class ProgramParser {

	public static void parse(String fileName) {
		System.out.println("\n/*************** " + fileName
				+ " *****************/");
		File file = new File(fileName);
		byte[] expbytes;
		try {
			expbytes = read(file);

			ByteArrayInputStream bais = new ByteArrayInputStream(expbytes);

			Parser parser = new Parser(new Scanner(bais));
			Object result = parser.parse().value;
			((Program)result).execute();

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
}
