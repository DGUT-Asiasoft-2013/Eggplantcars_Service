package com.cloudage.membercenter.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import com.cloudage.membercenter.util.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Deal extends BaseEntity {
	User seller;
	Date createDate;
	Date editDate;

	String title;
	String text;
	String getSellerName;
    String dealAvatar;
    String carModel;
    String travelDistance;
    Date buyDate;
    Integer price;
    
    @ManyToOne(optional = false)
	@JsonIgnore
	public User getSeller() {
		return seller;
	}
	public void setSeller(User seller) {
		this.seller = seller;
	}
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Transient
	public String getGetSellerName() {
		return getSellerName;
	}
	public void setGetSellerName(String getSellerName) {
		this.getSellerName = getSellerName;
	}
	public String getDealAvatar() {
		return dealAvatar;
	}
	public void setDealAvatar(String dealAvatar) {
		this.dealAvatar = dealAvatar;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public String getTravelDistance() {
		return travelDistance;
	}
	public void setTravelDistance(String travelDistance) {
		this.travelDistance = travelDistance;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
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
