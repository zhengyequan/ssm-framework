package com.yqbaba.baba.service;

import java.util.List;

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

	public LoupanInfo getByName(String name) {
		return babaDao.getByName(name);
	}

	public List<LoupanInfo> listLoupans(double minLng, double maxLng, double minLat, double maxLat) {
		return babaDao.listLoupans(minLng, maxLng, minLat, maxLat);
	}

	public void createLoupanInfo(LoupanInfo loupan) {
		babaDao.createLoupanInfo(loupan);
	}

	public void png2Jpg(LoupanInfo loupan) {
		babaDao.png2Jpg(loupan);
	}

}
