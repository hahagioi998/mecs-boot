package cn.com.newloading.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.bean.Admin;
import cn.com.newloading.service.PrintOrderService;
import cn.com.newloading.utils.IsNullUtil;

/**
 * 打印导检单和条码
 */
@Controller
public class PrintOrderController {

	@Autowired
	private PrintOrderService printOrderService;
	
	/**
	 * 根据卡号获取体检记录信息和用户信息
	 * @param cardNum
	 * @return
	 */
	@RequestMapping("/getMecRecordByCardNum.action")
	@ResponseBody
	public JSONObject getMecRecordByCardNum(String cardNum,HttpServletRequest req) {
		JSONObject obj = new JSONObject();
		/*
		 * 卡号非空判断
		 */
		List<String> isNullList = new ArrayList<String>();
		isNullList.add(cardNum);
		boolean notNull = IsNullUtil.isNull(isNullList);
		Admin admin = (Admin) req.getSession().getAttribute("sessionAdminKey");
		if(admin == null){//说明没有登录或者失效
			obj.put("res", "toLogin");
		}else if(notNull) {
			obj = printOrderService.getMecRecordByCardNum(cardNum);
		}else {
			obj.put("res", "isNull");
		}
		return obj;
	}
	
}
