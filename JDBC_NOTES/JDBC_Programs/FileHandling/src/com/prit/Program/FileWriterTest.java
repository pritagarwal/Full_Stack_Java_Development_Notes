package com.prit.Program;

import java.io.*;

public class FileWriterTest {

	public static void main(String[] args) {
		
		FileWriter fw = null;
		try {
			 fw = new FileWriter("D:\\JAVA\\FileHandling\\File2.TXT");
			 fw.write("Prit");
			 fw.write("Agarwal");
			 System.out.println("Writting Complete");
				fw.flush();
				//fw.close();
		}catch(IOException e) {
			e.printStackTrace();
		}

	}

}
