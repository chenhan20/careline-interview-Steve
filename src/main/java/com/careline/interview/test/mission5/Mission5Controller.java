package com.careline.interview.test.mission5;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;import org.springframework.web.multipart.support.RequestPartServletServerHttpRequest;

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
		if(request.getSession().getAttribute("LoginName")==null){
			//確認帳密資訊
			Map<String ,Object> userMap = huserSerivce.queryUser(model);
			if(userMap!=null) {
				map.put("successMsg", "登入成功" + userMap.get("NAME"));
//				map.put("userMap", userMap);
				request.getSession().setAttribute("LoginName", userMap.get("NAME"));
			}else {
				map.put("errorMsg", "EMAIL或是密碼錯誤!");
			}
		}else {
			map.put("Msg", "你已經登入了");
		}
		return map;
		
	}
	
	@RequestMapping("/mission5/logout")
	@ResponseBody
	public Map<String,Object> logout(HttpServletRequest request,HUser model) {
		Map<String ,Object> map = new HashMap<>();
		if(request.getSession().getAttribute("LoginName")!=null){
			request.getSession().removeAttribute("LoginName");
			map.put("Msg", "登出成功");
		}else {
			map.put("Msg", "你沒有登入!");
		}
		return map;
		
	}
	

	
}
