package com.cloudage.membercenter.service;

import org.springframework.data.domain.Page;

import com.cloudage.membercenter.entity.History;

public interface IHistoryService {

	History save(History history);

	Page<History> getHistoryItems(int page);

	void delectAllOfHistory(int author_id);

}
