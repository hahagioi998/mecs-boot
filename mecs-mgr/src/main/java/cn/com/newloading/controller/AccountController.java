package cn.com.newloading.controller;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ConfigurableObjectInputStream;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.bean.Account;
import cn.com.newloading.bean.Dict;
import cn.com.newloading.service.AccountService;
import cn.com.newloading.service.BackMoneyService;
import cn.com.newloading.utils.LimitUtil;

/**
 * 用户对账
 */
@Controller
public class AccountController {
	
	@Autowired
	private AccountService as;
	@Autowired
	private BackMoneyService bs;
	@RequestMapping("/readCard.action")
	@ResponseBody
	public JSONObject readCard(String currPage,String beginTime,String overTime,
			String type,String  cardNum) {//读卡 获取用卡人
		JSONObject obj = new JSONObject();
		int beginPage = (Integer.valueOf(currPage)-1) * LimitUtil.ACCOUNTLIMIT ;
//		System.out.println(beginPage+"---123");
		//获取余额
		String balance = null;
		balance = as.getBalanceByCard(cardNum);
		if(balance == null || "".equals(balance)) {
			obj.put("name", "");
			return obj;
		}
		//获取对账总记录数
		int count = as.getAccountCountByCard(cardNum, beginTime, overTime, type);
//		System.out.println(count+"--111");
		//获取对账记录的集合
		ArrayList<Account> list = (ArrayList<Account>) as.getAccountByCard(cardNum, beginTime, overTime, type, new RowBounds(beginPage, LimitUtil.ACCOUNTLIMIT ));
        
		String userName = bs.getUserNameByCard(cardNum);
		obj.put("userName", userName);
		obj.put("count", count);
		obj.put("list", list);
		obj.put("limit", LimitUtil.ACCOUNTLIMIT);
		obj.put("balance", balance);
		return obj;
	}
	/**
	 * 获取下拉框的值
	 * @param type
	 * @return
	 */
	@RequestMapping("/getCheckInfo.action")
	@ResponseBody
	public JSONObject getCheckInfoByDictType(String type) {
		ArrayList<Dict> list = as.getDeskInfoByDictType(type);
		JSONObject jo = new JSONObject();
		jo.put("list", list);
		return jo;
	}
}
