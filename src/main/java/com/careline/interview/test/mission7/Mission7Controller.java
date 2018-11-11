package com.careline.interview.test.mission7;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.careline.interview.test.model.HUser;
import com.careline.interview.test.service.HuserService;

@RestController
public class Mission7Controller {
	
	@Autowired
	HuserService huserSerivce;
	
	@RequestMapping("/mission7/uploadPicture")
	@ResponseBody
	public Map<String,Object> uploadPicture(HttpServletRequest request,HUser model) {
		Map<String ,Object> map = new HashMap<>();
		String id = huserSerivce.chkEmail(model);

		model.setId(StringUtils.isNullOrEmpty(id)?"":id);
		if(!StringUtils.isNullOrEmpty(id)) {
			String path = request.getServletContext().getRealPath("/images/") + model.getPicture().getOriginalFilename() ;
			System.out.println(path);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            File saveFile = new File(path);
            try {
            	if (!saveFile.exists()){
            		saveFile.mkdirs();
            	}
				model.getPicture().transferTo(saveFile);
			} catch (IllegalStateException e) {
				map.put("error", e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				map.put("error", e.getMessage());
			}
		}else {
			map.put("errorMsg","無此信箱資料故無法上傳圖片!");
		}
		return map;
		
		
		
		
		


		
	}
}
