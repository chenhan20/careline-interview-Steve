package com.careline.interview.test.mission6;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.careline.interview.test.model.HUser;
import com.careline.interview.test.service.HuserService;
import com.careline.interview.test.util.commonMsg;
import com.careline.interview.test.util.commonUtil;
import com.careline.interview.test.util.keyUtil;

@RestController
public class Mission6Controller {
	@Autowired
	HuserService huserSerivce;

	@Value("${steve.key}")
	private String key;

	@RequestMapping("/mission6/updateProfile")
	@ResponseBody
	public Map<String, Object> updateProfile(HttpServletRequest request, HUser model) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> userData = (Map<String, Object>) request.getSession().getAttribute("LoginUser");
		model.setId((String) userData.get("ID"));
		if (huserSerivce.changeProfile(model, commonMsg.CHANGE_TYPE_NAME)) {
			map.put("success", "變更成功");
		} else {
			map.put("ErrorMsg", "變更失敗");
		}
		return map;
	}

	@RequestMapping("/mission6/updatePassword")
	@ResponseBody
	public Map<String, Object> updatePassword(HttpServletRequest request, HUser model) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> userData = (Map<String, Object>) request.getSession().getAttribute("LoginUser");
		model.setId((String) userData.get("ID"));
		try {
			String encOldPassword = keyUtil.encrypt(model.getOldPassword(), key);
			String encNewPassword = keyUtil.encrypt(model.getNewPassword(), key);
			model.setOldPassword(encOldPassword);
			model.setNewPassword(encNewPassword);
		} catch (Exception e) {
			map.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		if (huserSerivce.changePassword(model, commonMsg.CHANGE_TYPE_PASSWORD)) {
			map.put("success", "變更成功");
		} else {
			map.put("errorMsg", "變更失敗，舊密碼錯誤");
		}
		return map;
	}

}
