
package com.cloudage.membercenter.service;

import org.springframework.data.domain.Page;

import com.cloudage.membercenter.entity.User;

public interface IUserService {
	User save(User user);
	User findByAccount(String account);
	User findById(Integer uid);
	User findByEmail(String email);
	User findByPasswordHash(Integer userId,String passwordHash);
	Page<User> getAllUser(int page);
}