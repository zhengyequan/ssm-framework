package com.yqbaba.common.dao;

import org.springframework.stereotype.Repository;

import com.yqbaba.common.entity.GlobalConfig;
import com.yqbaba.framework.dao.BaseDao;

@Repository("globalConfigDao")
public class GlobalConfigDao extends BaseDao {

	private String namespace = "globalConfig";

	public GlobalConfig getGlobalConfig() {
		return sqlSessionTemplate.selectOne(namespace + ".getGlobalConfig");
	}

}
