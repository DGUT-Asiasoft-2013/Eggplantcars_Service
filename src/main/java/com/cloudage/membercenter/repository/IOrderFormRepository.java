package com.cloudage.membercenter.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cloudage.membercenter.entity.OrderForm;

public interface IOrderFormRepository extends PagingAndSortingRepository<OrderForm,Integer>{

}
