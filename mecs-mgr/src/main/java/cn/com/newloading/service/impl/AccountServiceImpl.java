package cn.com.newloading.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.newloading.bean.Account;
import cn.com.newloading.bean.Dict;
import cn.com.newloading.dao.AccountMapper;
import cn.com.newloading.dao.DictMapper;
import cn.com.newloading.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	/**
	 * 卡对账时查询总条数
	 */
	@Autowired
	private AccountMapper mapper;
	@Autowired
	private DictMapper dictMapper;

	@Transactional(rollbackFor = Exception.class)
	public Integer getAccountCountByCard(String cardNum, String beginTime, String overTime, String type) {
		int count = mapper.getAccountCountByCard(cardNum, beginTime, overTime, type);
		return count;
	}

	/**
	 * 卡对账时根据卡号 查询所有对账记录集合
	 */
	@Override
	public List<Account> getAccountByCard(String cardNum, String beginTime, String overTime, String type,
			RowBounds rb) {
		ArrayList<Account> list = (ArrayList<Account>) mapper.getAccountByCard(cardNum, beginTime, overTime, type, rb);
		return list;
	}

	/**
	 * 根据卡号查询余额
	 */
	public String getBalanceByCard(String cardNum) {
		String s = mapper.getBalanceByCard(cardNum);
		return s;
	}
	
	@Override
	public ArrayList<Dict> getDeskInfoByDictType(String type) {
		ArrayList<Dict> list = dictMapper.getDeskInfoByDictType(type);
		return list;
	}

}
