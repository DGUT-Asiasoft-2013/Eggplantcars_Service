package com.cloudage.web.entity;

import javax.persistence.Entity;

@Entity
public class User extends BaseEntity<Integer>{
	String account;
	String passwordHash;
	String name;
	String avatar;
}
