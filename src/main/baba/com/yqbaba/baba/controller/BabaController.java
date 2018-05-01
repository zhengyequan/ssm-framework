package com.yqbaba.baba.controller;

import com.yqbaba.baba.entity.LoupanInfo;
import com.yqbaba.baba.service.BabaService;
import com.yqbaba.framework.util.BeanUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Scope("prototype")
@RequestMapping("/main")
public class BabaController {

	@Resource
	private BabaService babaService;

	@RequestMapping("/index")
	public String renderIndex(ModelMap map) {
		return "baba/index";
	}

	@RequestMapping("/listLoupans")
	public String listLoupans(ModelMap map, double minLng, double maxLng, double minLat, double maxLat) {
		List<LoupanInfo> loupans = babaService.listLoupans(minLng, maxLng, minLat, maxLat);
		map.put("data", BeanUtil.jsonArray(loupans));
		return "common/json_success";
	}

}
