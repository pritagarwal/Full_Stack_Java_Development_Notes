package in.ineuron.main;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.io.IOException;

import in.ineuron.util.JdbcUtil;

public class ClobInsertionApp {

	public static void main(String[] args) {
		
		Connection con=null;
		PreparedStatement pstat = null;
		Scanner sc =null;
		File f = null;
		FileReader fr = null;
		
		
		String sql = "insert into std_resume(name,resume) values(?,?);";
		String sname = null;
		String loc = null;
		
		try {
			con = JdbcUtil.getConnection();
			
			if(con!=null) {
				pstat = con.prepareStatement(sql);
			}
			if(pstat!=null)
			{
				sc  = new Scanner(System.in);
				System.out.print("Enter the name ::");
				sname = sc.next();
				System.out.print("\nEnter the loc of resume :: ");
				loc = sc.next();
				f = new File(loc);
				fr = new FileReader(f);
				
				
				pstat.setNString(1, sname);
				pstat.setCharacterStream(2,fr);
				
				int getCount = pstat.executeUpdate();
				System.out.println("No of rows aff :: "+getCount);
				fr.close();
				sc.close();
				
			}
			
			
		}catch(SQLException | IOException e ) {
			e.printStackTrace();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				JdbcUtil.cleanup(con, pstat, null);
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		



	}

}
