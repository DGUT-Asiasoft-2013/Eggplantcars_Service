package com.cloudage.membercenter.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import com.cloudage.membercenter.util.BaseEntity;

@Entity
public class Money extends BaseEntity{
	User user;
	int cash;
	
	@ManyToOne(optional= false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public int getCash() {
		return cash;
	}
	public void setCash(int cash) {
		this.cash = cash;
	}
	
}
