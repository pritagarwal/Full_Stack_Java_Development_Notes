package in.ineuron.servicefactory;

import in.ineuron.service.IStudentService;
import in.ineuron.service.StudentServiceImpl;
public class StudentServiceFactory {
	
	private static IStudentService studentService = null;
	

	private StudentServiceFactory() {}
	
	//singleton pattern design
	public static IStudentService getStudentService() {
		if(studentService == null) {
			studentService = new StudentServiceImpl();
		}
		return studentService;
	}

}
