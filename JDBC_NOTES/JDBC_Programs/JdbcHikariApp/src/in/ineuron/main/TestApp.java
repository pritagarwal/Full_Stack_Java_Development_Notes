package in.ineuron.main;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class TestApp {

	public static void main(String[] args)throws Exception {
 
	

		String configFile = "D:\\JAVA\\JdbcHikariApp\\db.properties";
		HikariConfig config = new HikariConfig(configFile);
		try(HikariDataSource ds = new HikariDataSource(config)){
			Connection con = ds.getConnection();
			Statement stat = con.createStatement();
			String sql = "Select * from student";
			ResultSet res = stat.executeQuery(sql);
			
			while(res.next())
			{
				System.out.println(res.getInt(1)+""+res.getString(2));
			}
		}

	}

}
