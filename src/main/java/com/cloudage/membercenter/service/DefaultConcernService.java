package com.cloudage.membercenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.entity.Concern;
import com.cloudage.membercenter.entity.Concern.Key;
import com.cloudage.membercenter.entity.News;
import com.cloudage.membercenter.repository.IConcernRepository;


@Component
@Service
@Transactional
public class DefaultConcernService implements IConcernService  {

	@Autowired
	IConcernRepository concernRepo;

	@Override
	public void addConcern(User user, User news_author) {
		Concern.Key key = new Key();
		key.setUser(user);
		key.setUser(news_author);
		Concern lk = new Concern();
		lk.setIdKey(key);
		concernRepo.save(lk);
	}

	@Override
	public boolean checkConcerned(int userId, int newsauthorid) {
		return concernRepo.checkConcernExsists(userId, newsauthorid)>0;
	}

	@Override
	public void removeConcern(User user, User news_author) {
		// TODO Auto-generated method stub
		Concern.Key key = new Key();
		key.setUser(user);
		key.setUser(news_author);
		concernRepo.delete(key);
	}
}
