package com.careline.interview.test.service.serviceImpl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.careline.interview.test.model.HUser;
import com.careline.interview.test.service.HuserService;
import com.careline.interview.test.util.commonMsg;
import com.careline.interview.test.util.commonUtil;
import com.careline.interview.test.util.keyUtil;

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
	public String chkEmail(String email) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select ID from H_USER where EMAIL = '" + email + "'");
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
		sql.append(
				" select a.ID,a.EMAIL,a.NAME,(b.IMG_URL || b.ATTACH_NAME) IMGURL from H_USER a LEFT JOIN REF_IMAGE b ");
		sql.append(" ON a.ID=b.USER_ID ");
		sql.append(" WHERE  a.EMAIL='" + huser.getEmail() + "' ");
		sql.append(" AND a.PASSWORD = '" + huser.getPassword() + "' ");

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
	public Map<String, Object> queryUser(String email) {
		StringBuffer sql = new StringBuffer();
		Map<Object, Object> params = new HashMap<>();
		sql.append(" select a.ID,a.EMAIL,a.NAME,(b.IMG_URL || b.ATTACH_NAME) IMGURL ");
		sql.append(" from H_USER a LEFT JOIN REF_IMAGE b ");
		sql.append(" ON a.ID=b.USER_ID ");
		sql.append(" WHERE a.EMAIL='" + email + "'");
		Map<String, Object> userData = db.queryForMap(sql.toString());
		// 若查無資料回傳null 但感覺應會有更好的做法
		return userData;
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
		sql.append(" UPDATE H_USER set PASSWORD= '" + huser.getNewPassword() + "' where ID = '" + huser.getId() + "'");
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
			deleteImg(huser.getId());
			uploadImg(huser, url);
		}
	}

	private void record(HUser huser, String RecordType) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT　INTO　USER_RECORD　");
		sql.append(" (USER_ID,CHANGETIME,CHANGEDETAIL)");
		sql.append(" VALUES  ");
		sql.append(" ('" + huser.getId() + "','" + commonUtil.genTime("Time") + "','" + RecordType + "') ");
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

	private void deleteImg(String userId) {
		StringBuffer sql = new StringBuffer();

		sql.append(" DELETE　REF_IMAGE　");
		sql.append(" WHERE USER_ID = '" + userId + "'");
		db.update(sql.toString());

	}

	@Override
	public Map<String, Object> getPicture(HUser huser) {
		StringBuffer sql = new StringBuffer();
		Map<Object, Object> params = new HashMap<>();
		sql.append(" select a.*,(a.IMG_URL || a.ATTACH_NAME)  IMGURL from REF_IMAGE a ");
		sql.append(" WHERE a.USER_ID='" + huser.getId() + "'");
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
		// select a.INTERESTS_CODE key,DECODE(b.INTERESTS_CODE,null,false,true)
		// isChecked from INTERESTS a LEFT JOIN USER_INTERESTS b ON a.INTERESTS_CODE =
		// b.INTERESTS_CODE AND b.USER_ID = :userId ;

		sql.append(" select a.INTERESTS_CODE key, ");
		sql.append(" DECODE(b.INTERESTS_CODE,null,false,true) isChecked ");
		sql.append(" from INTERESTS a LEFT JOIN USER_INTERESTS b ");
		sql.append(" ON a.INTERESTS_CODE = b.INTERESTS_CODE ");
		sql.append(" AND b.USER_ID = '" + userId + "' ");

		List<Map<String, Object>> userInterestsList = db.queryForList(sql.toString());
		return userInterestsList;
	}

	@Override
	public void insertInterests(String interestsCode, String userId) {
		StringBuffer sql = new StringBuffer();
		String id = commonUtil.genId();
		sql.append("INSERT　INTO　USER_INTERESTS　");
		sql.append(" (ID,USER_ID,INTERESTS_CODE) ");
		sql.append(" VALUES  ");
		sql.append(" ('" + id + "','" + userId + "','" + interestsCode + "' ) ");
		db.update(sql.toString());

	}

	@Override
	public void deleteInterests(String userId) {
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE　USER_INTERESTS　");
		sql.append(" where USER_ID = '" + userId + "' ");
		db.update(sql.toString());
	}

	@Override
	@Transactional
	public void updateUser(HUser huser, String RecordType, String path, String url, Map<String, Object> map) {
		StringBuffer sql = new StringBuffer();
		int updateNum = 0;
		if (!huser.getNewEmailConfirm().equals(huser.getEmail())) {
			// 確認此EMAIL還沒人使用過
			if (StringUtils.isNullOrEmpty(chkEmail(huser.getNewEmailConfirm()))) {
				sql.append(" UPDATE H_USER set EMAIL= '" + huser.getNewEmailConfirm() + "'");
				sql.append(" where ID = '" + huser.getId() + "'");
				updateNum += db.update(sql.toString());
				sql = new StringBuffer();
			} else {
				map.put("Msg", commonMsg.EMAIL_REPEAT_MSG);
				return; // 若信箱重複直接回傳了不繼續執行
			}
		}

		sql.append(" UPDATE H_USER set NAME= '" + huser.getName() + "' ");
		sql.append(" where ID = '" + huser.getId() + "'");
		updateNum += db.update(sql.toString());

		// 更新照片
		try {
			uploadImage(huser, path, url);
			map.put("IMGURL", url + huser.getPicture().getOriginalFilename());
		} catch (Exception e) {
			map.put("Msg", e.getMessage());
			e.printStackTrace();
			return;
		}

		if (updateNum > 0) {
			record(huser, RecordType);
		} else {
			map.put("Msg", commonMsg.UPDATE_ERROR_MSG);
		}
	}

}
