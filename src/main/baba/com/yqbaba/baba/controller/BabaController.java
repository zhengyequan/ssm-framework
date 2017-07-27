package com.yqbaba.baba.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yqbaba.baba.service.BabaService;

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
}
