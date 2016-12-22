package com.cloudage.membercenter.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cloudage.membercenter.entity.NewsComment;

public interface INewsCommentRepository extends PagingAndSortingRepository<NewsComment,Integer>{

}
