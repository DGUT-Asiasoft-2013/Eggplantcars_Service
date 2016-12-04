package com.cloudage.repository;

import org.springframework.stereotype.Repository;
import com.cloudage.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface IUserRepository extends PagingAndSortingRepository<User, Integer>{

}
