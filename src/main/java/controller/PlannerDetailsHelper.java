package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.PlannerDetails;

public class PlannerDetailsHelper {
	
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("WebBasedPlannerApp");
	
	public void insertNewPlannerDetails(PlannerDetails p) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<PlannerDetails> getPlanners() {
		EntityManager em = emfactory.createEntityManager();
		List<PlannerDetails> allDetails = em.createQuery("SELECT d FROM PlannerDetails d").getResultList();
		return allDetails;
	}

	public void deletePlanner(PlannerDetails toDelete) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<PlannerDetails> typedQuery = em.createQuery("select detail from PlannerDetails detail where detail.id = :selectedId", PlannerDetails.class);
		
		typedQuery.setParameter("selectedId", toDelete.getId());
		
		typedQuery.setMaxResults(1);
		
		PlannerDetails result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	public PlannerDetails searchForPlannerDetailsById(Integer tempId) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		PlannerDetails found = em.find(PlannerDetails.class, tempId);
		em.close();
		return found;
	}

	public void updatePlanner(PlannerDetails toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
}
