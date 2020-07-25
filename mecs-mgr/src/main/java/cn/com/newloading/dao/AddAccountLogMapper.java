package cn.com.newloading.dao;

import cn.com.newloading.bean.Account;

public interface AddAccountLogMapper {
	/**
	 * 类里面需要设置的字段为：
	 * #{cardNum},#{accType},#{accDescribe},#{accMoney}
	 * @param account
	 * @return
	 */
	//个人对账表插入数据
	Integer insertAccount(Account account);
}
