package com.careline.interview.test.mission5;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.support.RequestPartServletServerHttpRequest;

import com.careline.interview.test.model.HUser;
import com.careline.interview.test.service.HuserService;
import com.careline.interview.test.util.commonUtil;

@RestController
public class Mission5Controller {

	@Autowired
	HuserService huserSerivce;

	@RequestMapping("/mission5/login")
	@ResponseBody
	public Map<String, Object> login(HttpServletRequest request, HUser model) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> userData = (Map<String, Object>) request.getSession().getAttribute("LoginUser");
		if (!commonUtil.chkLogin(userData)) {
			// 確認帳密資訊
			userData = huserSerivce.queryUser(model);
			if (userData != null) {
				map.put("successMsg", "登入成功" + userData.get("NAME"));
				map.put("userMap", userData);
				request.getSession().setAttribute("LoginUser", userData);
			} else {
				map.put("errorMsg", "EMAIL或密碼錯誤!");
			}
		} else {
			map.put("errorMsg", "你已經登入了");
		}
		return map;

	}

	@RequestMapping("/mission5/logout")
	@ResponseBody
	public Map<String, Object> logout(HttpServletRequest request, HUser model) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> userData = (Map<String, Object>) request.getSession().getAttribute("LoginUser");
		if (commonUtil.chkLogin(userData)) {
			request.getSession().removeAttribute("LoginUser");
			map.put("Msg", "登出成功");
		} else {
			map.put("Msg", "你沒有登入!");
		}
		return map;

	}

}
