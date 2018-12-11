package com.dama.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class PersistenceService {
	
	public User get(Long id) throws Exception {
		EntityManagerFactory emf = EntityManagerFactoryHandler.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		User user = em.find(User.class, id);
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getAll() throws Exception {
		EntityManagerFactory emf = EntityManagerFactoryHandler.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		List<User> list = em.createNamedQuery(User.class.getSimpleName()+".findAll").getResultList();
		return list;
	}
	
	public void save(User user) throws Exception {
		EntityManagerFactory emf = EntityManagerFactoryHandler.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}

}
