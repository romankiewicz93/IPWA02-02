package com.managedBeans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.dao.ICoEmission;
import com.dao.IUser;
import com.daoImp.CoEmissionImp;
import com.daoImp.UserImp;
import com.entity.CoEmission;
import com.entity.User;

@ManagedBean(name="co2E")
@ViewScoped
public class Co2EmMB {
	
	private CoEmission co2 = new CoEmission() ;
	private CoEmission selectedCo2 = new CoEmission() ;
	private List<CoEmission> listCo2 ; 
	ICoEmission co2Dao = new CoEmissionImp() ; 
	IUser userDao = new UserImp() ; 
	
	
	public  Co2EmMB() {
	}

	
	public String add() { 
		
		 ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		 User loggedInUser = (User) externalContext.getSessionMap().get("loggedInUser");

		 if (loggedInUser != null) {
			    System.out.println(loggedInUser.getFirstname());
		        co2.setUser(loggedInUser);
		        co2Dao.save(this.co2); 
		        return "dataset.xhtml?faces-redirect=true";
		    } else {
		        // Handle case when no logged-in user is found
		        return null;
		    }
	}
	public String delete() { 
		co2Dao.deleteCoEmission(this.selectedCo2) ; 
		return "dataset.xhtml?faces-redirect=true" ; 
	}
	
	public String edit() { 
		co2Dao.updateCoEmission(this.selectedCo2) ; 
		return "dataset.xhtml?faces-redirect=true" ; 
	}
	
	public String editDS() { 
		this.selectedCo2.setApprouved(false);
		co2Dao.updateCoEmission(this.selectedCo2) ; 
		return "dataset.xhtml?faces-redirect=true" ; 
	}
	
	 public String approveCo2(CoEmission co2) {
	        co2.setApprouved(true);
	        co2Dao.updateCoEmission(co2);
	        return "dataset.xhtml?msg=data approved&faces-redirect=true";

	 }
	public void initUserDataTable(){
		 ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		 User loggedInUser = (User) externalContext.getSessionMap().get("loggedInUser");
		listCo2 = co2Dao.getCoEmissionPerUser(loggedInUser) ; 

	}
	
	public void initDataTable() {
		listCo2 = co2Dao.getAllCoEmission() ; 
	}
	
	public void initForm() {
		long id;		
		id = Integer.parseInt(FacesContext.getCurrentInstance()
						.getExternalContext().getRequestParameterMap()
						.get("id"));

		CoEmission co2E =new CoEmission();
		co2E= co2Dao.getCoEmission(id);
		
		if (co2E!=null)
			this.selectedCo2 = co2E;
			
	}

	public CoEmission getCo2() {
		return co2;
	}

	public void setCo2(CoEmission co2) {
		this.co2 = co2;
	}

	public CoEmission getSelectedCo2() {
		return selectedCo2;
	}

	public void setSelectedCo2(CoEmission slectedCo2) {
		this.selectedCo2 = slectedCo2;
	}

	public List<CoEmission> getListCo2() {
		return listCo2;
	}

	public void setListCo2(List<CoEmission> listCo2) {
		this.listCo2 = listCo2;
	}
	

}
