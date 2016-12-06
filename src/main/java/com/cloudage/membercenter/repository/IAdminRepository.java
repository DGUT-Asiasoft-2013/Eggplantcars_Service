package com.cloudage.membercenter.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cloudage.membercenter.entity.Admin;

@Repository
public interface IAdminRepository extends CrudRepository<Admin, Integer>{

}
