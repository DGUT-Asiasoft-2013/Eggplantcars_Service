package com.cloudage.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cloudage.entity.Admin;

@Repository
public interface IAdminRepository extends CrudRepository<Admin, Integer>{

}
