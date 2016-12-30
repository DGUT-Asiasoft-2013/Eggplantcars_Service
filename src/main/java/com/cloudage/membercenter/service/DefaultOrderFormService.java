package com.cloudage.membercenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudage.membercenter.entity.Deal;
import com.cloudage.membercenter.entity.OrderForm;
import com.cloudage.membercenter.repository.IOrderFormRepository;

@Component
@Service
@Transactional
public class DefaultOrderFormService implements IOrderFormService{

	@Autowired
	IOrderFormRepository orderRepo;
	@Override
	public OrderForm save(OrderForm order) {
		// TODO Auto-generated method stub
		return orderRepo.save(order);
	}

}
