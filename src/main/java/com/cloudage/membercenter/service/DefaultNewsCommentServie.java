package com.cloudage.membercenter.service;

import org.springframework.beans.factory.annotation.Autowired;
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

}
