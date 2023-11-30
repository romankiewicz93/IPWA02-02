package com.test;

import java.util.List;

import com.dao.IUser;
import com.daoImp.UserImp;
import com.entity.User;

public class Test {

	public static void main(String[] args) {
		
		IUser userDao = new UserImp() ; 
		List<User> users = userDao.getAllUser() ; 
		
		for (User u : users) {
			System.out.println(u.getId());
		}


	}
}