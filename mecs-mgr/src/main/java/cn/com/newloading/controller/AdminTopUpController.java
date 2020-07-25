package cn.com.newloading.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.service.AdminTopUpService;

@Controller
public class AdminTopUpController {
	
	@Autowired
	private AdminTopUpService ser;
	
	
	/**
	 * 充值
	 * @param money
	 * @param carNum
	 * @return
	 * 返回 suc 充值成功
	 * Err充值失败
	 * notLogin:没登录
	 */
	@RequestMapping("/AdminTopUp")
	@ResponseBody
	public JSONObject topUp(String money,String cardNum,HttpSession session) {
		JSONObject obj =new JSONObject();
		String st=ser.AdminTopUp(money,cardNum,session);
		obj.put("AdminTopUpKey", st);
		return obj;
	}
}
