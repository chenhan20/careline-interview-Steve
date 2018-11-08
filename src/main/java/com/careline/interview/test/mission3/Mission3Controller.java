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
		huserSerivce.InsertUser(model);
		
		System.out.println(model.getEmail());
		return map;
	}
	
}
