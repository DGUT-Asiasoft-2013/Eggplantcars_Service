package com.cloudage.membercenter.service;

import org.springframework.data.domain.Page;

import com.cloudage.membercenter.entity.PrivateLatter;

public interface IPrivateLatterService {

	public PrivateLatter save(PrivateLatter latter);
	
	Page<PrivateLatter> findPrivateLetterByReveiverId(int receiverId,int senderId,int page);
}
