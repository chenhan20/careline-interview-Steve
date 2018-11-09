package com.careline.interview.test.mission4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.careline.interview.test.model.HUser;
import com.careline.interview.test.service.HuserService;

@RestController
public class Mission4Controller {

	
	@Autowired
	HuserService huserSerivce;
	
	@RequestMapping("/mission4/getAllMembers")
	@ResponseBody
	public Map<String,Object> getAllMembers(HUser model) {
		Map<String ,Object> map = new HashMap<>();
		 List<Map<String ,Object>> userList = huserSerivce.queryUser();
		map.put("memberList", userList);
		return map;
	}
	
}
