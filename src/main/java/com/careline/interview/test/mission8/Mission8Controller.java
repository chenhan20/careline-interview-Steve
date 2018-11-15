package com.careline.interview.test.mission8;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.careline.interview.test.model.HUser;
import com.careline.interview.test.service.HuserService;
import com.careline.interview.test.util.commonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Mission8Controller {

	@Autowired
	HuserService huserSerivce;

	@RequestMapping("/mission8/saveInterest")
	@ResponseBody
	public Map<String, Object> saveInterest(HttpServletRequest request, HUser model) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> userData = (Map<String, Object>) request.getSession().getAttribute("LoginUser");
		boolean login = commonUtil.chkLogin(userData);

		if (login) {
			map.put("login", login);
		} else {

		}
		return map;
	}

	@RequestMapping("/mission8/getInterest")
	@ResponseBody
	public Map<String, Object> getInterest(HttpServletRequest request, HUser model) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> userData = (Map<String, Object>) request.getSession().getAttribute("LoginUser");
		List<Map<String, Object>> interestList = null;

		boolean login = commonUtil.chkLogin(userData);

		map.put("login", login);
		if (login) {
			interestList = huserSerivce.queryUserInterests((String) userData.get("ID"));
			map.put("interestList", interestList);
		} else {

		}
		map.put("success", true);
		return map;
	}

}
