package com.cloudage.membercenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudage.membercenter.entity.Address;
import com.cloudage.membercenter.repository.IAddressRepository;

@Component
@Service
@Transactional
public class DefaultAddressService implements IAddressService {
	@Autowired
	IAddressRepository addressRepo;

	@Override
	public Address save(Address address) {
		// TODO Auto-generated method stub
		return addressRepo.save(address);
	}

	@Override
	public Page<Address> findAddressOfUser(int userId, int page) {
		Sort sort = new Sort(Direction.DESC, "createDate");
		PageRequest pageRequest = new PageRequest(page, 3, sort);
		return addressRepo.findAddressByUserId(userId, pageRequest);
	}

	@Override
	public void delectAddressById(int address_id) {
		// TODO Auto-generated method stub
		addressRepo.delectAddressById(address_id);
	}

	@Override
	public Address findAddressById(int address_id) {
		// TODO Auto-generated method stub
		return addressRepo.findAddressById(address_id);
	}

	public Address findLastAddressOfUser(int meId) {
		Sort sort = new Sort(Direction.DESC, "createDate");
		PageRequest page = new PageRequest(0, 1, sort);
		return addressRepo.findAddressByUserId(meId, page).getContent().get(0);

	}

}
