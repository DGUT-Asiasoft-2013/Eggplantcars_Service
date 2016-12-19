package com.cloudage.membercenter.service;

import com.cloudage.membercenter.entity.Article;
import com.cloudage.membercenter.entity.User;

public interface ILikesService {

	void addLike(User user, Article article);

	void removeLike(User user, Article article);

	int countLikes(int articleId);
	
	boolean checkLiked(int userId, int articleId);
}
