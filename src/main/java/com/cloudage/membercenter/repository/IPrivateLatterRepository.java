package com.cloudage.membercenter.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cloudage.membercenter.entity.PrivateLatter;

@Repository
public interface IPrivateLatterRepository extends PagingAndSortingRepository<PrivateLatter, Integer> {
	@Query("from PrivateLatter latter where (latter.receiver.id=?1 and latter.sender.id=?2) or (latter.sender.id=?1 and latter.receiver.id=?2)")
	Page<PrivateLatter> findPrivateLatterByReceiverId(int receiverId,int senderId,Pageable page);
}
