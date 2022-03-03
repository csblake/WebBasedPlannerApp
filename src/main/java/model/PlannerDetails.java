package model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class PlannerDetails {
	@Id
	@GeneratedValue
	private int id;
	private String plannerName;
	private LocalDate plannerDate;
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Student student;
	@OneToMany(cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
	private List<PlannerItem> listOfPlanners;
	
	// constructors
	public PlannerDetails() {
		// default
	}
	
	public PlannerDetails(int id, String plannerName, LocalDate plannerDate, Student student, List<PlannerItem> listOfPlanners) {
		super();
		this.id = id;
		this.plannerName = plannerName;
		this.plannerDate = plannerDate;
		this.student = student;
		this.listOfPlanners = listOfPlanners;
	}
	
	public PlannerDetails(String plannerName, LocalDate plannerDate, Student student, List<PlannerItem> listOfPlanners) {
		super();
		this.plannerName = plannerName;
		this.plannerDate = plannerDate;
		this.student = student;
		this.listOfPlanners = listOfPlanners;
	}
	
	public PlannerDetails(String plannerName, LocalDate plannerDate, Student student) {
		super();
		this.plannerName = plannerName;
		this.plannerDate = plannerDate;
		this.student = student;
	}

	
	// getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlannerName() {
		return plannerName;
	}

	public void setPlannerName(String plannerName) {
		this.plannerName = plannerName;
	}

	public LocalDate getPlannerDate() {
		return plannerDate;
	}

	public void setPlannerDate(LocalDate plannerDate) {
		this.plannerDate = plannerDate;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<PlannerItem> getListOfPlanners() {
		return listOfPlanners;
	}

	public void setListOfPlanners(List<PlannerItem> listOfPlanners) {
		this.listOfPlanners = listOfPlanners;
	}

	// methods
	@Override
	public String toString() {
		return "PlannerDetails [id=" + id + ", plannerName=" + plannerName + ", plannerDate=" + plannerDate
				+ ", student=" + student + ", listOfPlanners=" + listOfPlanners + "]";
	}
}
