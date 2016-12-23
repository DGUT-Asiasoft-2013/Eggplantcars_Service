package com.cloudage.membercenter.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

@Entity
public class Likes {

	@Embeddable
	public static class Key implements Serializable {
		User user;
		News news;

		@ManyToOne(optional = false,cascade=CascadeType.ALL)
		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		@ManyToOne(optional = false)
		public News getNews() {
			return news;
		}

		public void setNews(News news) {
			this.news = news;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Key) {
				Key other = (Key) obj;
				return news.getId() == other.news.getId() && user.getId() == other.user.getId();
			} else {
				return false;
			}
		}

		@Override
		public int hashCode() {
			return news.getId();
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
