package com.cloudage.membercenter.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cloudage.membercenter.entity.PrivateLatter;

public interface IPrivateLatterService {

	public PrivateLatter save(PrivateLatter latter);

	Page<PrivateLatter> findPrivateLetterByReveiverId(int receiverId, int senderId, int page);

	int countUnreadMessages(int receiverId, int senderId);

	void updateUnread(int receiverId,int senderId);
}
