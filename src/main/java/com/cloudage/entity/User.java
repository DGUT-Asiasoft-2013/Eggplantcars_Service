package com.cloudage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import com.cloudage.util.BaseEntity;

@Entity
public class User extends BaseEntity{
	String account;
	String passwordHash;
	String name;
	String avatar;

	@Column(unique=true)
	public String getAccount() {
		return account;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public String getName() {
		return name;
	}
	public String getAvatar() {
		return avatar;
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
