package com.cloudage.membercenter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cloudage.membercenter.entity.NewsComment;

public interface INewsCommentRepository extends PagingAndSortingRepository<NewsComment,Integer>{

	@Query("from NewsComment newsComment where newsComment.news.id = ?1")
	Page<NewsComment> findAllOfArticleId(int news_id, Pageable pageRequest);

}
