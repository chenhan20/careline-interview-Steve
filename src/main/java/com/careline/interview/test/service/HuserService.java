package com.careline.interview.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.careline.interview.test.model.HUser;

@Service
public interface HuserService {
	
	public List<HUser> queryUser();
	public void InsertUser(HUser huser);
}
