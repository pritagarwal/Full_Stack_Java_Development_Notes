package in.ineuron.dao;

import in.ineuron.dto.Student;

public interface IStudentDao {
	
	// operations to be implemented
	public String save(String sname, Integer sage, String saddress);

	public Student findById(Integer sid);

	public String updateStudent(Student student);

	public String delete(Integer sid);

}
