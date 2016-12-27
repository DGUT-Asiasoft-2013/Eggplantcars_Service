package com.cloudage.membercenter.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.cloudage.membercenter.entity.Likes.Key;

@Entity
public class ShoppingCar{
	
	@Embeddable
	public static class Key implements Serializable {
		Deal deal;
		User buyer;
		
		@ManyToOne(optional = false)
		public Deal getDeal() {
			return deal;
		}
		public void setDeal(Deal deal) {
			this.deal = deal;
		}
		@ManyToOne(optional = false,cascade=CascadeType.ALL)
		public User getBuyer() {
			return buyer;
		}
		public void setBuyer(User buyer) {
			this.buyer = buyer;
		}
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Key) {
				Key other = (Key) obj;
				return deal.getId() == other.deal.getId() && buyer.getId() == other.buyer.getId();
			} else {
				return false;
			}
		}

		@Override
		public int hashCode() {
			return deal.getId();
		}
	}
	
	Key id;
	Date createDate;
	@EmbeddedId
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
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
}
