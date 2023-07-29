package in.ineuron.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Properties;
import java.sql.SQLException;
import java.sql.Statement;
public class JdbcUtil {
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private JdbcUtil() {}
	
	public static Connection getConnection() throws SQLException, IOException {
		
		Connection con = null;
		
		FileInputStream fis = new FileInputStream("src\\in\\ineuron\\properties\\application.properties");
		Properties pos = new Properties();
		pos.load(fis);
		String url =pos.getProperty("url");
		String user = pos.getProperty("user");
		String password = pos.getProperty("password");
		con = DriverManager.getConnection(url,user,password);
		return con;
	}
	
	public static void cleanup(Connection con,Statement stat,ResultSet res) throws SQLException{
		
		if(con!=null)
			con.close();
		if(stat!=null)
			stat.close();
		if(res!=null)
			res.close();
		
		
	}

}
