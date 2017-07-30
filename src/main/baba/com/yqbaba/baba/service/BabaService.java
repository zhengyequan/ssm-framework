package com.yqbaba.baba.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yqbaba.baba.dao.BabaDao;
import com.yqbaba.baba.entity.LoupanInfo;
import com.yqbaba.framework.service.BaseService;

@Service("babaService")
public class BabaService extends BaseService {
	@Resource
	private BabaDao babaDao;

	public LoupanInfo getById(int id) {
		return babaDao.getById(id);
	}

	public void createLoupanInfo(LoupanInfo loupan) {
		babaDao.createLoupanInfo(loupan);
	}

}
