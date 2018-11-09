package com.careline.interview.test.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.careline.interview.test.model.HUser;

@Service
public interface HuserService {
	
	public List<Map<String, Object>> queryUser();
	public void InsertUser(HUser huser);
	public boolean chkEmail(HUser huser);
}
