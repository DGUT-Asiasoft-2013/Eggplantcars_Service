package com.cloudage.membercenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.repository.IUserRepository;

@Component
@Service
@Transactional
public class DefaultUserService implements IUserService {

	@Autowired
	IUserRepository userRepo;

	@Override
	public User save(User user) {
		return userRepo.save(user);
	}

	public User findByAccount(String account){
		return userRepo.findUserByAccount(account);
	}

	@Override
	public User findById(Integer uid) {
		return userRepo.findOne(uid);
	}

	@Override
	public User findByPasswordHash(Integer userId,String passwordHash) {
		return userRepo.findUserByPasswordHash(userId,passwordHash);
	}

	@Override
	public User findByEmail(String email) {
		return userRepo.findUserByEmail(email);
	}


}

