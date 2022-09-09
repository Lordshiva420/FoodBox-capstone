package com.foodBox.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="users")
public class user implements UserDetails{
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long uId;
	

	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String profile;
	private boolean enable=true;
	
//	one user can have many roles
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER,mappedBy ="user")
	@JsonIgnore
	private Set<UserRole> userRoles= new HashSet<>();
	

	public user() {
		
	}
	
	public user(long uId, String userName, String password, String firstName, String lastName, String email,
			String phone, String profile, boolean enable) {
		super();
		this.uId = uId;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.profile = profile;
		this.enable = enable;
	}

	public long getuId() {
		return uId;
	}

	
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}



	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	
//get the roles of this user instance put them in set and return the authorities
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		
		Set<authority> set =new HashSet<>();
		
		this.userRoles.forEach(userRole->{
			set.add(new authority(userRole.getRole().getRollName()));
		});
		
		return set;
	}

	

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public boolean isAccountNonExpired() {
	
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.enable;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.userName;
	}

	@Override
	public String toString() {
		return "user [uId=" + uId + ", userName=" + userName + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", phone=" + phone + ", profile=" + profile
				+ ", enable=" + enable + ", userRoles=" + userRoles + "]";
	}

	
	
}
