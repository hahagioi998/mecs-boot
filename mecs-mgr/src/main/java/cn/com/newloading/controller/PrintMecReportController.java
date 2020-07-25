package cn.com.newloading.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.bean.Admin;
import cn.com.newloading.bean.ItemsRecord;
import cn.com.newloading.bean.MecRecord;
import cn.com.newloading.bean.User;
import cn.com.newloading.service.PrintMecReportService;
import cn.com.newloading.utils.IsNullUtil;

/**
 * 体检报告信息打印获取
 */
@Controller
public class PrintMecReportController {

	@Autowired
	private PrintMecReportService printMecReportService;
	
	@RequestMapping("/getPrintMecReportInfo.action")
	@ResponseBody
	public JSONObject getPrintMecReport(HttpServletRequest req) {
		JSONObject obj = new JSONObject();
		String mrNum = (String) req.getSession().getAttribute("mrNum");
		List<String> isNullList = new ArrayList<String>();
		isNullList.add(mrNum);
		boolean notNull = IsNullUtil.isNull(isNullList);
		Admin admin = (Admin) req.getSession().getAttribute("sessionAdminKey");
		if(notNull) {
			obj = printMecReportService.getPrintMecReport(mrNum);
		}else {
			obj.put("res", "isNull");
		}
		obj.put("mrNum", mrNum);
		return obj;
	}
	
	@RequestMapping("/printMecReport.action")
	public ModelAndView getPrintMecReportInfo(String mrNum,HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();//返回jsp
		JSONObject obj = new JSONObject();
		List<String> isNullList = new ArrayList<String>();
		isNullList.add(mrNum);
		boolean notNull = IsNullUtil.isNull(isNullList);
		Admin admin = (Admin) req.getSession().getAttribute("sessionAdminKey");
		if(notNull) {
			obj = printMecReportService.getPrintMecReportInfo(mrNum,admin);
			User u = (User) obj.get("user");
			MecRecord mecRecord = (MecRecord) obj.get("mecRecord");
			List<ItemsRecord> itemsList = (List<ItemsRecord>) obj.get("itemsList");
			mv.addObject("mrNum", mrNum);
			mv.addObject("user", u);
			mv.addObject("mecRecord", mecRecord);
			mv.addObject("itemsList", itemsList);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			mv.addObject("printTime", df.format(new Date()));
			mv.setViewName("printMecReport");
		}else {
			obj.put("res", "isNull");
		}
		return mv;
	}
	
}
