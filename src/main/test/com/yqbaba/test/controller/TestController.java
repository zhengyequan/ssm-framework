package com.yqbaba.test.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
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
	public String test(@RequestParam("idCard") String idCard, @RequestParam("cardNo") String cardNo) {
		Test entity = new Test();
		entity.setIdCard(idCard);
		entity.setCardNo(cardNo);
		testService.createTest(entity);

		Test test = testService.getByIdCard("jj");
		if (test == null) {
			System.out.println(test);
		} else {
			System.out.println(test.getIdCard() + "; " + test.getCardNo());
		}
		return "test/test";
	}

}
