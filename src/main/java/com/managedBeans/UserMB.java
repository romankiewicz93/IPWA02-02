package com.managedBeans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.dao.IUser;
import com.daoImp.UserImp;
import com.entity.User;

@ManagedBean(name="userMB")
@ViewScoped
public class UserMB {

	private User user = new User() ; 
	private User selectedUser = new User() ; 
	private List<User> listUsers ; 
	IUser userDao = new UserImp() ; 
	
	
	public UserMB() { 
		
	}
	
	
	public String login() {
	    User user = userDao.getUserPerUsername(this.user.getUsername());
	    if (user != null && user.getPassword().equals(this.user.getPassword())) {
	    	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true) ; 
	    	session.setAttribute("loggedInUser", user);
	    	if(this.user.isDataPublisher() && user.getRole().equals("DP")) {
		        return "/DataPublishers/home.xhtml?faces-redirect=true";

	 	   }else if(user.getRole().equals("DS") && !this.user.isDataPublisher()) {
		        return "/DataScientists/home.xhtml?faces-redirect=true";
	 	   }else {
	 		   
		        return "/Users/login.xhtml?errorMsg=Please check your username and password.&faces-redirect=true";
	 	   }
	    	
	    } else {
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid credentials", "Please check your username and password."));
	        return "/Users/login.xhtml?errorMsg=Please check your username and password.&faces-redirect=true";
	    }
	}
	
	public void logout() {
		 FacesContext facesContext = FacesContext.getCurrentInstance();
		    ExternalContext externalContext = facesContext.getExternalContext();
		    HttpSession session = (HttpSession) externalContext.getSession(false);
		    
		    if (session != null) {
		        session.invalidate(); // Destroy the session
		    }
		    
		    try {
		        externalContext.redirect("http://localhost:8080/CO2Project//Users/login.xhtml"); // Redirect to the login page
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
	}

	
	public String add() {
	    // Check if a user with the same username already exists
	    User existingUser = userDao.getUserPerUsername(this.user.getUsername());
	    if (existingUser != null) {
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Username already exists"));
	        return "register.xhtml?error=Username already exists&faces-redirect=true";
	    }
	    // Save the new user
	    User u = userDao.save(this.user);
	    if (u != null) {
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Account created successfully"));
	        return "register.xhtml?success=Account created successfully&faces-redirect=true";
	    }

	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "An error occurred while creating the account"));
	    return null; // Stay on the same page
	}

	
	public String delete() { 
		userDao.deleteUser(this.selectedUser) ; 
		return "add.xhtml?faces-redirect=true" ; 
	}
	
	public String edit() { 
		userDao.updateUser(this.selectedUser) ; 
		return "add.xhtml?faces-redirect=true" ; 
	}
	
	
	
	public void initDataTable() {
		listUsers = userDao.getAllUser() ; 
	}
	
	//initialization of the form 
	public void initForm() {
		int code;		
		code = Integer.parseInt(FacesContext.getCurrentInstance()
						.getExternalContext().getRequestParameterMap()
						.get("id"));

		User usr =new User();
		usr= userDao.getUser(code);
		
		if (usr!=null)
			this.selectedUser = usr;
			
	}
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public User getSelectedUser() {
		return selectedUser;
	}


	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}


	public List<User> getListUsers() {
		return listUsers;
	}


	public void setListUsers(List<User> listUsers) {
		this.listUsers = listUsers;
	}
	
	
	
}
