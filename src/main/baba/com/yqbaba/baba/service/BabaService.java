package com.yqbaba.baba.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yqbaba.baba.dao.BabaDao;
import com.yqbaba.baba.entity.LoupanInfo;
import com.yqbaba.common.entity.GlobalConfig;
import com.yqbaba.common.service.GlobalConfigService;
import com.yqbaba.framework.service.BaseService;
import com.yqbaba.framework.util.BeanUtil;

@Service("babaService")
public class BabaService extends BaseService {
	@Resource
	private BabaDao babaDao;

	@Resource
	private GlobalConfigService globalConfigService;

	public LoupanInfo getById(int id) {
		return babaDao.getById(id);
	}

	public LoupanInfo getByName(String name) {
		return babaDao.getByName(name);
	}

	public List<LoupanInfo> listLoupans(double minLng, double maxLng, double minLat, double maxLat) {
		GlobalConfig config = globalConfigService.getGlobalConfig();
		List<LoupanInfo> loupans = babaDao.listLoupans(minLng, maxLng, minLat, maxLat);
		for (LoupanInfo loupan : loupans) {
			List<String> indexImgs = BeanUtil.json2List(loupan.getIndexImgs(), String.class);
			loupan.setAbsIndexImg(config.getDomainName() + indexImgs.get(0));
		}

		return loupans;
	}

	public void createLoupanInfo(LoupanInfo loupan) {
		babaDao.createLoupanInfo(loupan);
	}

}
