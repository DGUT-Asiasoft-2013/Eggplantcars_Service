package com.cloudage.membercenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudage.membercenter.entity.Admin;
import com.cloudage.membercenter.repository.IAdminRepository;

@Component
@Service
@Transactional
public class DefaultAdminService implements IAdminService {

	@Autowired
	IAdminRepository adminRepo;
	
	@Override
	public Admin create(String account, String passwordHash) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void login(String account, String passwordHash) {
		// TODO Auto-generated method stub
	}

	@Override
	public Admin getCurrentAdmin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changePassword(String newPasswordHash) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Admin> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
