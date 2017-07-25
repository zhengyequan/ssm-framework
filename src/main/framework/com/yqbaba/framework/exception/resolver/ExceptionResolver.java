package com.yqbaba.framework.exception.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.yqbaba.framework.annotation.JsonMethod;
import com.yqbaba.framework.error.IError;
import com.yqbaba.framework.exception.BizException;
import com.yqbaba.framework.util.MessageUtil;
import com.yqbaba.framework.util.StringUtil;

public class ExceptionResolver implements HandlerExceptionResolver {

	private final static Log log = LogFactory.getLog(MessageUtil.class);

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
			BizException be = (BizException) e;
			Enum<?> errorCode = be.getErrorCode();
			if (errorCode instanceof IError) {
				view.addObject("errorCode", ((IError) errorCode).getCode());
			}

			view.addObject("message", StringUtil.quotes(MessageUtil.getMessage(be.getErrorCode(), be.getParams())));
		} else {
			log.error("[error] :", e);
			view.addObject("message", StringUtil.quotes("system error, please try later...^_^"));
		}

		return view;
	}

}
