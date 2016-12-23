package com.cloudage.membercenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudage.membercenter.entity.History;
import com.cloudage.membercenter.repository.IHistoryRepository;

@Component
@Service
@Transactional
public class DefaultHistoryService implements IHistoryService {
	@Autowired
	IHistoryRepository historyRepo;

	@Override
	public History save(History history) {
		// TODO Auto-generated method stub
		return historyRepo.save(history);
	}

	@Override
	public Page<History> getHistoryItems(int page) {
		// TODO Auto-generated method stub
		Sort sort = new Sort(Direction.DESC,"createDate");  //≈≈–Ú
		PageRequest pageRequest = new PageRequest(page, 10, sort);
		return historyRepo.findAll(pageRequest);
	}

	@Override
	public void delectAllOfHistory(int author_id) {
		// TODO Auto-generated method stub
		historyRepo.delectALLOfHistory(author_id);
	}
	
}
