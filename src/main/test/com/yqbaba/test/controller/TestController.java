package com.yqbaba.test.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yqbaba.framework.annotation.JsonMethod;
import com.yqbaba.framework.exception.BizException;
import com.yqbaba.test.entity.Test;
import com.yqbaba.test.error.TestError;
import com.yqbaba.test.service.TestService;

import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
@RequestMapping("/test")
public class TestController {

	@Resource
	private TestService testService;

	@JsonMethod
	@RequestMapping("/testJsonSuccess.do")
	public String testJsonSuccess(ModelMap map) {
		Test test = new Test();
		test.setIdCard("testJsonSuccess");
		test.setCardNo("1");
		map.put("data", JSONObject.fromObject(test).toString());
		return "common/json_success";
	}

	@JsonMethod
	@RequestMapping("/testJsonError.do")
	public String testJsonError(ModelMap map) {
		Test test = new Test();
		test.setIdCard("testJsonSuccess");
		test.setCardNo("1");
		map.put("test", test);
		throw new BizException(TestError.TEST);
	}

	@RequestMapping("/testPageSuccess.do")
	public String testPageSuccess(ModelMap map) {
		Test test = new Test();
		test.setIdCard("testJsonSuccess");
		test.setCardNo("1");
		map.put("test", test);
		return "test/test";
	}

	@RequestMapping("/testPageError.do")
	public String testPageError(ModelMap map) {
		Test test = new Test();
		test.setIdCard("testJsonSuccess");
		test.setCardNo("1");
		map.put("test", test);
		throw new BizException(TestError.TEST);
	}

}
