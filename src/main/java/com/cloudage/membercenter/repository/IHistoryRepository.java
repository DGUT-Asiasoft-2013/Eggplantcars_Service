package com.cloudage.membercenter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cloudage.membercenter.entity.History;

public interface IHistoryRepository extends PagingAndSortingRepository<History, Integer>{

	@Modifying
	@Query("delete History history where history.author.id=?1")
	void delectALLOfHistory(int author_id);

}
