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
import com.cloudage.membercenter.entity.ShoppingCar;
import com.cloudage.membercenter.entity.ShoppingCar.Key;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.repository.IShoppingCarRepository;

@Component
@Service
@Transactional
public class DefaultShoppingCarService implements IShoppingCarService {

	@Autowired
	IShoppingCarRepository shoppingCarRepo;
	
	@Override
	public void addShoppingCar(Deal deal, User currentUser) {
		// TODO Auto-generated method stub
		ShoppingCar.Key key=new Key();
		key.setBuyer(currentUser);
		key.setDeal(deal);
		
		ShoppingCar shoppingCar=new ShoppingCar();
		shoppingCar.setId(key);
		shoppingCarRepo.save(shoppingCar);
	}

	@Override
	public Page<ShoppingCar> findMyShoppingCar(int buyer_id, int page) {
		// TODO Auto-generated method stub
		Sort sort = new Sort(Direction.DESC,"createDate");
		PageRequest pageRequest = new PageRequest(page, 5,sort);
		return shoppingCarRepo.findMyShoppingCar(buyer_id, pageRequest);
	}

	@Override
	public void delectMyShoppingCar(int deal_id, int buyer_id) {
		// TODO Auto-generated method stub
		shoppingCarRepo.delectMyShoppingCar(deal_id,buyer_id);
	}

}
