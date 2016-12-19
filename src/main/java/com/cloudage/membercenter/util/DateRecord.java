package com.cloudage.membercenter.util;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public class DateRecord extends BaseEntity{

	Date createDate;
	Date editDate;
	
	
	
	@Column(updatable=false)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getEditDate() {
		return editDate;
	}
	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}
	
	@PreUpdate
	void onPreUpdate(){
		editDate=new Date();
	}
	
	@PrePersist
	void onPrePersist(){
		createDate=new Date();
		editDate = new Date();
	}
}
