package com.cloudage.membercenter.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cloudage.membercenter.entity.Concern;


@Repository
public interface IConcernRepository extends PagingAndSortingRepository<Concern,Concern.Key> {

	@Query("select count(*) from Concern concern where concern.id.user.id = ?1 and concern.id.news_author.id = ?2")
	int checkConcernExsists(int authorId, int newsauthorid);
}
