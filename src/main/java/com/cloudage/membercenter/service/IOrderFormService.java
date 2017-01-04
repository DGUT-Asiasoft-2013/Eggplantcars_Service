package com.cloudage.membercenter.service;

import java.util.List;

import com.cloudage.membercenter.entity.OrderForm;

public interface IOrderFormService {

	OrderForm save(OrderForm order);
	
	OrderForm findByOrderId(Integer orderid);

	List<OrderForm> findByBuyerId(Integer buyerid);
	
	List<OrderForm> findBySellerId(Integer sellerid);
}
