package com.yqbaba.baba.dao;

import org.springframework.stereotype.Repository;

import com.yqbaba.baba.entity.User;
import com.yqbaba.framework.dao.BaseDao;

@Repository("babaDao")
public class BabaDao extends BaseDao {

	private String namespace = "baba";

	public User getUserById(int id) {
		return sqlSessionTemplate.selectOne(namespace + ".getUserById", id);
	}
}
