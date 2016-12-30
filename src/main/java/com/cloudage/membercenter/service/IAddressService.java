package com.cloudage.membercenter.service;

import org.springframework.data.domain.Page;

import com.cloudage.membercenter.entity.Address;

public interface IAddressService {

	Page<Address> findAddressOfUser(int userId,int page);
	Address save(Address address);

	void delectAddressById(int address_id);
	Address findAddressById(int address_id);

	Address findLastAddressOfUser(int meId);

}
