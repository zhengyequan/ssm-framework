package com.yqbaba.baba.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yqbaba.baba.entity.LoupanInfo;
import com.yqbaba.framework.dao.BaseDao;

@Repository("babaDao")
public class BabaDao extends BaseDao {

	private String namespace = "baba";

	public LoupanInfo getById(int id) {
		return sqlSessionTemplate.selectOne(namespace + ".getById", id);
	}

	public LoupanInfo getByName(String name) {
		return sqlSessionTemplate.selectOne(namespace + ".getByName", name);
	}

	public List<LoupanInfo> listLoupans(double minLng, double maxLng, double minLat, double maxLat) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("minLng", minLng);
		params.put("maxLng", maxLng);
		params.put("minLat", minLat);
		params.put("maxLat", maxLat);
		return sqlSessionTemplate.selectList(namespace + ".listLoupans", params);
	}

	public void createLoupanInfo(LoupanInfo loupan) {
		sqlSessionTemplate.insert(namespace + ".createLoupanInfo", loupan);
	}

	public void png2Jpg(LoupanInfo loupan) {
		sqlSessionTemplate.update(namespace + ".png2Jpg", loupan);
	}

}
