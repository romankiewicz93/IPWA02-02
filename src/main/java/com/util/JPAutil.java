package com.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAutil {
	
	private static EntityManagerFactory emf ; 
	private static EntityManager em ; 
	
	
	public static EntityManager getEntityManager(String presistUnit) {
		if(em==null) {
			emf = Persistence.createEntityManagerFactory(presistUnit) ; 
			em = emf.createEntityManager() ; 
		}
		
		return em ; 
	}
	

}
