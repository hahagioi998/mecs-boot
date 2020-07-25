package cn.com.newloading.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.bean.Log;
import cn.com.newloading.service.MgrLogService;
import cn.com.newloading.utils.LimitUtil;
@Controller
public class MgrLogController {
	
	@Autowired
	private MgrLogService mgrLogService;
	
	@RequestMapping("/showMgrLog.action")
	@ResponseBody
	public JSONObject showMgrLog(String beginTime,String endTime,String userName,String nowPage) {
		ArrayList<Log> mgrtList = mgrLogService.getLog(beginTime, endTime, userName);
		
		Integer count = mgrLogService.getAllCount(beginTime, endTime, userName);
		JSONObject obj = new JSONObject();
			obj.put("mgrtList", mgrtList);
			obj.put("count", count);
			obj.put("limit", LimitUtil.LOGLIMIT);
			
			return obj;
		
	}
	
	@RequestMapping("/delMgrLog.action")
	@ResponseBody
	public JSONObject delMgrLog(String logIdJson) {
		
		List<String> logIdList = JSONObject.parseArray(logIdJson, String.class);
		boolean bo = mgrLogService.delLog(logIdList);
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
