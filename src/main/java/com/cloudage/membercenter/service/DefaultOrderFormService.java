package com.cloudage.membercenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Override
	public List<OrderForm> findByBuyerId(Integer buyerid) {
		// TODO Auto-generated method stub
		return orderRepo.findAllByBuyerId(buyerid);
	}
	
	@Override
	public List<OrderForm> findBySellerId(Integer sellerid) {
		// TODO Auto-generated method stub
		return orderRepo.findAllBySellerId(sellerid);
	}
	@Override
	public OrderForm findByOrderId(Integer orderid) {
		// TODO Auto-generated method stub
		return orderRepo.findByOrderId(orderid);
	}

}
