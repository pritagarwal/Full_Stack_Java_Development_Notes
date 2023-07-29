package in.ineuron.contoller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import in.ineuron.dto.Student;
import in.ineuron.service.IStudentService;
import in.ineuron.servicefactory.StudentServiceFactory;
import java.util.*;

public class TestApp {


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {

			System.out.println("1. CREATE");
			System.out.println("2. READ");
			System.out.println("3. UPDATE");
			System.out.println("4. DELETE");
			System.out.println("5. EXIT");
			System.out.print("ENTER UR CHOICE, PRESS[1/2/3/4/5]::  ");
			String option = br.readLine();

			switch (option) {
			case "1":
				insertOperation();
				break;
			case "2":
				selectOperation();
				break;
			case "3":
				updateOperation();
				break;
			case "4":
				deleteOperation();
				break;
			case "5":
				System.out.println("******* Thanks for using the application *****");
				System.exit(0);
			default:
				System.out.println("Invalid option plz try agin with valid options....");
				break;
			}

		}

	}

	private static void updateOperation() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the id for updation ::");
		int sid = sc.nextInt();
		IStudentService student = StudentServiceFactory.getStudentService();
		Student std = student.searchStudent(sid);
		if(std !=null)
		{
			String name=null;
			Integer age = null;
			String address=null;
			
			System.out.print("Old  name :: "+std.getSname()+" Press Y/N to update name:: ");
			if(sc.next().equalsIgnoreCase("Y"))
			{
				System.out.print("Enter the new name :: ");
				name = sc.next();
			}
			else
			name = std.getSname();	
			
			System.out.print("Old age :: "+std.getSage()+" Press Y/N to update age :: ");
			if(sc.next().equalsIgnoreCase("Y"))
			{
				System.out.print("Enter the new age :: ");
				age = sc.nextInt();
			}
			else
			age = std.getSage();
		
			System.out.print("Old address :: "+std.getSadd()+" Press Y/N to update address :: ");
			if(sc.next().equalsIgnoreCase("Y"))
			{
				System.out.print("Enter the new Address :: ");
				address = sc.next();
			}
			else
			address = std.getSadd();
			
			String msg = student.updateStudent(sid,name, age, address);
			if(msg.equalsIgnoreCase("Success"))
				System.out.println("Records updated Successfully.."); 
			else
				System.out.println("Records Failed to Update...");
		}
		else
		{
			System.out.println("Records not found....");
		}
		
	}

	private static void deleteOperation() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the id to delete :: ");
		int sid = sc.nextInt();
		IStudentService studentService = StudentServiceFactory.getStudentService();
		String msg = studentService.deleteStudent(sid);
		if(msg.equalsIgnoreCase("success"))
			 System.out.println("Record Deleted Successfully...");
		else if(msg.equalsIgnoreCase("not found"))
			System.out.println("Record not found for id :: "+sid);
		else
			System.out.println("Record Deletion Failed");
		
	}
	
	private static void selectOperation() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the id to search :: ");
		int sid = sc.nextInt();
		
		IStudentService studentService = StudentServiceFactory.getStudentService();
		Student std = studentService.searchStudent(sid);
		
		if(std!=null)
		{
			System.out.println(std);
			System.out.println("ID\tName\tAge\tAddress");
			System.out.println(std.getSid()+"\t"+std.getSname()+"\t"+std.getSage()+"\t"+std.getSadd());
		}
		else
		{
			System.out.println("Records not found for ID :: "+sid);
		}
		
		
		
	}
	
	private static void insertOperation() {
		IStudentService studentService = null;
		
		studentService = StudentServiceFactory.getStudentService();
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the name :: ");
		String sname = sc.next();
		
		System.out.print("Enter the age :: ");
		int sage = sc.nextInt();
		
		System.out.print("Enter the address :: ");
		String sadd = sc.next();
		
		String msg = studentService.addStudent(sname,sage,sadd);
		
		if(msg.equalsIgnoreCase("success"))
			System.out.println("Data added Successfully..");
		else
			System.out.println("Data failed to add");
		
		
	}

}
