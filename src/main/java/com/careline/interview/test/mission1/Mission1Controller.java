package com.careline.interview.test.mission1;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Mission1Controller {

	@RequestMapping("/mission1/hello")
	public String hello() throws Exception {
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        Date date = new Date();
        String Nowdate = sdFormat.format(date);
        return "Hello ! "+Nowdate;
	} 
	



}
