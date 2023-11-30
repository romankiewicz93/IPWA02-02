package com.daoImp;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.dao.IUser;
import com.entity.User;
import com.util.JPAutil;

public class UserImp implements IUser {
	
	private EntityManager em = JPAutil.getEntityManager("co2_emissions") ; 

	@Override
	public User save(User user) {
		try {
			EntityTransaction tx = em.getTransaction()  ;
			tx.begin();
			em.persist(user);
			tx.commit();
			return user ; 
		}catch(Exception e) {
			System.out.println(e);
			return null ; 
		}
	}

	@Override
	public User getUserPerUsername(String username) {
		User user = null;
		try {
			user = em.createQuery("select u from User u where u.username = :username",User.class ).setParameter("username",username).getSingleResult(); 
			return user ; 
		}catch(Exception e) {
			System.out.println(e);
			return user ; 
		}
	}

	@Override
	public User getUser(long id) {
		try {
			return em.find(User.class, id) ; 
		}catch(Exception e) {
			System.out.println(e);
			return null ; 
		}
	}

	@Override
	public User updateUser(User a) {
		
		try{
			EntityTransaction tx = em.getTransaction()  ;
			tx.begin();
			em.merge(a);
			tx.commit();
			return a; 
		}catch(Exception e) {
			System.out.println(e);
			return null ; 
		}
	}

	@Override
	public void deleteUser(User user) {
		try {
			
			EntityTransaction tx = em.getTransaction();
		 	tx.begin(); 
		 	user = em.merge(user) ; 
		 	em.remove(user);
		 	tx.commit();
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
	}
	
	public List<User> getAllUser() {
		System.out.println("list of All Users");
		List<User> users = em.createQuery("select u from User u").getResultList();
		return users ; 
	}


}
