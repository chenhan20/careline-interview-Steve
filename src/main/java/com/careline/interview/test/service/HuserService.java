package com.careline.interview.test.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.careline.interview.test.model.HUser;

@Service
public interface HuserService {
	// 查詢所有USER
	public List<Map<String, Object>> queryUser();

	// 根據條件查詢USER
	public Map<String, Object> queryUser(HUser huser);

	// 新增USER
	public void InsertUser(HUser huser);

	// 檢核是否有
	public String chkEmail(HUser huser);

	// 變更USER名稱
	public boolean changeProfile(HUser huser, String RecordType);

	// 變更密碼
	public boolean changePassword(HUser huser, String RecordType);

	// 上傳圖片
	public void uploadImage(HUser huser, String path, String url) throws Exception;

	// 取得圖片
	public Map<String, Object> getPicture(HUser huser);
}
