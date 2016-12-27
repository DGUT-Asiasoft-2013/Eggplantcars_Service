package com.cloudage.membercenter.service;

import com.cloudage.membercenter.entity.Money;

public interface IMoneyService {

	boolean checkMoneyed(int userId);
	
	Money getMoneyByUserId(int userId);

	Money save(Money money);
}
