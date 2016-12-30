package com.cloudage.membercenter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cloudage.membercenter.entity.Deal;

public interface IDealRepository extends PagingAndSortingRepository<Deal,Integer>{
	
	@Query("from Deal deal where deal.text like %?1%")
	Page<Deal> searchTextWithKeyword(String keyword, Pageable pageRequest);
	@Modifying
	@Query("delete Deal deal where deal.id=?1")
	void delectDeal(int deal_id);

}
