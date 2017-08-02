package com.yqbaba.common.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yqbaba.common.dao.GlobalConfigDao;
import com.yqbaba.common.entity.GlobalConfig;
import com.yqbaba.framework.service.BaseService;

@Service("globalConfigService")
public class GlobalConfigService extends BaseService {

	@Resource
	private GlobalConfigDao globalConfigDao;

	public GlobalConfig getGlobalConfig() {
		return globalConfigDao.getGlobalConfig();
	}

}
