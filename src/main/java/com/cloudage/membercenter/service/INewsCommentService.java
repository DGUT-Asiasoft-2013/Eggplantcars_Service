package com.cloudage.membercenter.service;

import org.springframework.data.domain.Page;

import com.cloudage.membercenter.entity.NewsComment;

public interface INewsCommentService {

	NewsComment save(NewsComment newsComment);

	Page<NewsComment> findNewsCommentsOfNews(int news_id, int page);

	Page<NewsComment> findAllOfMyNewsComment(int author_id, int page);

	Page<NewsComment> findNewsCommentsOfAuthor(int author_id, int page);

}
