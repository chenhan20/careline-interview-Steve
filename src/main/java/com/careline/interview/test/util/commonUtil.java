package com.careline.interview.test.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class commonUtil {

	public static String genId() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		int random = (int) (Math.random() * 100);
		String formatStr = formatter.format(new Date()) + Integer.toString(random);
		return formatStr;
	}

	public static String genTime(String timeType) {
		SimpleDateFormat formatter = null;
		switch (timeType) {
		case "Time":
			formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			break;
		case "Date":
			formatter = new SimpleDateFormat("yyyy/MM/dd");
			break;
		default:
		}
		return formatter.format(new Date());
	}

	// 待補規則 先這樣判斷
	public static boolean chkLogin(Map<String, Object> loginUser) {
		if (loginUser == null) {
			return false;
		} else {
			return true;
		}
	}
}
