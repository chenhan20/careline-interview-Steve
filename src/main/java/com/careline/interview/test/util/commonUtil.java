package com.careline.interview.test.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class commonUtil {
	
	public static String genId() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		int random = (int) (Math.random()*100);
		String formatStr =formatter.format(new Date()) + Integer.toString(random);
		return formatStr;
	}
	
}
