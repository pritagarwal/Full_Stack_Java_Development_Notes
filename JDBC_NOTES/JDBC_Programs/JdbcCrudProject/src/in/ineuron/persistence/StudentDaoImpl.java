package in.ineuron.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import in.ineuron.util.JdbcUtil;
import in.ineuron.dto.Student;

//Here write persistence logic using JDBC API
public class StudentDaoImpl implements IStudentDao {
	
	private Connection con ;
	private PreparedStatement pstat;
	private ResultSet res;

	@Override
	public String addStudent(String sname, Integer sage, String sadd) {
	 
		String sql = "insert into student(name,age,address) values(?,?,?);";
		try {
			con = JdbcUtil.getConnection();
			if(con!=null)
				pstat = con.prepareStatement(sql);
			if(pstat!=null)
			{
				pstat.setString(1, sname);
				pstat.setInt(2,sage);
				pstat.setNString(3, sadd);
			}
			int rowsAffected = pstat.executeUpdate();
			if(rowsAffected == 1 )
				return "success";
				
				
		}catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		return "failure";
		
	}

	@Override
	public Student searchStudent(Integer sid) {
		
		String sql = "Select id,name,age,address from student where id = ?";
		Student std = null;
		
		try {
			con = JdbcUtil.getConnection();
			
			if(con !=null)
				pstat = con.prepareStatement(sql);
			if(pstat !=null)
			{
				pstat.setInt(1, sid);
			}
			if(pstat !=null) {
				res = pstat.executeQuery();
			}
			if(res != null) {
				//copy the res data to student data
				
				if(res.next()) {
				//when we are sure that data is present then only 
                //create the Student DTO object else for no resultSet data 
			    //also the value will be passed to controller as null and it will 
                //not enter the else case rather print the table as null null ....
					std = new Student(); 
					std.setSid(res.getInt(1));
					std.setSname(res.getString(2));
					std.setSage(res.getInt(3));
					std.setSadd(res.getString(4));
					return std;
					
				}
				
				
			}
			
		}catch(SQLException | IOException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}

        return std;
	}

	@Override
	public String updateStudent(Integer sid, String sname, Integer sage, String sadd) {
		String sql = "update student set name=? ,age=? ,address=? where id=?";
		int rowCount=0;
		try {
			con=JdbcUtil.getConnection();
			if(con!=null)
				pstat = con.prepareStatement(sql);
			if(pstat != null)
			{
				pstat.setString(1, sname);
				pstat.setInt(2, sage);
				pstat.setString(3,sadd);
				pstat.setInt(4, sid);
				rowCount = pstat.executeUpdate();
			}
			if(rowCount == 0)
				return "Failure";
			else
				return "Success";
				
		}catch(SQLException|IOException e) {
			e.printStackTrace();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "failure";
	}

	@Override
	public String deleteStudent(Integer sid) {
		String sql = "Delete from student where id = ?";
		int rowCount=0;
		
		try {
			con = JdbcUtil.getConnection();
			
			if(con !=null)
				pstat = con.prepareStatement(sql);
			if(pstat !=null)
			{
				pstat.setInt(1, sid);
			}
			if(pstat !=null) {
			  rowCount = pstat.executeUpdate();
			}
		
			if(rowCount > 0)
				 return "success";
			else
				return "not found";
				
			
			
		}catch(SQLException | IOException e) {
			e.printStackTrace();
			
		}catch(Exception e) {
			e.printStackTrace();
		
		}

       	
		return "failed";
	}

}
