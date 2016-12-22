package com.cloudage.membercenter.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

@Entity
public class PrivateLatter {

	
	@Embeddable
	public static class Key implements Serializable{
		User sender; //私信发起者
		User receiver; //私信接受者
		
		@ManyToOne(optional=false)
		public User getSender() {
			return sender;
		}
		public void setSender(User sender) {
			this.sender = sender;
		}
		@ManyToOne(optional=false)
		public User getReceiver() {
			return receiver;
		}
		public void setReceiver(User receiver) {
			this.receiver = receiver;
		}
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Key) {
				Key other = (Key) obj;
				return sender.getId() == other.sender.getId() && receiver.getId() == other.receiver.getId();
			} else {
				return false;
			}
		}
		
	}
	
	Key idKey;
	String latterText;  //私信内容
	Date createDate;	//发送时间
	@EmbeddedId
	public Key getIdKey() {
		return idKey;
	}
	public void setIdKey(Key idKey) {
		this.idKey = idKey;
	}
	@Column(nullable=false)
	public String getLatterText() {
		return latterText;
	}
	public void setLatterText(String latterText) {
		this.latterText = latterText;
	}
	@Column(updatable=false)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@PrePersist
	void onPreRersist(){
		createDate = new Date();
	}
	
	
}
