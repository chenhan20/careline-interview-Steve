package com.careline.interview.test.mission5;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.careline.interview.test.model.HUser;
import com.careline.interview.test.service.HuserService;

@RestController
public class Mission5Controller {

	
	@Autowired
	HuserService huserSerivce;
	
	@RequestMapping("/mission5/login")
	@ResponseBody
	public Map<String,Object> login(HttpServletRequest request,HUser model) {
		Map<String ,Object> map = new HashMap<>();
		//確認帳密資訊
		Map<String ,Object> userMap = huserSerivce.queryUser(model);
		request.getSession().setAttribute("USERNAME", userMap.get);
		map.put("userMap", userMap);
		return map;
	}
	
}
