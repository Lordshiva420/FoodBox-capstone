package com.foodBox.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity

public class UserRole {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long userRoleId;
    
    //user
    @ManyToOne(fetch=FetchType.EAGER)
    private user user;
    
    //role
    @ManyToOne
    private role role;
    
    

	public UserRole() {
		super();
		
	}
	

	
	public long getUserRoleId() {
		return userRoleId;
	}



	public user getUser() {
		return user;
	}

	public void setUser(user user) {
		this.user = user;
	}	
	
	public role getRole() {
		return role;
	}

	public void setRole(role role) {
		this.role = role;
	}


}
