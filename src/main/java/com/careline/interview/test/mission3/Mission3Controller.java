package com.careline.interview.test.mission3;

import java.util.HashMap;
import java.util.Map;

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
	public Map<String,Object> register(HUser model) {
		Map<String ,Object> map = new HashMap<>();
		if(huserSerivce.chkEmail(model)) {
			huserSerivce.InsertUser(model);
			map.put("memberId", model.getId() +"，恭喜註冊成功囉!");
		}else {
			map.put("msg", model.getEmail() +"，此Email有人用過囉!");
		}
		return map;
	}
	
}
