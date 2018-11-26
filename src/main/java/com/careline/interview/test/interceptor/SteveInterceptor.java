package com.careline.interview.test.interceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.careline.interview.test.service.CommonService;
import com.careline.interview.test.util.commonMsg;
import com.careline.interview.test.util.commonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class SteveInterceptor implements HandlerInterceptor {

	@Autowired
	CommonService commonService;

	public final ObjectMapper mapper = new ObjectMapper();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 權限卡控 之後弄在這邊

		boolean checkIn = true;

		// 執行次數 之後弄在這邊
		String Url = request.getRequestURI();
		Map<String, Object> userData = (Map<String, Object>) request.getSession().getAttribute("LoginUser");
		String userId;
		String[] NonLoginApi = { "/mission1/hello", "/mission2/compute", "/mission3/register", "/mission5/login" };
		boolean UrlNoLogin = false; // true:免登入也能使用
		for (String value : NonLoginApi) {
			if (Url.equals(value)) {
				UrlNoLogin = true;
				break;
			}
		}

		boolean login = commonUtil.chkLogin(userData);
		if (login) {
			userId = (String) userData.get("ID");
		} else {
			userId = "NonLogin";
		}

		// DB內有此URL才會去塞(代表URL真的存在或開放)
		Map<String, Object> ApiData = commonService.queryApiData(Url);
		if (ApiData != null) {
			commonService.insertApiCourse(ApiData, userId);
		}

		// 沒登入 且 為非可進入頁面 直接回傳錯誤
		if (!login && !UrlNoLogin) {
			Map<String, Object> errorMap = new HashMap<>();
			errorMap.put("NotLogin", true);
			errorMap.put("login", login);
			errorMap.put("Msg", commonMsg.NONLOGIN_MSG);
			String json = mapper.writeValueAsString(errorMap);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().print(json);
			checkIn = false;
		}

		return checkIn;
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
