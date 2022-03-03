package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student {
	@Id
	@GeneratedValue
	private int id;
	private String studentName;
	
	
	// constructors
	public Student() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public Student(int id, String studentName) {
		super();
		this.id = id;
		this.studentName = studentName;
	}

	public Student(String studentName) {
		super();
		this.studentName = studentName;
	}

	// getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	// methods
	@Override
	public String toString() {
		return "Student [id=" + id + ", studentName=" + studentName + "]";
	}
}
