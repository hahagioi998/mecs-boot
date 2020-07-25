package cn.com.newloading.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.bean.Admin;
import cn.com.newloading.service.CardDeleteService;

@Controller
public class CardDeleteController {

	@Autowired
	private CardDeleteService cardDeleteService;

	@RequestMapping("/delCard.action")
	@ResponseBody
	public JSONObject delCard(String cardId, String cardNum, HttpServletRequest request) {

		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("sessionAdminKey");
		String adminId = admin.getAdminId();

		JSONObject delCard = cardDeleteService.delCard(cardId, cardNum, adminId);
		return delCard;
	}

}
