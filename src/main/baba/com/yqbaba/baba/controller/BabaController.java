package com.yqbaba.baba.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yqbaba.baba.entity.User;
import com.yqbaba.baba.service.BabaService;

@Controller
@Scope("prototype")
@RequestMapping("/main")
public class BabaController {

	@Resource
	private BabaService babaService;

	@RequestMapping("/test.do")
	public String test(@RequestParam("id") int id) {
		User user = babaService.getUserById(id);
		if (user == null) {
			System.out.println(user);
		} else {
			System.out.println(user.getName() + "; " + user.getAge());
		}

		return "baba/index";
	}
}
