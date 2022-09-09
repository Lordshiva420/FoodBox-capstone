package com.foodBox.model;
// outgoing token is sent in the form of object by using this class.
public class jwtResponse {
	String token;

	public jwtResponse() {
		super();
		
	}

	public jwtResponse(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}
