package com.cloudage.membercenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudage.membercenter.entity.Record;
import com.cloudage.membercenter.repository.IRecordRepository;

@Component
@Service
@Transactional
public class DefaultRecordService implements IRecordService{

	@Autowired
	IRecordRepository recordRepo;
	
	@Override
	public Record save(Record record) {
		// TODO Auto-generated method stub
		return recordRepo.save(record);
	}

	@Override
	public List<Record> findAllByUserId(Integer userid) {
		// TODO Auto-generated method stub
		return recordRepo.findAllByUserId(userid);
	}

}
