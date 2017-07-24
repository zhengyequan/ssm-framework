package com.yqbaba.framework.exception.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.yqbaba.framework.annotation.JsonMethod;
import com.yqbaba.framework.exception.BizException;

public class ExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception e) {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		JsonMethod jsonMethod = handlerMethod.getMethodAnnotation(JsonMethod.class);
		if (jsonMethod == null) {
			return new ModelAndView("common/error");
		}

		ModelAndView view = new ModelAndView("common/json_error");

		if (e instanceof BizException) {
			BizException bizException = (BizException) e;
			view.addObject("message", bizException.getMessage());
		} else {
			view.addObject("message", "系统出错啦");
		}

		return view;
	}

}
