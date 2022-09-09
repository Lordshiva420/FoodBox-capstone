package com.foodBox.model;

// incoming username and password is stored here and can be accessed by accessing this object.
public class jwtRequest {
	
	String userName;
	
	String password;

	public jwtRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public jwtRequest(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
