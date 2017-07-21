package com.yqbaba.baba.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yqbaba.baba.entity.User;
import com.yqbaba.baba.service.BabaService;

import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
@RequestMapping("/main")
public class BabaController {

	@Resource
	private BabaService babaService;
	
	@RequestMapping("/test.do")
	@ResponseBody
	public String test() {
		int id = 3;
		User user = babaService.getUserById(id);
		return JSONObject.fromObject(user).toString(2);
	}
}
