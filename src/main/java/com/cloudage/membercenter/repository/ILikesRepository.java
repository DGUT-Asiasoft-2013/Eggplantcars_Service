package com.cloudage.membercenter.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cloudage.membercenter.entity.Likes;

@Repository
public interface ILikesRepository extends PagingAndSortingRepository<Likes, Likes.Key>{

	@Query("select count(*) from Likes likes where likes.id.news.id = ?1")
	int likeCountsOfArticle(int newsId);
	
	@Query("select count(*) from Likes likes where likes.id.user.id = ?1 and likes.id.news.id = ?2")
	int checkLikesExsists(int authorId, int newsId);
}
