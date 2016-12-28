package com.cloudage.membercenter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cloudage.membercenter.entity.Address;

public interface IAddressRepository extends PagingAndSortingRepository<Address,Integer> {
	@Query("from Address address where address.user.id=?1")
	Page<Address> findAddressByUserId(int userId,Pageable page);
}
