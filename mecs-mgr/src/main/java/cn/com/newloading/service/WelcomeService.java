package cn.com.newloading.service;

import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.bean.Admin;

public interface WelcomeService {

	/**
	 * 获取首页的信息
	 * @return
	 */
	JSONObject getAdminAndData(Admin admin);
	
}
