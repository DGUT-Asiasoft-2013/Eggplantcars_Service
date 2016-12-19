package com.cloudage.membercenter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.cloudage.membercenter.util.BaseEntity;

@Entity
public class User extends BaseEntity{
	String account;
	String passwordHash;
	String name;
	String email;
	String avatar;

	@Column(unique=true)
	public String getAccount() {
		return account;
	}
	@Column(unique=false)
	public String getPasswordHash() {
		return passwordHash;
	}
	@Column(unique=true)
	public String getName() {
		return name;
	}
	@Column(nullable=true)
	public String getAvatar() {
		return avatar;
	}
	@Column(nullable=false, unique=true)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
