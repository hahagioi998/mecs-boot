package cn.com.newloading.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.bean.Log;
import cn.com.newloading.service.FrontLogService;
import cn.com.newloading.utils.LimitUtil;

@Controller
public class FrontLogController {
	
	@Autowired
	private FrontLogService frontLogService;
	
	@RequestMapping("/showFrontLog.action")
	@ResponseBody
	public JSONObject showFrontLog(String beginTime,String endTime,String userName,String nowPage) {
		ArrayList<Log> frontList = frontLogService.getLog(beginTime, endTime, userName);
		Integer count = frontLogService.getAllCount(beginTime, endTime, userName);
		JSONObject obj = new JSONObject();
			obj.put("frontList", frontList);
			obj.put("count", count);
			obj.put("limit", LimitUtil.LOGLIMIT);
			return obj;
		
	}
	
	@RequestMapping("/delFrontLog.action")
	@ResponseBody
	public JSONObject delFrontLog(String logIdJson) {
		
		List<String> logIdList = JSONObject.parseArray(logIdJson, String.class);
		boolean bo = frontLogService.delLog(logIdList);
		String res=null;
		if(bo) {
			res = "suc";
		}else {
			res = "fals";
		}
		JSONObject obj = new JSONObject();
			obj.put("res", res);
			return obj;
	}
	
	
}
