package com.cloudage.membercenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cloudage.membercenter.entity.Record;

public interface IRecordRepository extends PagingAndSortingRepository<Record, Integer> {

	@Query("from Record record where record.id.user.id = ?1")
	List<Record> findAllByUserId(Integer userid);

}
