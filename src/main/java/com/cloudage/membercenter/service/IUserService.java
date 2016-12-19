
package com.cloudage.membercenter.service;

import com.cloudage.membercenter.entity.User;

public interface IUserService {
	User save(User user);
	User findByAccount(String account);
	User findById(Integer uid);
	User findByEmail(String email);
}