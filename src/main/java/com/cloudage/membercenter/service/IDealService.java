package com.cloudage.membercenter.service;

import org.springframework.data.domain.Page;

import com.cloudage.membercenter.entity.Deal;

public interface IDealService {
	Deal save(Deal deal);
	Page<Deal> getDealItems(int page);

}
