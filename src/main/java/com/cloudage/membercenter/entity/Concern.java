package com.cloudage.membercenter.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;


@Entity
public class Concern {

	@Embeddable
	public static class Key implements Serializable {
		User user;
		User news_author;

		@OneToOne(optional = false)
		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}


		@OneToOne(optional = false)
		public User getNews_author() {
			return news_author;
		}

		public void setNews_author(User news_author) {
			this.news_author = news_author;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Key) {
				Key other = (Key) obj;
				return news_author.getId() == other.news_author.getId() && user.getId() == other.user.getId();
			} else {
				return false;
			}
		}

		@Override
		public int hashCode() {
			return news_author.getId();
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
