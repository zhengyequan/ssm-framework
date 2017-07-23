package com.yqbaba.framework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yqbaba.framework.Exception.BizException;

public class ExcpetionInterceptor implements HandlerInterceptor {

	Log log = LogFactory.getLog(ExcpetionInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		if (ex == null) {
			return;
		}

		ResponseBody annotation = handlerMethod.getMethodAnnotation(ResponseBody.class);
		if (annotation == null) {
			response.sendRedirect("/error.html");
			return;
		}

		// TODO data.put("result", -1);
		if (ex instanceof BizException) {
			// data.put("message", ex.getMessage);
			return;
		}

		// data.put("message", common.errorMsg);
	}

}
