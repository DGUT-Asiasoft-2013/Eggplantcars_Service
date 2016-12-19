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

import com.cloudage.membercenter.entity.Article;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.repository.IArticleRepository;

@Component
@Service
@Transactional
public class DefaultArticleService implements IArticleService {
	@Autowired
	IArticleRepository articleRepo;

	@Override
	public List<Article> findAllByAuthor(User user) {
		return articleRepo.findAllByAuthor(user); 
	}

	@Override
	public List<Article> findAllByAuthorId(Integer userId) {
		return articleRepo.findAllByAuthorId(userId);
	}

	@Override
	public Article save(Article article) {
		return articleRepo.save(article);
	}
	
	@Override
	public Page<Article> getFeeds(int page){
		Sort sort = new Sort(Direction.DESC,"createDate");  //≈≈–Ú
		PageRequest pageRequest = new PageRequest(page, 10, sort);
		return articleRepo.findAll(pageRequest);
		}

	@Override
	public Article findOne(int articleId) {
		// TODO Auto-generated method stub
		return articleRepo.findOne(articleId);
	}

	@Override
	public Page<Article> searchTextWithKeyword(String keyword, int page) {
		Sort sort = new Sort(Direction.DESC,"createDate");  //≈≈–Ú
		PageRequest pageRequest = new PageRequest(page, 5, sort);
		return articleRepo.searchTextWithKeyword(keyword, pageRequest);
		
	}
}

