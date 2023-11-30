package com.daoImp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.dao.ICoEmission;
import com.entity.CoEmission;
import com.entity.User;
import com.util.JPAutil;

public class CoEmissionImp implements ICoEmission{
	
	private EntityManager em = JPAutil.getEntityManager("co2_emissions") ; 
	
	@Override
	public CoEmission save(CoEmission co2Emission) {
		try {
			EntityTransaction tx = em.getTransaction(); 
			tx.begin();
			em.persist(co2Emission);
			tx.commit();
			return co2Emission;
		}catch(Exception e) {
			System.out.println(e);
			return null ; 
		}
	}

	@Override
	public CoEmission getCoEmission(long id) {
		try {
			return em.find(CoEmission.class, id) ; 
		}catch(Exception e) {
			System.out.println(e);
			return null ; 
		}
	}

	@Override
	public List<CoEmission> getAllCoEmission() {
		System.out.println("List of all CO2 emmisions");
		List<CoEmission> co2s = em.createQuery("select c from CoEmission c").getResultList();
		return co2s ; 
	}

	@Override
	public CoEmission getCoEmissionPerCountry(String country_name) {
		CoEmission co2E = null;
		try {
			co2E = em.createQuery("select c from CoEmission c where c.country_name = :country_name",CoEmission.class ).setParameter("country_name",country_name).getSingleResult(); 
			return co2E ; 
		}catch(Exception e) {
			System.out.println(e);
			return co2E ; 
		}
	}
	public List<CoEmission> getCoEmissionPerUser(User user) {
		List<CoEmission> co2E = null;
		try {
			co2E = em.createQuery("select c from CoEmission c where c.user = :user",CoEmission.class ).setParameter("user",user).getResultList(); 
			return co2E ; 
		}catch(Exception e) {
			System.out.println(e);
			return co2E ; 
		}
	}

	@Override
	public CoEmission updateCoEmission(CoEmission c) {

		try{
			EntityTransaction tx = em.getTransaction()  ;
			tx.begin();
			em.merge(c);
			tx.commit();
			return c; 
		}catch(Exception e) {
			System.out.println(e);
			return null ; 
		}
	}

	@Override
	public void deleteCoEmission(CoEmission c) {
		
		try {
			
			EntityTransaction tx = em.getTransaction();
		 	tx.begin(); 
		 	c = em.merge(c) ; 
		 	em.remove(c);
		 	tx.commit();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	

}
