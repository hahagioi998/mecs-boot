package cn.com.newloading.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.service.DictService;

@Controller
public class DictController {

	@Autowired
	private DictService dictService;

	@RequestMapping("/dictPrefix.action")
	@ResponseBody
	public JSONObject dictPrefix(String cardId) {

		JSONObject cardPrefix = dictService.getCardPrefix();
		return cardPrefix;
	}

}
