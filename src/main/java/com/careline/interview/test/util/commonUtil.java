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

	// 待補規則 先這樣判斷
	public static boolean chkLogin(Map<String, Object> loginUser) {
		if (loginUser == null) {
			return false;
		} else {
			return true;
		}
	}
}
