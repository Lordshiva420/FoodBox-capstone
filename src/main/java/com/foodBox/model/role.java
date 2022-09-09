package com.foodBox.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="roles")
public class role {
	@Id
	private long rId;
	private String rollName;
	
	
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy ="role")
	@JsonIgnore
	private Set<UserRole> userRoles= new HashSet<>();
	
	
	
	public role() {
		super();
		
	}

	public role(long rId, String rollName) {
		super();
		this.rId = rId;
		this.rollName = rollName;
	}
	

	public long getrId() {
		return rId;
	}

	public void setrId(long rId) {
		this.rId = rId;
	}

	public String getRollName() {
		return rollName;
	}

	public void setRollName(String rollName) {
		this.rollName = rollName;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	

}
