package cn.com.newloading.service;


import cn.com.newloading.bean.dto.SettleAccountsUserDto;

public interface SettleAccountsService {
	SettleAccountsUserDto querySettleAccountsByService(String cardNum);
}
