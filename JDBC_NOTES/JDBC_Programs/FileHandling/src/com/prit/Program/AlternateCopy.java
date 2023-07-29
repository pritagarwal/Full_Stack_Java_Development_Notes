package com.prit.Program;

import java.io.*;

public class AlternateCopy {

	public static void main(String[] args) throws IOException {
		
		FileWriter fw = new FileWriter("D:\\JAVA\\FileHandling\\File3.TXT");
		PrintWriter pw = new PrintWriter(fw);
		
		FileReader fr1 = new FileReader("D:\\JAVA\\FileHandling\\File1.TXT");
		BufferedReader r1 = new BufferedReader(fr1);
		
		FileReader fr2 = new FileReader("D:\\JAVA\\FileHandling\\File2.TXT");
		BufferedReader r2 = new BufferedReader(fr2);
		
		String s1 = r1.readLine();
		String s2 = r2.readLine();
		int f1=1,f2=0;
		
		while(s1!=null || s2!=null)
		{
			if(s1!=null && f1==1)
			{
				System.out.println(s1);
				pw.println(s1);
				s1=r1.readLine();
				f1=0;
				f2=1;
				
				
			}
			
			else if(s2!=null && f2==1)
			{
				System.out.println(s2);
				pw.println(s2);
				s2=r2.readLine();
				f2=0;
				f1=1;
				
			}
			else
			{
				break;
			}
		
		}
		
	    while(s1!=null) {
	    	System.out.println(s1);
	    	pw.println(s1);
	    	s1 = r1.readLine();
	    	
	    }
	    while(s2!=null)	{
	    	System.out.println(s2);
	    	pw.println(s2);
	    	s2 = r2.readLine();
	    
	    }
		
		pw.flush();
		pw.close();
		r1.close();
		r2.close();



	}

}
