package com.careline.interview.test.mission7;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.careline.interview.test.model.HUser;
import com.careline.interview.test.service.HuserService;
import com.careline.interview.test.util.commonMsg;
import com.careline.interview.test.util.commonUtil;

import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Mission7Controller {

	@Autowired
	HuserService huserSerivce;

	@RequestMapping("/mission7/uploadPicture")
	@ResponseBody
	public Map<String, Object> uploadPicture(HttpServletRequest request, HUser model) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> userData = (Map<String, Object>) request.getSession().getAttribute("LoginUser");
		model.setId((String) userData.get("ID"));
		String url = "/images/" + model.getId() + "/";
		String path = request.getServletContext().getRealPath(url);

		try {
			huserSerivce.uploadImage(model, path, url);
			map.put("url", url + model.getPicture().getOriginalFilename());
		} catch (Exception e) {
			model.setErrorMsg(e.getMessage());
			e.printStackTrace();
		}
		if (StringUtils.isNullOrEmpty(model.getErrorMsg())) {
			map.put("success", true);
		} else {
			map.put("errorMsg", model.getErrorMsg());
		}
		return map;
	}

	@RequestMapping("/mission7/getPicture")
	@ResponseBody
	public Map<String, Object> getPicture(HttpServletRequest request, HUser model) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> userData = (Map<String, Object>) request.getSession().getAttribute("LoginUser");
		model.setId((String) userData.get("ID"));
		Map<String, Object> imgMap = huserSerivce.getPicture(model);
		String imageUrl = null;
		if (imgMap != null) {
			imageUrl = (String) imgMap.get("IMGURL");
		}
		map.put("imageUrl", imageUrl);
		map.put("success", true);
		return map;
	}
}
