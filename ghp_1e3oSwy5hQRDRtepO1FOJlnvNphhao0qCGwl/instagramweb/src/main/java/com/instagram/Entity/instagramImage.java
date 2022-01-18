package com.instagram.Entity;

import javax.persistence.*;



@Entity
@Table(name="StoreImages")
public class instagramImage {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int imgid;
	public int getImgid() {
		return imgid;
	}
	public void setImgid(int imgid) {
		this.imgid = imgid;
	}
	
	@Column(unique=true)
	private String email;

	
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
