package com.cloudage.membercenter.service;

import org.springframework.data.domain.Page;

import com.cloudage.membercenter.entity.Comment;

public interface ICommentService {

	Page<Comment> findCommentsOfArticle(int articleId,int page);
	Comment save(Comment comment);
	int getCommentCountOfArticle(int articleId);
	Page<Comment> findCommentsOfAuthor(int authorId,int page);
	Page<Comment> findAllOfMyComment(int authorId,int page);
}
