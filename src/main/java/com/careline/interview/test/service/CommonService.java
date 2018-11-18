package com.careline.interview.test.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface CommonService {
	// 新增USER的興趣
	public void insertApiCourse(Map<String, Object> ApiData, String userId);

	public Map<String, Object> queryApiData(String apiUrl);

	// 取得API使用次數
	public int getApiCount(String Url);
}
