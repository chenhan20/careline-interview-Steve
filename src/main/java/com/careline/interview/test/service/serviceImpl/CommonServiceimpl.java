package com.careline.interview.test.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.careline.interview.test.service.CommonService;
import com.careline.interview.test.util.commonUtil;

@Service
public class CommonServiceimpl implements CommonService {
	@Autowired
	private JdbcTemplate db;

	@Override
	public void insertApiCourse(Map<String, Object> ApiData, String userId) {
		StringBuffer sql = new StringBuffer();
		String id = commonUtil.genId();
		String time = commonUtil.genTime("Time");
		String apiUrl = (String) ApiData.get("API_URL");
		String apiCode = (String) ApiData.get("API_CODE");
		sql.append("INSERT　INTO　API_COURSE　");
		sql.append(" (ID,API_URL,API_CODE,CALL_USER,CALL_TIME) ");
		sql.append(" VALUES  ");
		sql.append(" ('" + id + "','" + apiUrl + "', '" + apiCode + "','" + userId + "' , '" + time + "' ) ");
		db.update(sql.toString());

	}

	@Override
	public Map<String, Object> queryApiData(String apiUrl) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * FROM API_FUNCTION WHERE API_URL='" + apiUrl + "' AND USE_OPEN = 'Y' ");
		List<Map<String, Object>> apiData = db.queryForList(sql.toString());
		// 若查無資料回傳null 但感覺應會有更好的做法
		if (apiData.size() == 1) {
			return apiData.get(0);
		} else {
			return null;
		}
	}

	@Override
	public int getApiCount(String Url) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select COUNT(*) COUNT FROM API_COURSE where API_URL = '" + Url + "' ");
		Map<String, Object> apiCourse = db.queryForMap(sql.toString());
		Object count = apiCourse.get("COUNT");
		return Integer.parseInt(count.toString());
	}

}
