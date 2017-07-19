package com.yqbaba.baba.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yqbaba.baba.entity.User;
import com.yqbaba.baba.service.BabaService;

@Controller
@RequestMapping("/main")
public class BabaController {

	@Resource
	private BabaService babaService;
	
	@RequestMapping("/test.do")
	public String test() {
		int id = 1;
		User user = babaService.getUserById(id);
		System.out.println("name : " + user.getName() + "; age : " + user.getAge());
		return "index";
	}
}
