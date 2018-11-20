package com.careline.interview.test.mission6;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.careline.interview.test.model.HUser;
import com.careline.interview.test.service.HuserService;
import com.careline.interview.test.util.commonMsg;
import com.careline.interview.test.util.commonUtil;

@RestController
public class Mission6Controller {
	@Autowired
	HuserService huserSerivce;

	@RequestMapping("/mission6/updateProfile")
	@ResponseBody
	public Map<String, Object> updateProfile(HttpServletRequest request, HUser model) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> userData = (Map<String, Object>) request.getSession().getAttribute("LoginUser");
		boolean login = commonUtil.chkLogin(userData);
		if (login) {
			model.setId((String) userData.get("ID"));
			if (huserSerivce.changeProfile(model, commonMsg.CHANGE_TYPE_NAME)) {
				map.put("success", "變更成功");
			} else {
				map.put("ErrorMsg", "變更失敗");
			}
		} else {
			map.put("ErrorMsg", commonMsg.NONLOGIN_MSG);
		}
		map.put("login", login);
		return map;
	}

	@RequestMapping("/mission6/updatePassword")
	@ResponseBody
	public Map<String, Object> updatePassword(HttpServletRequest request, HUser model) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> userData = (Map<String, Object>) request.getSession().getAttribute("LoginUser");
		if (commonUtil.chkLogin(userData)) {
			model.setId((String) userData.get("ID"));
			if (huserSerivce.changePassword(model, commonMsg.CHANGE_TYPE_PASSWORD)) {
				map.put("success", "變更成功");
			} else {
				map.put("errorMsg", "變更失敗，舊密碼錯誤");
			}
			;
		} else {
			map.put("errorMsg", commonMsg.NONLOGIN_MSG);
		}
		return map;
	}

}
