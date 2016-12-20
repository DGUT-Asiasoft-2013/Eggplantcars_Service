package com.cloudage.membercenter.entity;

import java.util.Date;

import javax.persistence.Column;
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
    String buyDate;
    String price;

    
    @ManyToOne(optional = false)
	@JsonIgnore
	public User getSeller() {
		return seller;
	}
	public void setSeller(User seller) {
		this.seller = seller;
	}
	@Transient
	public String getSellerName(){
		return seller.name;
	}
	@Transient
	@JsonIgnore
	public String getSellerAvatar(){
		return seller.avatar;
	}
	@Column(updatable = false)
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
	
	
	public String getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}
	
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
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
