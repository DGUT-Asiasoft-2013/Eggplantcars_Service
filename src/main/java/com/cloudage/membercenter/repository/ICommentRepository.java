package com.cloudage.membercenter.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cloudage.membercenter.entity.Comment;


public interface ICommentRepository extends PagingAndSortingRepository<Comment,Integer> {

	@Query("from Comment comment where comment.article.id= ?1")
	Page<Comment> findAllOfArticleId(int articleId,Pageable page);
	
	@Query("select count(*) from Comment comment where comment.article.id = ?1")
	int commentCountOfArticle(int articleId);
	
	@Query("from Comment comment where comment.article.author.id=?1")
	Page<Comment> findAllOfArticleAuthorId(int author_id,Pageable page);
	
	@Query("from Comment comment where comment.author.id=?1")
	Page<Comment> findAllMyComment(int author_id,Pageable page);  //显示所有我发表的评论    我的ID，页数
}
