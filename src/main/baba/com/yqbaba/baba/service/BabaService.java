package com.yqbaba.baba.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yqbaba.baba.dao.BabaDao;
import com.yqbaba.baba.entity.User;

@Service("babaService")
public class BabaService {
	@Resource
	private BabaDao babaDao;
	
	public User getUserById(int id) {
		return babaDao.getUserById(id);
	}

}
