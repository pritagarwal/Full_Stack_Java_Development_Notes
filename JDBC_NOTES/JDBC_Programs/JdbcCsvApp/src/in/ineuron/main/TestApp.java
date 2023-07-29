package in.ineuron.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;


public class TestApp {
	private static String sql = "select * from data.csv";

	public static void main(String[] args) {
		try {
			String url = "jdbc:Text:///D:\\Notes\\Programs\\JDBCSourceCode_20_01_2023";
			Connection con = DriverManager.getConnection(url);
			PreparedStatement pstat = con.prepareStatement(sql);
			ResultSet res = pstat.executeQuery();
			if(res!=null)
			{
				while(res.next())
				{
					System.out.println(res.getInt(1)+""+res.getNString(2)+""+res.getString(3));
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
