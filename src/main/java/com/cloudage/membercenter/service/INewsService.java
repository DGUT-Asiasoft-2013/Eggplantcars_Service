package com.cloudage.membercenter.service;

import java.util.List;

import org.springframework.data.domain.Page;


import com.cloudage.membercenter.entity.News;
import com.cloudage.membercenter.entity.User;

public interface INewsService {

	List<News> findAllByAuthor(User user);
	List<News> findAllByAuthorId(Integer userId);
	News findOne(int newsId);
	News save(News news);
	Page<News> getNews(int page);
}
