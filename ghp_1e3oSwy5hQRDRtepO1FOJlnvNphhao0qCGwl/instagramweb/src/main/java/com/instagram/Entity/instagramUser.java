package com.instagram.Entity;

import javax.persistence.*;



@Entity
@Table(name="BasicDetails")
public class instagramUser {

	@Id
	private String email;
	private String fullname;
	private String username;
	private String password;
	//private String userimg;
	private String gender;
	private String mobile;
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
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
	
	
//	public String getUserimg() {
//		return userimg;
//	}
//	public void setUserimg(String userimg) {
//		this.userimg = userimg;
//	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
}
