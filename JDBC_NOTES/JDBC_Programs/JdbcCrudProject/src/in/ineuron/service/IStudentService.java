package in.ineuron.service;

import in.ineuron.dto.Student;

public interface IStudentService {
	public String addStudent(String sname,Integer sage,String sadd);
	public Student searchStudent(Integer sid);
	public String updateStudent(Integer sid,String sname,Integer sage,String sadd);
	public String deleteStudent(Integer sid);
}
