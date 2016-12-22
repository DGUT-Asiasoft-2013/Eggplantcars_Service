package com.cloudage.membercenter.service;

import com.cloudage.membercenter.entity.News;
import com.cloudage.membercenter.entity.User;

public interface ILikesService {

	void addLike(User user, News news);

	void removeLike(User user, News news);

	int countLikes(int newsId);
	
	boolean checkLiked(int userId, int newsId);
}
