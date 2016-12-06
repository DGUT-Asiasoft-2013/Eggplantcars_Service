package com.cloudage.membercenter.service;

import java.util.List;

import com.cloudage.membercenter.entity.Admin;

public interface IAdminService {
	Admin create(String account, String passwordHash);

	List<Admin> list();
	
	void login(String account, String passwordHash);
	Admin getCurrentAdmin();
	boolean changePassword(String newPasswordHash);
	void logout();
}
