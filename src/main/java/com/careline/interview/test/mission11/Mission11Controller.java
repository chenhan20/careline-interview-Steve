package com.careline.interview.test.mission11;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.careline.interview.test.service.CommonService;
import com.careline.interview.test.service.HuserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Mission11Controller {

    @Autowired
    HuserService huserSerivce;
    @Autowired
    CommonService commonService;

    @RequestMapping("/mission11/getMembers")
    @ResponseBody
    public Map<String, Object> getMembers(String email) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> user = huserSerivce.queryUser(email);
        map.put("member", user);
        return map;
    }

    @RequestMapping("/mission11/getSession")
    @ResponseBody
    public Map<String, Object> getSession(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> userData = (Map<String, Object>) request.getSession().getAttribute("LoginUser");
        map.put("userData", userData);
        return map;
    }

}
