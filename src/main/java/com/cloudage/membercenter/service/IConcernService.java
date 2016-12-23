package com.cloudage.membercenter.service;

import java.util.List;

import com.cloudage.membercenter.entity.Concern;
import com.cloudage.membercenter.entity.User;

public interface IConcernService {

	void addConcern(User user, User news_author);

	void removeConcern(User user,User news_author);

	boolean checkConcerned(int userId, int newsauthorId);

	List<Concern> getConcernsByUserId(int userId);
}
