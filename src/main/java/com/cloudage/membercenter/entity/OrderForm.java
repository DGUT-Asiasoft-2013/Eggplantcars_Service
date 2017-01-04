package com.cloudage.membercenter.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import com.cloudage.membercenter.util.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrderForm extends BaseEntity{
	Date createDate;
	User buyer;
	Address address;
	User seller;
	Deal deal;
	String type;// 订单的状态：接受，发货，收货


	@ManyToOne(optional = false)
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@ManyToOne(optional = false)
	public User getSeller() {
		return seller;
	}
	public void setSeller(User seller) {
		this.seller = seller;
	}
	@ManyToOne(optional = false)

	public User getBuyer() {
		return buyer;
	}
	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@PrePersist
	void onPrePersist() {
		createDate = new Date();
	}
	@ManyToOne(optional = false)
	public Deal getDeal() {
		return deal;
	}
	public void setDeal(Deal deal) {
		this.deal = deal;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


}
