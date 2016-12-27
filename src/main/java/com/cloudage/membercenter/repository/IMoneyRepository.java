package com.cloudage.membercenter.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cloudage.membercenter.entity.Money;

public interface IMoneyRepository extends PagingAndSortingRepository<Money,Integer> {

	@Query("select count(*) from Money money where money.id.user.id = ?1")
	int checkMoneyExsists(Integer userid);
	
	@Query("from Money money where money.id.user.id = ?1")
	Money getMoneyByUserId(Integer userid);
}
