package com.yqbaba.test.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yqbaba.test.entity.Test;
import com.yqbaba.test.service.TestService;

@Controller
@Scope("prototype")
@RequestMapping("/test")
public class TestController {

	@Resource
	private TestService testService;

	@RequestMapping("/test.do")
	public String test(ModelMap map, @RequestParam("idCard") String idCard, @RequestParam("cardNo") String cardNo) {
		Test test = new Test();
		test.setIdCard(idCard);
		test.setCardNo(cardNo);
		map.put("test", test);
		return "test/test";
	}

}
