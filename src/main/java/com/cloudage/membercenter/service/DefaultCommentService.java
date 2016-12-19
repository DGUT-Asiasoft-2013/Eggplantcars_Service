package com.cloudage.membercenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudage.membercenter.entity.Comment;
import com.cloudage.membercenter.repository.ICommentRepository;


@Component
@Service
@Transactional
public class DefaultCommentService implements ICommentService{

	@Autowired
	ICommentRepository commentRepo;
	
	@Override
	public Page<Comment> findCommentsOfArticle(int articleId, int page) {
		Sort sort = new Sort(Direction.DESC,"createDate");
		PageRequest pageRequest=new PageRequest(page, 3,sort);
		return commentRepo.findAllOfArticleId(articleId, pageRequest);
	}

	@Override
	public Comment save(Comment comment) {
		// TODO Auto-generated method stub
		return commentRepo.save(comment);
	}
	
	@Override
	public int getCommentCountOfArticle(int articleId) {
	return commentRepo.commentCountOfArticle(articleId);
	}

	@Override
	public Page<Comment> findCommentsOfAuthor(int authorId, int page) {
		Sort sort = new Sort(Direction.DESC,"createDate");
		PageRequest pageRequest = new PageRequest(page, 5,sort);
		return commentRepo.findAllOfArticleAuthorId(authorId, pageRequest);
	}

	@Override
	public Page<Comment> findAllOfMyComment(int authorId, int page) {		//comment.User(author).id
		Sort sort = new Sort(Direction.DESC,"createDate");
		PageRequest pageRequest = new PageRequest(page, 5,sort);
		return commentRepo.findAllMyComment(authorId, pageRequest);
	}
	
}
