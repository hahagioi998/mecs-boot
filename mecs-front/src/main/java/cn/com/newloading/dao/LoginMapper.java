package cn.com.newloading.dao;

import org.apache.ibatis.annotations.Param;

import cn.com.newloading.bean.User;

/**
     用户通过卡号登录
 */
public interface LoginMapper {
	/**
	 * 用户通过卡号登录
	 * @param cardNum
	 * @return
	 */
	User loginByCardNum(@Param("cardNum") String cardNum);

}
