package in.ineuron.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import in.ineuron.util.*;
import org.apache.commons.io.IOUtils;

public class BlobRetrevingApp {

	public static void main(String[] args) {
		
		//Resource used
		Connection con = null;
		PreparedStatement pstat = null;
		ResultSet res = null;
		Scanner sc=null;
		InputStream is=null;
		FileOutputStream fos = null;
		
		File f = new File("D:\\JAVA\\FileHandling\\copy.jpg");
	
		
		
		//variables
		int id =0;
		String sql=null;
		int sid=0;
		String sname=null;
		
		try {
			
			con = JdbcUtil.getConnection();
			fos = new FileOutputStream(f);
			

			sql = "select id,name,image from person where id=?";
			
			if(con!=null)
				pstat = con.prepareStatement(sql);
			
			if(pstat!=null)
			{
				sc = new Scanner(System.in);
				System.out.print("Enter id whose image want to see :: ");
				id = sc.nextInt();
				
				pstat.setInt(1, id);
				res = pstat.executeQuery();
				
			}
			if(res!=null)
			{
				if(res.next())
				{
					 sid = res.getInt(1);
					 sname = res.getNString(2);
					is = res.getBinaryStream(3);
				}
			}
			System.out.println("ID\tName\tIMG");
//			int i = is.read();
//			while(i!=-1) {
//				fos.write(i);
//				i=is.read();
//			}
			
//			byte[] b = new byte[1024];
//			int i = is.read(b);
//			while(i>0) {
//				fos.write(b);
//				i = is.read(b);
//			}
			
			IOUtils.copy(is,fos);

			is.close();
			fos.flush();
			fos.close();
			System.out.println(sid+"\t"+sname+"\t"+f.getAbsolutePath());
			
		}catch(SQLException | IOException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				JdbcUtil.cleanup(con, pstat, res);
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			
		}

	}

}
