package com.foodBox.model;

import org.springframework.security.core.GrantedAuthority;
//each role we put in this class make a object and set in get authorities.
public class authority implements GrantedAuthority{
 
	private String authority;
	
	public authority(String authority) {
		super();
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		
		return this.authority;
	}

	@Override
	public String toString() {
		return "authority [authority=" + authority + "]";
	}

}
