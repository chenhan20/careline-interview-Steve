package com.careline.interview.test.mission7;

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
public class Mission7Controller {

	@Autowired
	HuserService huserSerivce;

	@RequestMapping("/mission7/uploadPicture")
	@ResponseBody
	public Map<String, Object> uploadPicture(HttpServletRequest request, HUser model) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> userData = (Map<String, Object>) request.getSession().getAttribute("LoginUser");
		boolean login = commonUtil.chkLogin(userData);

		if (login) {
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
		} else {
			map.put("ErrorMsg", commonMsg.NONLOGIN_MSG);
		}
		map.put("login", login);
		return map;
	}

	@RequestMapping("/mission7/getPicture")
	@ResponseBody
	public Map<String, Object> getPicture(HttpServletRequest request, HUser model) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> userData = (Map<String, Object>) request.getSession().getAttribute("LoginUser");
		boolean login = commonUtil.chkLogin(userData);

		if (login) {
			model.setId((String) userData.get("ID"));
			Map<String, Object> imgMap = huserSerivce.getPicture(model);
			String imageUrl = null;
			String imageName = null;
			System.out.println(imgMap);
			if (imgMap != null) {
				imageUrl = (String) imgMap.get("IMG_URL");
				imageName = (String) imgMap.get("ATTACH_NAME");
			}
			map.put("imageUrl", imageUrl + imageName);
			map.put("success", true);

		} else {
			map.put("ErrorMsg", commonMsg.NONLOGIN_MSG);
		}
		map.put("login", login);

		return map;
	}
}
