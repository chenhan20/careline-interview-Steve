package com.careline.interview.test.mission3;

import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.careline.interview.test.model.HUser;
import com.careline.interview.test.service.HuserService;

@RestController
public class Mission3Controller {

	@Autowired
	HuserService huserSerivce;

	@RequestMapping("/mission3/register")
	@ResponseBody
	public Map<String, Object> register(HUser model) {
		Map<String, Object> map = new HashMap<>();
		try {
			chkRegisterData(model);
		} catch (Exception e) {
			map.put("msg", e.getMessage());
			return map;
		}
		if (org.h2.util.StringUtils.isNullOrEmpty(huserSerivce.chkEmail(model.getEmail()))) {
			huserSerivce.InsertUser(model);
			map.put("memberId", model.getId() + "，恭喜註冊成功囉!");
		} else {
			map.put("msg", model.getEmail() + "，此Email有人用過囉!");
		}
		return map;
	}

	private void chkRegisterData(HUser huser) throws Exception {
		if (huser.getName().isEmpty()) {
			throw new Exception("名稱不得為空");
		}
		if (huser.getPassword().isEmpty()) {
			throw new Exception("密碼不得為空");
		}
		if (huser.getEmail().isEmpty()) {
			throw new Exception("信箱不得為空");
		}
	}

}
