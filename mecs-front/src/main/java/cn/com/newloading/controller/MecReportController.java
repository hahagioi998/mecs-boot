package cn.com.newloading.controller;

import javax.servlet.http.HttpServletRequest;

import cn.com.newloading.service.MecReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

/**
 * 体检报告打印
 */
@Controller
public class MecReportController {

	@Autowired
	private MecReportService mecReportService;
	
	@RequestMapping("/getMecRecord.action")
	@ResponseBody
	public JSONObject getMecRecord(String cardNum,String mecCode,HttpServletRequest req) {
		JSONObject obj = new JSONObject();
		
		//将存入session里面的String取出转换成Admin对象
		  String cardN = (String) req.getSession().getAttribute("cardNum");
		if(cardNum != null && !"".equals(cardNum)) {
			if(!(cardN.equals(cardNum))) {
				obj.put("res", "cardNumErr");//输入的卡号和登录的卡号不一致
				return obj;
			}
		}
		if(cardN != null && !"".equals(cardN)) {
			obj = mecReportService.getMecRecord(cardN,mecCode);
		}else {
			obj.put("res", "isNull");
		}
		return obj;
	}
}
