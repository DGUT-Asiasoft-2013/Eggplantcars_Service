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
	
	String buyerName;
	String buyerAddress;
	String buyerPhone;
	String orderTitle;
	String orderCarModel;
	String orderBuyDate;
	String orderTravelDistance;
	Integer orderPrice;
	String orderText;
	String orderAvatar;
	
	@ManyToOne(optional = false)
	@JsonIgnore
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
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getBuyerAddress() {
		return buyerAddress;
	}
	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}
	public String getBuyerPhone() {
		return buyerPhone;
	}
	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}


	public String getOrderTitle() {
		return orderTitle;
	}
	public void setOrderTitle(String orderTitle) {
		this.orderTitle = orderTitle;
	}
	public String getOrderCarModel() {
		return orderCarModel;
	}
	public void setOrderCarModel(String orderCarModel) {
		this.orderCarModel = orderCarModel;
	}
	public String getOrderBuyDate() {
		return orderBuyDate;
	}
	public void setOrderBuyDate(String orderBuyDate) {
		this.orderBuyDate = orderBuyDate;
	}
	public String getOrderTravelDistance() {
		return orderTravelDistance;
	}
	public void setOrderTravelDistance(String orderTravelDistance) {
		this.orderTravelDistance = orderTravelDistance;
	}
	public Integer getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(Integer orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getOrderText() {
		return orderText;
	}
	public void setOrderText(String orderText) {
		this.orderText = orderText;
	}
	public String getOrderAvatar() {
		return orderAvatar;
	}
	public void setOrderAvatar(String orderAvatar) {
		this.orderAvatar = orderAvatar;
	}
	@PrePersist
	void onPrePersist() {
		createDate = new Date();
	}
}
