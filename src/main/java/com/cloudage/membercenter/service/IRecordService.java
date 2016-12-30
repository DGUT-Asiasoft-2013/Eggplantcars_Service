package com.cloudage.membercenter.service;

import java.util.List;

import com.cloudage.membercenter.entity.Record;

public interface IRecordService {

	Record save(Record record);
	
	List<Record> findAllByUserId(Integer userid);
}
