package com.yqbaba.baba.dao;

import org.springframework.stereotype.Repository;

import com.yqbaba.baba.entity.LoupanInfo;
import com.yqbaba.framework.dao.BaseDao;

@Repository("babaDao")
public class BabaDao extends BaseDao {

	private String namespace = "baba";

	public LoupanInfo getById(int id) {
		return sqlSessionTemplate.selectOne(namespace + ".getById", id);
	}

	public void createLoupanInfo(LoupanInfo loupan) {
		sqlSessionTemplate.insert(namespace + ".createLoupanInfo", loupan);
	}

}
