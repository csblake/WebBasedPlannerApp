package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Student;

public class StudentHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("WebBasedPlannerApp");
	
	public void insertStudent(Student s) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(s);
		em.getTransaction().commit();
		em.close();
	}

	public List<Student> showAllStudents() {
		EntityManager em = emfactory.createEntityManager();
		List<Student> allStudents = em.createQuery("SELECT s FROM Student s").getResultList();
		return allStudents;
	}

	public Student findStudent(String nameToLookUp) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Student> typedQuery = em.createQuery("select sh from Student sh where sh.studentName = :selectedName", Student.class);
		
		typedQuery.setParameter("selectedName", nameToLookUp);
		typedQuery.setMaxResults(1);
		
		Student foundStudent;
		
		try {
			foundStudent = typedQuery.getSingleResult();
		} catch (NoResultException ex) {
			foundStudent = new Student(nameToLookUp);
		}
		em.close();
		
		return foundStudent;
	}
}
