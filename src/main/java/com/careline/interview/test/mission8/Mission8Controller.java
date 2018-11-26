package com.careline.interview.test.mission8;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.careline.interview.test.model.HUser;
import com.careline.interview.test.service.HuserService;
import com.careline.interview.test.util.commonUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Mission8Controller {

	@Autowired
	HuserService huserSerivce;

	public final ObjectMapper mapper = new ObjectMapper();

	@RequestMapping("/mission8/saveInterest")
	@ResponseBody
	public Map<String, Object> saveInterest(HttpServletRequest request, String json) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> userData = (Map<String, Object>) request.getSession().getAttribute("LoginUser");
		List<Map<String, Object>> interestList = null;
		try {
			List<Map<String, Object>> myObjects = mapper.readValue(json,
					new TypeReference<List<Map<String, Object>>>() {
					});
			huserSerivce.deleteInterests((String) userData.get("ID"));// 放這裡其實不太合理 如果再新增興趣有問題時 這個會先被commit 表示沒做到交易
			for (Map<String, Object> valueMap : myObjects) {
				if ((boolean) valueMap.get("isChecked")) {
					huserSerivce.insertInterests((String) valueMap.get("key"), (String) userData.get("ID"));
				}
			}
			interestList = huserSerivce.queryUserInterests((String) userData.get("ID"));
			map.put("interestList", interestList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	@RequestMapping("/mission8/getInterest")
	@ResponseBody
	public Map<String, Object> getInterest(HttpServletRequest request, HUser model) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> userData = (Map<String, Object>) request.getSession().getAttribute("LoginUser");
		List<Map<String, Object>> interestList = null;

		interestList = huserSerivce.queryUserInterests((String) userData.get("ID"));
		map.put("interestList", interestList);
		map.put("success", true);
		return map;
	}

}
