package com.prit.Program;

import java.io.*;


//To copy all content from File1 and File2 to File3

public class CopyFileContent {

	public static void main(String[] args)throws IOException {
		
		FileReader read = new FileReader("D:\\JAVA\\FileHandling\\File1.TXT");
		BufferedReader br = new BufferedReader(read);
		
		FileWriter fw =  new FileWriter("D:\\JAVA\\FileHandling\\File3.TXT");
		PrintWriter pw = new PrintWriter(fw);
		String str = br.readLine();
		while(str!=null) {
			pw.println(str);
			str = br.readLine();
		}
		
		pw.flush();
		pw.close();
		br.close();
		
		read = new FileReader("D:\\JAVA\\FileHandling\\File2.TXT");
		br = new BufferedReader(read);
		
		fw = new FileWriter("D:\\JAVA\\FileHandling\\File3.TXT",true);
		pw = new PrintWriter(fw);
		str = br.readLine();
		while(str!=null) {
			pw.println(str);
			str = br.readLine();
		}
		pw.flush();
		pw.close();
		br.close();
		
		
		
	

	}

}
