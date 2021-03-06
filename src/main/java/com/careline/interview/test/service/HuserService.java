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

	// 根據信箱查詢USER
	public Map<String, Object> queryUser(String email);

	// 新增USER
	public void InsertUser(HUser huser);

	// 檢核是否有重複信箱
	public String chkEmail(String email);

	// 變更USER名稱
	public boolean changeProfile(HUser huser, String RecordType);

	// 變更密碼
	public boolean changePassword(HUser huser, String RecordType);

	// 上傳圖片
	public void uploadImage(HUser huser, String path, String url) throws Exception;

	// 取得圖片
	public Map<String, Object> getPicture(HUser huser);

	// 查詢USER的興趣
	public List<Map<String, Object>> queryUserInterests(String userId);

	// 重置USER的興趣
	public void deleteInterests(String userId);

	// 新增USER的興趣
	public void insertInterests(String interestsCode, String userId);

	// 更新USER資料
	public void updateUser(HUser huser, String RecordType, String path, String url, Map<String, Object> map);
}
