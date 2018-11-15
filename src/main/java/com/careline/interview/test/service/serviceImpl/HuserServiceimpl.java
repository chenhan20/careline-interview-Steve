package com.careline.interview.test.service.serviceImpl;

import java.io.File;
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
import com.careline.interview.test.util.commonUtil;

@Service
public class HuserServiceimpl implements HuserService {
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
		Map<Object, Object> params = null;

		String id = commonUtil.genId();
		sql.append("INSERT INTO H_USER ");
		sql.append(" ( ID,email,name,password ) ");
		sql.append(" VALUES  ");
		sql.append(" ('" + id + "', '" + huser.getEmail().toString() + "','" + huser.getName() + "','"
				+ huser.getPassword() + "')");
		db.update(sql.toString());
		huser.setId(id);
		System.out.println("db success");
	}

	@Override
	public String chkEmail(HUser huser) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select ID from H_USER where EMAIL = '" + huser.getEmail() + "'");
		List<Map<String, Object>> object = db.queryForList(sql.toString());
		if (object.size() == 0) {
			return null;
		} else {
			return (String) object.get(0).get("ID");
		}
	}

	@Override
	public Map<String, Object> queryUser(HUser huser) {
		StringBuffer sql = new StringBuffer();
		Map<Object, Object> params = new HashMap<>();
		sql.append(" select ID,EMAIL,NAME from H_USER WHERE EMAIL='" + huser.getEmail() + "' AND PASSWORD = '"
				+ huser.getPassword() + "'");
		params.put("email", huser.getEmail());
		params.put("password", huser.getPassword());
		List<Map<String, Object>> userData = db.queryForList(sql.toString());
		// 若查無資料回傳null 但感覺應會有更好的做法
		if (userData.size() == 1) {
			return userData.get(0);
		} else {
			return null;
		}
	}

	@Override
	public boolean changeProfile(HUser huser, String RecordType) {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE H_USER set NAME= '" + huser.getName() + "' where ID = '" + huser.getId() + "'");
		int updateNum = db.update(sql.toString());
		if (updateNum > 0) {
			record(huser, RecordType);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean changePassword(HUser huser, String RecordType) {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE H_USER set PASSWORD= '" + huser.getNewPassword() + "' where EMAIL = '" + huser.getEmail()
				+ "'");
		sql.append(" AND PASSWORD = '" + huser.getOldPassword() + "'");
		int updateNum = db.update(sql.toString());
		if (updateNum > 0) {
			record(huser, RecordType);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void uploadImage(HUser huser, String path, String url) throws Exception {
		if (huser.getPicture().isEmpty()) {
			huser.setErrorMsg("無檔案上傳");
			return;
		} else {
			File saveFile = new File(path);
			if (!saveFile.exists()) {
				saveFile.mkdirs();
			}
			String RealFilePath = path + huser.getPicture().getOriginalFilename();
			huser.getPicture().transferTo(new File(RealFilePath));
			uploadImg(huser, url);
		}
	}

	private void record(HUser huser, String RecordType) {
		StringBuffer sql = new StringBuffer();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/ddHH:mm:ss");
		String formatStr = formatter.format(new Date());

		sql.append("INSERT　INTO　USER_RECORD　");
		sql.append(" (USER_ID,CHANGETIME,CHANGEDETAIL)");
		sql.append(" VALUES  ");
		sql.append(" ('" + huser.getId() + "','" + formatStr + "','" + RecordType + "') ");
		db.update(sql.toString());

	}

	private void uploadImg(HUser huser, String url) {
		StringBuffer sql = new StringBuffer();
		String id = commonUtil.genId();

		sql.append("INSERT　INTO　REF_IMAGE　");
		sql.append(" (ID,USER_ID,ATTACH_NAME,IMG_URL,IMG_SIZE)");
		sql.append(" VALUES  ");
		sql.append(" ('" + id + "','" + huser.getId() + "','" + huser.getPicture().getOriginalFilename() + "','" + url
				+ "','" + huser.getPicture().getSize() + "') ");
		db.update(sql.toString());

	}

	@Override
	public Map<String, Object> getPicture(HUser huser) {
		StringBuffer sql = new StringBuffer();
		Map<Object, Object> params = new HashMap<>();
		sql.append(" select * from REF_IMAGE WHERE USER_ID='" + huser.getId() + "'");
		List<Map<String, Object>> ImageData = db.queryForList(sql.toString());
		// 若查無資料回傳null 但感覺應會有更好的做法
		if (ImageData.size() == 1) {
			return ImageData.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> queryUserInterests(String userId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from INTERESTS ");// where USER_ID = '"+ userId + "'");
		List<Map<String, Object>> userInterestsList = db.queryForList(sql.toString());
		return userInterestsList;
	}

}
