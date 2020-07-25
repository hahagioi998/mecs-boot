package cn.com.newloading.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.newloading.bean.dto.SettleAccountsUserDto;
import cn.com.newloading.dao.SettleAccountsUserDtoMapper;
import cn.com.newloading.service.SettleAccountsService;

@Service
public class SettleAccountsServiceImpl implements SettleAccountsService{
	//获得dao方法
	@Autowired
	private SettleAccountsUserDtoMapper mapper;
	
	
	@Override
	public SettleAccountsUserDto querySettleAccountsByService(String cardNum) {
		// TODO Auto-generated method stub
		SettleAccountsUserDto dto=mapper.querySettleAccounts(cardNum);
		return dto;
	}
	
}
