package com.cloudage.membercenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudage.membercenter.entity.News;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.repository.INewsRepository;

@Component
@Service
@Transactional
public class DefaultNewsService implements INewsService {

	@Autowired
	INewsRepository newsRepo;
	@Override
	public List<News> findAllByAuthor(User user) {
		// TODO Auto-generated method stub
		return newsRepo.findAllByAuthor(user);
	}

	@Override
	public List<News> findAllByAuthorId(Integer userId) {
		// TODO Auto-generated method stub
		return newsRepo.findAllByAuthorId(userId);
	}

	@Override
	public News findOne(int newsId) {
		// TODO Auto-generated method stub
		return newsRepo.findOne(newsId);
	}

	@Override
	public News save(News news) {
		// TODO Auto-generated method stub
		return newsRepo.save(news);
	}

	@Override
	public Page<News> getNews(int page) {
		Sort sort = new Sort(Direction.DESC,"createDate");
		PageRequest pageRequest = new PageRequest(page, 10, sort);
		return newsRepo.findAll(pageRequest);
	}

	

}
