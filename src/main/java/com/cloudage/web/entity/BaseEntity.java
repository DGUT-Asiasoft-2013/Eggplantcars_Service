package com.cloudage.web.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity <T extends Serializable>{
	private T id;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}
}
