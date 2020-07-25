package cn.com.newloading.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.service.UserMsgService;

@Controller
public class UserMsgController {

	@Autowired
	private UserMsgService msgService;

	@RequestMapping("/userQuery.action")
	@ResponseBody
	public JSONObject userQuery(String nowPage, String userName, String number, String userTel, String startTime,
			String endTime) {

		JSONObject userQuery = msgService.userMsgQuery(nowPage, userName, number, userTel, startTime, endTime);

		return userQuery;
	}

}
