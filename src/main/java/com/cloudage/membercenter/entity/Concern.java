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
public class Concern {

	@Embeddable
	public static class Key implements Serializable {
		User user;
		User news_auhor;

		@ManyToOne(optional = false)
		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}


		@ManyToOne(optional = false)
		public User getNews_auhor() {
			return news_auhor;
		}

		public void setNews_auhor(User news_auhor) {
			this.news_auhor = news_auhor;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Key) {
				Key other = (Key) obj;
				return news_auhor.getId() == other.news_auhor.getId() && user.getId() == other.user.getId();
			} else {
				return false;
			}
		}

		@Override
		public int hashCode() {
			return news_auhor.getId();
		}
	}

	Key idKey;
	Date createDate;

	@EmbeddedId
	public Key getIdKey() {
		return idKey;
	}

	public void setIdKey(Key idKey) {
		this.idKey = idKey;
	}

	@Column(updatable = false)
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
