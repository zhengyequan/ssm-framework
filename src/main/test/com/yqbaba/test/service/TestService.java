package com.yqbaba.test.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yqbaba.framework.service.BaseService;
import com.yqbaba.test.dao.TestDao;
import com.yqbaba.test.entity.Test;

@Service("testService")
public class TestService extends BaseService {

	@Resource
	private TestDao testDao;

	public Test getByIdCard(String idCard) {
		return testDao.getUserByIdCard(idCard);
	}

	public void createTest(Test test) {
		testDao.createTest(test);
	}

}
