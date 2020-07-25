package cn.com.newloading.service;

import cn.com.newloading.bean.User;

public interface LoginService {
	/**
	 * 用户通过卡号登录
	 * 
	 * @param cardNum
	 * @return
	 */
	User login( String cardNum);
}
