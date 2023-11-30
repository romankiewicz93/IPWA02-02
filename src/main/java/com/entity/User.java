package com.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "users")  
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id ; 
	
	@Basic(optional = false)
	@Column(length = 70)
	private String firstname ; 
	
	@Basic(optional = false)
	@Column(length = 70)
	private String lastname ; 
	
	
	@Basic(optional = false)
	@Column(length = 170, unique=true)
	private String username ;
	
	@Basic(optional = false)
	@Column(length = 170)
	private String password ; 
	
	@Basic(optional = false)
	@Column(length = 90)
	private String role ; 
 
	 @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<CoEmission> co2Emissions ;
	 
	 @Transient
	 private boolean isDataPublisher ;
	
	public User() {
		super();
	}
	
	
	public User(String name,  String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role ; 
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	
	public List<CoEmission> getCo2Emissions() {
		return co2Emissions;
	}


	public void setCo2Emissions(List<CoEmission> co2Emissions) {
		this.co2Emissions = co2Emissions;
	}


	public boolean isDataPublisher() {
		return isDataPublisher;
	}


	public void setDataPublisher(boolean isDataPublisher) {
		this.isDataPublisher = isDataPublisher;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}

	
	
	
	
	
}
