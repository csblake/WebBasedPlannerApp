import java.util.List;

import controller.StudentHelper;
import model.Student;

public class StudentTester {

	public static void main(String[] args) {
		Student clay = new Student("Clay");
		Student michelle = new Student("Michelle");
		
		StudentHelper sh = new StudentHelper();
		
		sh.insertStudent(clay);
		sh.insertStudent(michelle);
		
		List<Student> allStudents = sh.showAllStudents();
		for(Student c : allStudents) {
			System.out.println(c.toString());
		}
	}
}
