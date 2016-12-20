package com.cloudage.membercenter.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cloudage.membercenter.entity.Article;
import com.cloudage.membercenter.entity.News;
import com.cloudage.membercenter.entity.User;

public interface INewsRepository extends PagingAndSortingRepository<News, Integer>{

	@Query("from News news where news.author = ?1")
	List<News> findAllByAuthor(User user);
	
	@Query("from News news where news.author.id = ?1")
	List<News> findAllByAuthorId(Integer userId);
}
