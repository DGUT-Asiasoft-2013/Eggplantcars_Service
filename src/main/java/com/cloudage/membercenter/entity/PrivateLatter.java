package com.cloudage.membercenter.entity;


import java.util.Date;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;


import com.cloudage.membercenter.util.BaseEntity;
import com.mchange.v2.c3p0.test.OneThreadRepeatedInsertOrQueryTest;

@Entity
public class PrivateLatter extends BaseEntity{

	User sender; // 私信发起者
	User receiver; // 私信接受者
	String sendtype; //消息类型 send 或者 receive
	String latterText; // 私信内容
	Date createDate; // 发送时间
	boolean unread; //判断是否已读

	
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

	public String getSendtype() {
		return sendtype;
	}

	public void setSendtype(String sendtype) {
		this.sendtype = sendtype;
	}

	@Column(nullable = false)
	public String getLatterText() {
		return latterText;
	}

	public void setLatterText(String latterText) {
		this.latterText = latterText;
	}

	@Column(updatable = false)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	
	public boolean isUnread() {
		return unread;
	}

	public void setUnread(boolean unread) {
		this.unread = unread;
	}

	@PrePersist
	void onPreRersist() {
		createDate = new Date();
		unread = true;
	}

}
