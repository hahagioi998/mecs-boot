package cn.com.newloading.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.bean.Admin;
import cn.com.newloading.service.ItemAlterService;

@Controller
public class ItemAlterController {

	@Autowired
	private ItemAlterService itemAlterService;

	@RequestMapping("/itemAlter.action")
	@ResponseBody
	public JSONObject itemAlter(String itemName, String itemId, HttpServletRequest request) {

		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("sessionAdminKey");
		String adminId = admin.getAdminId();

		JSONObject item = itemAlterService.alterItem(adminId,itemName, itemId);

		return item;
	}

}
