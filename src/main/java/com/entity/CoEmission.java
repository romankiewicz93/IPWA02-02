package com.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "co2_emissions")  
public class CoEmission implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id ; 
	
	@Basic(optional = false)
	@Column(length = 70)
	private String country_name ; 
	
	@Basic(optional = false)
	@Column(length = 10)
	private String year ; 
	
	@Basic(optional = false)
	@Column(length = 90)
	private double emission_kt ; 
	
	@Basic(optional = false) 
	private boolean approuved ; 
	
	@ManyToOne
	private User user  ;  
	
	
	public CoEmission() {
		super();
	}
	
	
	public CoEmission(String country_name, String year, double emission_kt) {
		super();
		this.country_name = country_name ; 
		this.year = year ; 
		this.emission_kt = emission_kt ; 

	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}


	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}
	
	

	public boolean isApprouved() {
		return approuved;
	}


	public void setApprouved(boolean approuved) {
		this.approuved = approuved;
	}
	
	public void setYear(String year) {
		this.year = year;
	}


	public String getYear() {
		return year;
	}

	public double getEmission_kt() {
		return emission_kt;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setEmission_kt(double emission_kt) {
		this.emission_kt = emission_kt;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
