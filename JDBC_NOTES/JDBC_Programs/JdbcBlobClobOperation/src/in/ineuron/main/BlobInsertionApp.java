package in.ineuron.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

import in.ineuron.util.JdbcUtil;

public class BlobInsertionApp {

	public static void main(String[] args) {
		
		//Recourse used 
		Connection con = null;
		PreparedStatement pstat = null;
		Scanner sc = null;
		
		//variable used
		String name = null;
		String imgLoc=null;
		
		try {
			con = JdbcUtil.getConnection();
			String query = "insert into person(name,image) values(?,?)";
			if(con!=null)
				pstat = con.prepareStatement(query);
			
			if(pstat!=null)
			{
				sc = new Scanner(System.in);
				if(sc!=null) {
					System.out.print("Enter the name :: ");
					name = sc.next();
					System.out.print("\nEnter the location of image ::  ");
					imgLoc = sc.next();
				}

				
				pstat.setString(1,name);
				pstat.setBinaryStream(2, new FileInputStream(new File(imgLoc)));
				
				int rowAffected = pstat.executeUpdate();
				System.out.println("No of rows Aff :: "+rowAffected);
			}
		}catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			sc.close();
			try {
				JdbcUtil.cleanup(con, pstat, null);
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		 

	}

}
