package com.yqbaba.baba.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yqbaba.baba.service.BabaService;
import com.yqbaba.framework.entity.GPS;
import com.yqbaba.framework.util.BeanUtil;
import com.yqbaba.framework.util.PositionUtil;

@Controller
@Scope("prototype")
@RequestMapping("/main")
public class BabaController {

	@Resource
	private BabaService babaService;

	@RequestMapping("/index.do")
	public String renderIndex() {

		return "baba/index";
	}

	@RequestMapping("/transformPosition.do")
	public String transformPosition(ModelMap map, @RequestParam double lng, @RequestParam double lat) {
		GPS gps = PositionUtil.gps84_To_Gcj02(lat, lng);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("lat", gps.getWgLat());
		result.put("lng", gps.getWgLng());
		map.put("data", BeanUtil.json(result));
		return "common/json_success";
	}
}
