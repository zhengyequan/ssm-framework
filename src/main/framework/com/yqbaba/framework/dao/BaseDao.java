package com.yqbaba.framework.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

public class BaseDao {
	
	protected String namespace;

	@Resource
	protected SqlSessionTemplate sqlSessionTemplate;

}
