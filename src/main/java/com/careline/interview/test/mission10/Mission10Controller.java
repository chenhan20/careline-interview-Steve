package com.careline.interview.test.mission10;

import javax.servlet.http.HttpServletRequest;

import com.careline.interview.test.service.CommonService;
import com.careline.interview.test.service.HuserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Mission10Controller {
	@Autowired
	HuserService huserSerivce;
	@Autowired
	CommonService commonService;

	/**
	 * 方法 1. 放在db 但這樣會是存 從建立到現在的次數 方法2. 想辦法抓出 一個SERVER RUN起來就不會被重置的值 固定加他
	 * 就可以算出SERVER RUN起來到現在共被呼叫幾次
	 * 
	 * 問題: 同時執行的太多支時間太快會導致回傳的數字有些問題 但資料庫資料是正確的 之後解決
	 * 
	 * @return
	 */

	@RequestMapping("/mission10/call")
	@ResponseBody
	public int call(HttpServletRequest request) {
		String Url = request.getRequestURI();
		int num = commonService.getApiCount(Url);
		return num;
	}
}
