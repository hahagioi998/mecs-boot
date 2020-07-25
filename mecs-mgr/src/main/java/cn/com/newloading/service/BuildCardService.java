package cn.com.newloading.service;

import javax.servlet.http.HttpServletRequest;

public interface BuildCardService {
	//查已经有3个卡，返回给界面提示
	String backCardNumber();
	
	//建卡
	String buildCard(HttpServletRequest req,String cardNum,String money,String userName,String userSex,String userAge,String userBloodType,String userBirthday,String userPhone,String userAddress,String userMemo,String userNativePlace);
}
