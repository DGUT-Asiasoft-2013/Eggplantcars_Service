package com.cloudage.membercenter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cloudage.membercenter.entity.ShoppingCar;

public interface IShoppingCarRepository extends PagingAndSortingRepository<ShoppingCar, Integer>{

	@Query("from ShoppingCar shoppingCar where shoppingCar.id.buyer.id=?1")
	Page<ShoppingCar> findMyShoppingCar(int buyer_id, Pageable pageRequest);
	
	@Modifying
	@Query("delete ShoppingCar shoppingCar where shoppingCar.id.deal.id=?1 and shoppingCar.id.buyer.id=?2")
	void delectMyShoppingCar(int deal_id, int buyer_id);


}
