package cn.com.newloading.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.bean.Admin;
import cn.com.newloading.bean.Items;
import cn.com.newloading.bean.User;
import cn.com.newloading.service.BillingService;
import cn.com.newloading.utils.IsNullUtil;

/**
 * 开单
 */
@Controller
public class BillingController {

	@Autowired
	private BillingService billingService;
	/**
	 * 根据卡号去获取体检人信息
	 * @param cardNum
	 * @return
	 */
	@RequestMapping("/getUserInfoByCardNum.action")
	@ResponseBody
	public JSONObject getUserInfoByCardNum(String cardNum) {
		JSONObject obj = new JSONObject();
		//非空判断
		List<String> isNullList = new ArrayList<String>();
		isNullList.add(cardNum);
		boolean notNull = IsNullUtil.isNull(isNullList);
		if(notNull) {
			obj = billingService.getUserInfoByCardNum(cardNum);
		}else {
			obj.put("res", "isNull");
		}
		return obj;
	}
	
	/**
	 * 获取体检套餐
	 * @param cardNum
	 * @return
	 */
	@RequestMapping("/getAllComboInfo.action")
	@ResponseBody
	public JSONObject getAllComboInfo(String comboName,String comboState) {
		JSONObject obj = new JSONObject();
		//获取套餐集合
		obj = billingService.getAllComboInfo(comboName,comboState);
		return obj;
	}
	
	/**
	 * 根据套餐ID获取详情,包括项目,及项目里的细项
	 * @param cardNum
	 * @return
	 */
	@RequestMapping("/getDetailsByComboId.action")
	@ResponseBody
	public JSONObject getDetailsByComboId(HttpServletRequest req) {
		JSONObject obj = new JSONObject();
		String comboId = (String) req.getSession().getAttribute("comboId");
		//非空判断
		List<String> isNullList = new ArrayList<String>();
		isNullList.add(comboId);
		boolean notNull = IsNullUtil.isNull(isNullList);
		if(notNull) {
			/*
			 *调用service中的方法
			 */
			obj = billingService.getDetailsByComboId(comboId);
		}else {
			obj.put("res", "isNull");
		}
		return obj;
	}
	
	/**
	 * 确认体检套餐
	 * @param cardNum
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/chooseItems.action")
	@ResponseBody
	public JSONObject chooseItems(String comboJSON,String mrPrice,String cardNum,HttpServletRequest req) throws Exception {
		JSONObject obj = new JSONObject();
		//非空判断
		List<String> isNullList = new ArrayList<String>();
		isNullList.add(cardNum);
		boolean notNull = IsNullUtil.isNull(isNullList);
		//用json封装套餐集合
		List<String> comboList = JSONObject.parseArray(comboJSON, String.class);
		/*
		 *在作用域中获取登陆者 
		 */
		Admin admin = (Admin) req.getSession().getAttribute("sessionAdminKey");
		if(admin == null) {//说明session失效,需重新登录
			obj.put("res", "toLogin");
		}else if(notNull && comboList.size() > 0) {
			obj = billingService.sureBilling(comboList, mrPrice, cardNum, admin);
		}else {
			obj.put("res", "isNull");
		}
		return obj;
	}
	
	@RequestMapping("/billing.action")
	public ModelAndView getPrintInfo(String mrNum,HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		JSONObject obj = new JSONObject();
		//非空判断
		List<String> isNullList = new ArrayList<String>();
		isNullList.add(mrNum);
		boolean notNull = IsNullUtil.isNull(isNullList);
		/*
		 *在作用域中获取登陆者 
		 */
		Admin admin = (Admin) req.getSession().getAttribute("sessionAdminKey");
		if(admin == null) {
			mv.setViewName("adminlogin");
		}else if(notNull) {
			obj = billingService.getPrintInfo(mrNum);
			User u = (User) obj.get("user");
			mv.addObject("user", u);
			String time = obj.getString("time");
			mv.addObject("time", time);
			List<Items> itemsList = (List<Items>) obj.get("itemsList");
			mv.addObject("itemsList", itemsList);
			mv.addObject("mrNum", mrNum);
			mv.setViewName("billing");
		}else {
			obj.put("res", "isNull");
		}
		return mv;
	}
}
