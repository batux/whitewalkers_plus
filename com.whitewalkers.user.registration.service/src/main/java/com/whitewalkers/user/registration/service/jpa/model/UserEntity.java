package com.whitewalkers.user.registration.service.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_user")
public class UserEntity implements Serializable{

	private static final long serialVersionUID = 371986062744574373L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "surname")
	private String surname;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "authentication_secret_key")
	private String authenticationSecretKey;
	
	@Column(name = "age")
	private Integer age;
	
	@Column(name = "two_factor_authentication")
	private Boolean twoFactorAuthentication;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAuthenticationSecretKey() {
		return authenticationSecretKey;
	}
	public void setAuthenticationSecretKey(String authenticationSecretKey) {
		this.authenticationSecretKey = authenticationSecretKey;
	}
	public Boolean isTwoFactorAuthentication() {
		return twoFactorAuthentication;
	}
	public void setTwoFactorAuthentication(Boolean twoFactorAuthentication) {
		this.twoFactorAuthentication = twoFactorAuthentication;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
