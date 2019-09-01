package com.idexcel.adminservice.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import com.idexcel.adminservice.exceptions.AuthenticationException;

public class AuthInterceptor implements HandlerInterceptor {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final String APIM_HEADER_NAME = "idexceldemo-apim-token";
	private static final String APIM_HEADER_VALUE = "e3c865d32b1c7119304ffe5c57dbcb5a";
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object object) throws Exception {
		Enumeration<String> headerNames = request.getHeaderNames();
		String apimToken = "";
		logger.info("Request HTTP Headers: START");
		while(headerNames.hasMoreElements()) {
		  String headerName = headerNames.nextElement();
		  String headerValue = request.getHeader(headerName);
		  if (APIM_HEADER_NAME.equals(headerName)) {
			  apimToken = headerValue;
		  }
		  logger.info("headerName = {}", request.getHeader(headerName));
		}
		logger.info("Request HTTP Headers: END");
		/*if (!APIM_HEADER_VALUE.equals(apimToken)) {
			logger.info("Invalid APIM Token");
			throw new AuthenticationException("User Not Authenticated");
		}*/
		return true;
	}
}
