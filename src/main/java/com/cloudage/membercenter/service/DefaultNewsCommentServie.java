package com.cloudage.membercenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudage.membercenter.entity.NewsComment;
import com.cloudage.membercenter.repository.INewsCommentRepository;


@Component
@Service
@Transactional
public class DefaultNewsCommentServie implements INewsCommentService{

	@Autowired
	INewsCommentRepository newsCommentRepo;
	
	@Override
	public NewsComment save(NewsComment newsComment) {
		// TODO Auto-generated method stub
		return newsCommentRepo.save(newsComment);
	}

	@Override
	public Page<NewsComment> findNewsCommentsOfNews(int news_id, int page) {
		// TODO Auto-generated method stub
		Sort sort = new Sort(Direction.DESC,"createDate");
		PageRequest pageRequest=new PageRequest(page, 3,sort);
		return newsCommentRepo.findAllOfArticleId(news_id, pageRequest);
	}

	@Override
	public Page<NewsComment> findAllOfMyNewsComment(int author_id, int page) {
		// TODO Auto-generated method stub
		Sort sort = new Sort(Direction.DESC,"createDate");
		PageRequest pageRequest = new PageRequest(page, 5,sort);
		return newsCommentRepo.findAllOfArticleAuthorId(author_id, pageRequest);
	}

	@Override
	public Page<NewsComment> findNewsCommentsOfAuthor(int author_id, int page) {
		// TODO Auto-generated method stub
		Sort sort = new Sort(Direction.DESC,"createDate");
		PageRequest pageRequest = new PageRequest(page, 5,sort);
		return newsCommentRepo.findAllMyComment(author_id, pageRequest);
	}
	

}
