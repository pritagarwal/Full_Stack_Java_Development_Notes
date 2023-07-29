package in.ineuron.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
public class JdbcUtil {
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private JdbcUtil() {}
	
	public static Connection getConnection() 
			throws SQLException, IOException {
		HikariConfig config =  new HikariConfig("D:\\JAVA\\JDBC_Programs\\JdbcCrudProject\\src\\in\\ineuron\\properties\\application.properties");
		HikariDataSource ds = new HikariDataSource(config);
		return ds.getConnection();

	}
	


}
