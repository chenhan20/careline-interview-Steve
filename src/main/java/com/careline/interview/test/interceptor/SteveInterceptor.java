package com.careline.interview.test.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.careline.interview.test.service.CommonService;
import com.careline.interview.test.util.commonUtil;

@Component
public class SteveInterceptor implements HandlerInterceptor {

	@Autowired
	CommonService commonService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 權限卡控 之後弄在這邊
		// 執行次數 之後弄在這邊
		String Url = request.getRequestURI();
		System.out.println(Url);
		Map<String, Object> userData = (Map<String, Object>) request.getSession().getAttribute("LoginUser");
		String userId;
		boolean login = commonUtil.chkLogin(userData);
		if (login) {
			userId = (String) userData.get("ID");
		} else {
			userId = "NonLogin";
		}
		Map<String, Object> ApiData = commonService.queryApiData(Url);
		if (ApiData != null) {
			commonService.insertApiCourse(ApiData, userId);
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}
}
