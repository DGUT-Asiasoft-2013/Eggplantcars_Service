package com.cloudage.membercenter.service;

import org.springframework.data.domain.Page;

import com.cloudage.membercenter.entity.Deal;
import com.cloudage.membercenter.entity.ShoppingCar;
import com.cloudage.membercenter.entity.User;

public interface IShoppingCarService {
	void addShoppingCar(Deal deal, User currentUser);
	Page<ShoppingCar> findMyShoppingCar(int buyer_id, int page);
	void delectMyShoppingCar(int deal_id, int buyer_id);

}
