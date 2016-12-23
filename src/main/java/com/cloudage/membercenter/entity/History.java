package com.cloudage.membercenter.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.cloudage.membercenter.util.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class History extends BaseEntity{

	User author;
	String text;
	Date createDate;
	Date editDate;
	
	public Date getEditDate() {
		return editDate;
	}
	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@ManyToOne(optional = false)
	@JsonIgnore
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	@Column(unique=true)
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@PreUpdate
	void onPreUpdate() {
		editDate = new Date();
	}

	@PrePersist
	void onPrePersist() {
		createDate = new Date();
		editDate = new Date();
	}
	
}
