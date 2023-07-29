package in.ineuron.main;
import in.ineuron.util.JdbcUtil;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.util.Scanner;

public class TransactionApp {

	public static void main(String[] args) {
		
		Connection con = null;
	    Statement stat = null;
		Scanner sc = null;
		ResultSet res1 = null;
		ResultSet res2 = null;
		
		
		String sql = "select name , amt from account";
	
		
		try {	
			System.out.println("Data before Transaction");
			
			con = JdbcUtil.getConnection();
			if(con!=null)
				stat = con.createStatement();
			if(stat!=null)
				res1 = stat.executeQuery(sql);
			
			if(res1!=null)
				{
					System.out.println("Name\tAmount");
					while(res1.next())
					{
						System.out.println(res1.getString(1)+"\t"+res1.getInt(2));
					}
					
				}
			//Transaction Begins
			con.setAutoCommit(false);
			
			//Prepare the operation as single unit
			System.out.println("Transcation Begins.....");
			stat.executeUpdate("update account set amt=amt-3000 where name='Prince'");
			stat.executeUpdate("update account set amt=amt+3000 where name='Prit'");
			
			System.out.println("Please Confirm the Transcation [Y/N]");
			sc = new Scanner(System.in);
			String op = sc.next();
			if(op.equalsIgnoreCase("Y"))
			{
				con.commit();
				System.out.println("Transcation Successfull ....");
			}
				
			else{
				con.rollback();
				System.out.println("Transcation Retreived...");
			}
				
			
			System.out.println("Data after Transaction ....");
			System.out.println("Name\tAmount");
			res2 = stat.executeQuery(sql);
			if(res2!=null)
			{
				while(res2.next())
				{
					System.out.println(res2.getNString(1)+"\t"+res2.getInt(2));
				}
			}
			
			
			
			
			
		}catch(SQLException |IOException e) {
			e.printStackTrace();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				JdbcUtil.cleanup(con, stat, res1);
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
