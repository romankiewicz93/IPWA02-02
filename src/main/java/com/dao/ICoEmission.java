package com.dao;

import java.util.List;

import com.entity.CoEmission;
import com.entity.User;


public interface ICoEmission {
		
	public CoEmission save(CoEmission co2Emission) ; 
	public CoEmission getCoEmission(long id) ; 
	public List<CoEmission> getAllCoEmission() ; 
	public List<CoEmission> getCoEmissionPerUser(User user) ; 
	public CoEmission getCoEmissionPerCountry(String country) ;
	public CoEmission updateCoEmission(CoEmission c) ; 
	public void deleteCoEmission(CoEmission c) ; 
}
