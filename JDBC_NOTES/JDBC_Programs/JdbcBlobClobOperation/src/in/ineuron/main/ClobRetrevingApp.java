package in.ineuron.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.Reader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

import in.ineuron.util.JdbcUtil;

public class ClobRetrevingApp {

	public static void main(String[] args) {


		Connection con=null;
		PreparedStatement pstat = null;
		ResultSet res = null;
		Reader read=null;
		File f=null;
		FileWriter fw = null;
		Scanner sc = null;
		
		int id=0;
		int sid=0;
		String sname=null;
		String sql = "select id,name,resume from std_resume where id = ?";
		
		try {
			con = JdbcUtil.getConnection();
			
			if(con!=null)
			{
				pstat = con.prepareStatement(sql);
			}
			if(pstat != null)
			{
				sc = new Scanner(System.in);
				System.out.print("Enter the id to get resume ::");
				id = sc.nextInt();
				
				pstat.setInt(1, id);
				
				res = pstat.executeQuery();
			}
			
			if(res !=null)
			{
				if(res.next())
				{
					sid = res.getInt(1);
					sname = res.getNString(2);
					read = res.getCharacterStream(3);
					f = new File("D:\\JAVA\\JdbcBlobClobOperation\\copyResume.txt");
					fw = new FileWriter(f);
					IOUtils.copy(read, fw);
				}
				System.out.println("ID\tName\tResume Location");
				System.out.println(sid+"\t"+sname+"\t"+f.getAbsolutePath());
				read.close();
				fw.close();
				sc.close();
			}
		}
		catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				JdbcUtil.cleanup(con, pstat, res);
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		

	}

}
