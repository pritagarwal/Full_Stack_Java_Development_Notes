package in.ineuron.main;
import in.ineuron.util.JdbcUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.util.Scanner;

public class BatchUpdateUsingPStatement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstat = null;
		Scanner sc = null;
		ResultSet res = null;
		
		//String sql = "insert into student(name,age) values(?,?);";
		
		String sql ="Select id,name,age from student where id = ? ";
		
		String sname = null;
		int sage = 0;
		int sid=0;
		
		try {
			con = JdbcUtil.getConnection();
			
			if(con!=null)
				pstat = con.prepareStatement(sql);
			
			sc = new Scanner(System.in);
			
			if(sc != null)
			{
				while(true) {
//				System.out.print("Enter the name :: ");
//				sname = sc.next();
//				System.out.print("Enter the age :: ");
//				sage = sc.nextInt();
//				
//				pstat.setString(1, sname);
//				pstat.setInt(2,sage);
//				
//				pstat.addBatch();

					System.out.print("Enter the id to fetch Details :: ");
					sid = sc.nextInt();
					pstat.setInt(1, sid);
					
					pstat.addBatch();
					
					System.out.print("Y/N");
					String op = sc.next();
					if(op.equalsIgnoreCase("N"))
						break;
					
				
			}
			}
			  int[] arr = pstat.executeBatch();
			  for(int x:arr) {
				  System.out.print(x +" ");
			  }
			System.out.println("Data inserted Successfully");
			
			sc.close();
			
		}catch(SQLException |IOException e) {
			e.printStackTrace();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				JdbcUtil.cleanup(con, pstat, null);
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
