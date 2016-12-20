package com.cloudage.membercenter.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cloudage.membercenter.entity.Deal;

public interface IDealRepository extends PagingAndSortingRepository<Deal,Integer>{

	
}
