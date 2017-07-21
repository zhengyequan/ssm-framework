package com.yqbaba.test.dao;

import org.springframework.stereotype.Controller;

import com.yqbaba.framework.dao.BaseDao;
import com.yqbaba.test.entity.Test;

@Controller("testDao")
public class TestDao extends BaseDao {
	private String namespace = "test";

	public Test getUserByIdCard(String idCard) {
		return sqlSessionTemplate.selectOne(namespace + ".getUserByIdCard", idCard);
	}

	public void createTest(Test test) {
		sqlSessionTemplate.insert(namespace + ".createTest", test);
	}

}
