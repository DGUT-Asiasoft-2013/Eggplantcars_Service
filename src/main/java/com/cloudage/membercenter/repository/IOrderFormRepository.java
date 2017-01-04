package com.cloudage.membercenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cloudage.membercenter.entity.OrderForm;

@Repository
public interface IOrderFormRepository extends PagingAndSortingRepository<OrderForm,Integer>{

	@Query("from OrderForm orderform where orderform.id.buyer.id = ?1")
	List<OrderForm> findAllByBuyerId(Integer buyerid);
	
	@Query("from OrderForm orderform where orderform.id.seller.id = ?1")
	List<OrderForm> findAllBySellerId(Integer sellerid);
	
	@Query("from OrderForm orderform where orderform.id = ?1")
	OrderForm findByOrderId(Integer orderid);
	
}
