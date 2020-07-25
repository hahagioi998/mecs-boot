package cn.com.newloading.service;

import javax.servlet.http.HttpSession;

public interface AdminTopUpService {
	String AdminTopUp(String money,String cardNum,HttpSession session);
}
