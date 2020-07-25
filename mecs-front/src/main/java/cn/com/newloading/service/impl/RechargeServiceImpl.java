package cn.com.newloading.service.impl;

import cn.com.newloading.dao.RechargeMapper;
import cn.com.newloading.bean.Log;
import cn.com.newloading.service.RechargeService;
import cn.com.newloading.utils.CreateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RechargeServiceImpl implements RechargeService {

	@Autowired
	private RechargeMapper rechargeMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public String getAccountOrderNum() {
		/*
		 * 账单为15位
		 * 并检查该账单是否已经存在
		 */
		while(true) {
			String aoNum = CreateCodeUtil.createCode(15);
			String aoId = rechargeMapper.checkAccountOrderNum(aoNum);
			if(aoId == null || "".equals(aoId)) {
				return aoNum;
			}
		}
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public String finishRecharge(String cardNum, String aoNum, String rechrgeMoney) {
		// TODO Auto-generated method stub
		/*
		 * 插入账单表
		 */
		Integer addAccountOrder = rechargeMapper.addAccountOrder(aoNum, "自助充值", rechrgeMoney, "自助充值["+rechrgeMoney+"元]", cardNum);
		/*
		 * 修改卡表
		 */
		String oldCardBalance = rechargeMapper.getBalanceByCardNum(cardNum);
		String newBalance = String.valueOf(Double.valueOf(rechrgeMoney) + Double.valueOf(oldCardBalance));
		Integer changeCardBalance = rechargeMapper.changeCardBalance(newBalance, cardNum);
		/*
		 * 插入日志表
		 */
		//用卡号查找用户ID
		String userId = rechargeMapper.getUserIdByCrdNum(cardNum);
		String remark = "向卡号["+cardNum+"]自助充值了["+rechrgeMoney+"]元,账单号为["+aoNum+"]";
		Log frontLog = new Log(null, userId, "自助充值", remark, null, null);
		Integer addFrontLog = rechargeMapper.addFrontLog(frontLog);
		/*
		 * 插入个人对账表
		 */
		Integer addAccount = rechargeMapper.addAccount(cardNum, remark, rechrgeMoney);
		
		if(addAccount > 0 && changeCardBalance > 0 && addAccountOrder > 0 && addFrontLog > 0) {
			return "suc";
		}
		return "err";
	}

	@Override
	public String getBalanceByCardNum(String cardNum) {
		// TODO Auto-generated method stub
		String balance = rechargeMapper.getBalanceByCardNum(cardNum);
		return balance;
	}
}
