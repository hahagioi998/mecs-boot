package cn.com.newloading.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.bean.Admin;
import cn.com.newloading.service.CardStoreService;
import cn.com.newloading.utils.IsNullUtil;

@Controller
public class CardStoreController {

	@Autowired
	private CardStoreService cardStoreService;

	@RequestMapping("/cardStore.action")
	@ResponseBody
	public JSONObject cardStore(String startNum, String endNum, String cardPrefix, HttpServletRequest request) {

		HttpSession session = request.getSession();
		//将存入session里面的String取出转换成Admin对象
		Admin admin = (Admin) session.getAttribute("sessionAdminKey");
		String adminId = admin.getAdminId();

		JSONObject obj = new JSONObject();
		List<String> isNullList = new ArrayList<String>();
		isNullList.add(startNum);
		isNullList.add(endNum);
		
		boolean notNull = IsNullUtil.isNull(isNullList);
		if (notNull) {
			// 去service,然后添加日志
			JSONObject cardStore = cardStoreService.cardStore(startNum, endNum, cardPrefix, adminId);
			return cardStore;
		} else {
			obj.put("res", "isNull");
		}
		return obj;
	}

}
