package com.careline.interview.test.service.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.careline.interview.test.model.HUser;
import com.careline.interview.test.service.HuserService;



@Service
public class HuserServiceimpl implements HuserService{
	@Autowired
	private JdbcTemplate db;

	@Override
	public List<Map<String, Object>> queryUser() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select email,name from H_USER ");
		
		List<Map<String, Object>> userList = db.queryForList(sql.toString());
		return userList;
	}

	@Override
	@Transactional
	public void InsertUser(HUser huser) {
		StringBuffer sql = new StringBuffer();
		Map<Object,Object> params = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		int random = (int) (Math.random()*100);
		String formatStr =formatter.format(new Date()) + Integer.toString(random);

		sql.append("INSERT INTO H_USER ");
		sql.append(" ( ID,email,name,password ) ");
		sql.append(" VALUES  ");
		sql.append(" ('" + formatStr +"', '" + huser.getEmail().toString() +"','" + huser.getName() + "','" + huser.getPassword()+ "')");
		db.update(sql.toString());
		huser.setId(formatStr);
		System.out.println("db success");
	}

	@Override
	public String chkEmail(HUser huser) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select ID from H_USER where EMAIL = '" + huser.getEmail() +"'");
		List<Map<String, Object>> object = db.queryForList(sql.toString());
		if(object.size()==0) {
			return null;
		}else {
			return (String) object.get(0).get("ID");
		}
	}

	@Override
	public Map<String, Object> queryUser(HUser huser) {
		StringBuffer sql = new StringBuffer();
		Map<Object,Object> params = new HashMap<>();
		sql.append(" select * from H_USER WHERE EMAIL='" + huser.getEmail() +"' AND PASSWORD = '"+ huser.getPassword() +"'");
		params.put("email", huser.getEmail());
		params.put("password", huser.getPassword());
		List<Map<String, Object>> userData = db.queryForList(sql.toString());
		// 若查無資料回傳null 但感覺應會有更好的做法
		if(userData.size()==1) {
			return userData.get(0);
		}else {
			return null;
		}
	}

	@Override
	public boolean changeProfile(HUser huser,String RecordType) {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE H_USER set NAME= '" + huser.getName() + "' where EMAIL = '" + huser.getEmail() +"'");
		int updateNum = db.update(sql.toString());
		if(updateNum>0) {
			record(huser,RecordType);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean changePassword(HUser huser,String RecordType) {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE H_USER set PASSWORD= '" + huser.getNewPassword() + "' where EMAIL = '" + huser.getEmail() +"'");
		sql.append(" AND PASSWORD = '"+ huser.getOldPassword() + "'");
		int updateNum = db.update(sql.toString());
		if(updateNum>0) {
			record(huser,RecordType);
			return true;
		}else {
			return false;
		}
	}
	
	
	private void record(HUser huser,String RecordType) {
		StringBuffer sql = new StringBuffer();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/ddHH:mm:ss");
		String formatStr =formatter.format(new Date());

		sql.append("INSERT　INTO　USER_RECORD　");
		sql.append(" (USER_ID,CHANGETIME,CHANGEDETAIL)");
		sql.append(" VALUES  ");
		sql.append(" ('"+ huser.getId() +"','"+ formatStr +"','"+ RecordType +"') ");
		db.update(sql.toString());
		
	}
	
	
}
