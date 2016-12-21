package com.cloudage.membercenter.service;

import com.cloudage.membercenter.entity.News;
import com.cloudage.membercenter.entity.User;

public interface IConcernService {

	void addConcern(User user, User news_author);

	void removeConcern(User user,User news_author);

	boolean checkConcerned(int userId, int newsauthorId);

	int countConcerns(int news_author_id);
}
