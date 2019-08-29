package com.idexcel.adminservice.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthInterceptor implements HandlerInterceptor {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object object) throws Exception {
		Enumeration<String> headerNames = request.getHeaderNames();
		logger.info("Request HTTP Headers: START");
		while(headerNames.hasMoreElements()) {
		  String headerName = (String)headerNames.nextElement();
		  logger.info(headerName + "=" + request.getHeader(headerName));
		}
		logger.info("Request HTTP Headers: END");
		return true;
	}
}
