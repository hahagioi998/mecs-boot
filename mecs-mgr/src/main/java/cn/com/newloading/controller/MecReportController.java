package cn.com.newloading.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.service.MecReportService;

/**
 * 体检报告打印
 */
@Controller
public class MecReportController {

	@Autowired
	private MecReportService mecReportService;
	
	@RequestMapping("/getMecRecord.action")
	@ResponseBody
	public JSONObject getMecRecord(String cardNum,String mecCode) {
		JSONObject obj = new JSONObject();
		if((cardNum != null && !"".equals(cardNum)) || ((mecCode != null && !"".equals(mecCode))) ) {
			obj = mecReportService.getMecRecord(cardNum, mecCode);
		}else {
			obj.put("res", "isNull");
		}
		return obj;
	}
	
	@RequestMapping("/setMrNum.action")
	@ResponseBody
	public JSONObject setMrNum(String mrNum,HttpServletRequest request) {
		JSONObject obj = new JSONObject();
		request.getSession().setAttribute("mrNum", mrNum);
		obj.put("code", "0000");
		return obj;
	}
}
