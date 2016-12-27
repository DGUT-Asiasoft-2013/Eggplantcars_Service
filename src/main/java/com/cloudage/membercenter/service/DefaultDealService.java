package com.cloudage.membercenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudage.membercenter.entity.Deal;
import com.cloudage.membercenter.repository.IDealRepository;


@Component
@Service
@Transactional
public class DefaultDealService implements IDealService{

	@Autowired
	IDealRepository dealRepo;
	@Override
	public Deal save(Deal deal) {
		// TODO Auto-generated method stub
		return dealRepo.save(deal);
	}
	@Override
	public Page<Deal> getDealItems(int page) {
		// TODO Auto-generated method stub
		Sort sort = new Sort(Direction.DESC,"createDate");  //≈≈–Ú
		PageRequest pageRequest = new PageRequest(page, 10, sort);
		return dealRepo.findAll(pageRequest);
	}
	@Override
	public Page<Deal> searchTextByKeyword(String keyword, int page) {
		// TODO Auto-generated method stub
		Sort sort = new Sort(Direction.DESC,"createDate");  //≈≈–Ú
		PageRequest pageRequest = new PageRequest(page, 5, sort);
		return dealRepo.searchTextWithKeyword(keyword, pageRequest);
	}
	@Override
	public Deal findById(int deal_id) {
		// TODO Auto-generated method stub
		return dealRepo.findOne(deal_id);
	}

}
