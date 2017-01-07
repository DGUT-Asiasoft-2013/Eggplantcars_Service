package com.cloudage.membercenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
	
	
	//获取所有用户
	@Override
	public Page<User> getAllUser(int page){
		Sort sort = new Sort(Direction.DESC,"id"); 
		PageRequest pageRequest = new PageRequest(page, 20, sort);
		return userRepo.findAll(pageRequest);
	}

	@Override
	public User findUserByAccount(String account) {
		// TODO Auto-generated method stub
		return userRepo.findUserByAccount(account);
	}

	
}

