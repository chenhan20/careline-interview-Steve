package com.careline.interview.test.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.careline.interview.test.model.HUser;

@Service
public interface HuserService {
	
	public List<Map<String, Object>> queryUser();
	public Map<String, Object> queryUser(HUser huser);
	public void InsertUser(HUser huser);
	public String chkEmail(HUser huser);
	public boolean changeProfile(HUser huser,String RecordType);
	public boolean changePassword(HUser huser,String RecordType);
	
	
}
