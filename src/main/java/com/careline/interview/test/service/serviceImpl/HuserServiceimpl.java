package com.careline.interview.test.service.serviceImpl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	public List<HUser> queryUser() {
		return null;
	}

	@Override
	@Transactional
	public void InsertUser(HUser huser) {
		StringBuffer sql = new StringBuffer();
		Map<Object,Object> params = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		int random = (int) (Math.random()*100);
		String formatStr =formatter.format(new Date()) + Integer.toString(random);

		try {
			db.getDataSource().getConnection().setAutoCommit(true);
			sql.append("INSERT INTO H_USER ");
			sql.append(" ( ID,email,name,password ) ");
			sql.append(" VALUES  ");
			sql.append(" ('" + formatStr +"', '" + huser.getEmail().toString() +"','" + huser.getName() + "','" + huser.getPassword()+ "')");
			db.update(sql.toString());
			System.out.println("db success");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
