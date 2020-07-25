package cn.com.newloading.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.service.CardQueryService;
@Controller
public class CardQueryController {

	@Autowired
	private CardQueryService cardQueryService;

	@RequestMapping("/queryCard.action")
	@ResponseBody
	public JSONObject queryCard(String nowPage, String cardNumber, String userName, String cardState, String startTime,
			String endTime) {

		JSONObject cardQuery = cardQueryService.cardQuery(nowPage, cardNumber, userName, cardState, startTime,
				endTime);
		return cardQuery;

	}

}
