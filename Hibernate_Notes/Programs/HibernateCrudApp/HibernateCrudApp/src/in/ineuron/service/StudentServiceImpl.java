package in.ineuron.service;

import in.ineuron.dao.IStudentDao;
import in.ineuron.daofactory.StudentDaoFactory;
import in.ineuron.dto.Student;

//service layer logic
public class StudentServiceImpl implements IStudentService {

	private IStudentDao stdDao;

	@Override
	public String addStudent(String sname, Integer sage, String saddress) {
		stdDao = StudentDaoFactory.getStudentDao();
		return stdDao.save(sname, sage, saddress);
	}

	@Override
	public Student searchStudent(Integer sid) {
		stdDao = StudentDaoFactory.getStudentDao();
		return stdDao.findById(sid);
	}

	@Override
	public String updateStudent(Student student) {
		stdDao = StudentDaoFactory.getStudentDao();
		return stdDao.updateStudent(student);
	}

	@Override
	public String deleteStudent(Integer sid) {
		stdDao = StudentDaoFactory.getStudentDao();
		return stdDao.delete(sid);
	}

}
