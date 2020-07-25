package cn.com.newloading.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.bean.Account;
import cn.com.newloading.bean.Admin;
import cn.com.newloading.bean.Log;
import cn.com.newloading.bean.User;
import cn.com.newloading.dao.AddAccountLogMapper;
import cn.com.newloading.dao.AdminLoginMapper;
import cn.com.newloading.dao.BuildCardMapper;
import cn.com.newloading.service.BuildCardService;


@Service
public class BuildCardServiceImpl implements BuildCardService {

	@Autowired
	private BuildCardMapper ma;

	@Autowired // 就是为了插入日志，其他没用到
	private AdminLoginMapper mapper;

	@Autowired // 为了插入个人对账表
	private AddAccountLogMapper accountLog;

	@Override
	public String backCardNumber() {
		// TODO Auto-generated method stub
		// 给界面返回提示的3个卡号，0表示待销售的卡
		String list = ma.queryCardNumber(0);
		// System.out.println("给界面返回提示的3个卡号 list="+list);

		return list;
	}

	/**
	 * 返回值： withoutCardID没有这个卡号 ， buildCardPericeErr未设置开卡单价, MoneyErr充值金额小于开卡的钱，无法开卡
	 * buildUserErr：开卡失败，无法创建用户时，序列号 buildUserErr2：开卡失败，创建用户 notLogIn：收费员未先登录
	 * buildCardErr：建卡错误 logErr：插入日志失败 suc：建卡成功
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public String buildCard(HttpServletRequest req, String cardNum, String money, String userName, String userSex,
			String userAge, String userBloodType, String userBirthday, String userPhone, String userAddress,
			String userMemo, String userNativePlace) {
		// 从界面获得卡号，充值的金额，查到cardID，以cardID为条件，插入userID，然后改状态为已经销售的状态2
		//将存入session里面的String取出转换成Admin对象
		Admin ad = (Admin) req.getSession().getAttribute("sessionAdminKey");
		if (ad == null) {
			return "notLogIn";
		}
		// 先查数据库有没有这个卡号，状态待销售的卡，查下有没有这个条件的CardID
		String carId = ma.queryCardIdByCardNumberAndState(cardNum, 0);
		if (carId == null) {
			return "withoutCardID";
		}
		// 在字典表里面传入 buildcard字符串，到字典表查下 ，开单的单价，界面得到金额要减去这个单价，才能存到卡里
		Integer mo = ma.queryBuildCardMoney("BUILDCARD");
		if ("".equals(mo) || mo == null) {
			return "buildCardPericeErr";
		} else {
			// 注意这边传入数据的金额，必须要减去开卡的钱，再存入数据库
			// 从界面获得卡号，充值的金额，查到cardID，以cardID为条件，插入userID，然后改状态为已经销售的状态
			Integer balance = Integer.valueOf(money) - mo;
			// 如果存的钱不够
			if (balance < 0) {
				return "MoneyErr";
			} else {
				User u = new User();
				u.setUserName(userName);
				u.setUserSex(userSex);
				u.setUserAge(userAge);
				u.setUserBloodType(userBloodType);
				u.setUserBirthday(userBirthday);
				u.setUserPhone(userPhone);
				u.setUserAddress(userAddress);
				u.setUserNativePlace(userNativePlace);
				u.setUserMemo(userMemo);
				// 创建用户
				Integer a = ma.buildUser(u);
				if (a == 0) {
					return "buildUserErr2";
				} else {
					// 建卡注意这边传入数据的金额，必须要减去开卡的钱，再存入数据库
					String sellAdminId = ad.getAdminId();
					Integer in = ma.buildCard(u.getUserId(), carId, 1, sellAdminId, balance);
					if (in == 0) {
						return "buildCardErr";
					} else {
						// 插入个人对账表
						Account ac = new Account();
						ac.setCardNum(cardNum);// 卡号
						ac.setAccType("1");
						ac.setAccDescribe("开卡的时候，操作的收费员ID为：" + sellAdminId + "操作的收费员账号为：" + ad.getAdminAcc());// 详情
						ac.setAccMoney(String.valueOf(mo));// 金额
						Integer insertAccount = accountLog.insertAccount(ac);
						if (insertAccount == 0) {
							return "AccountErr";
						}

						// 插入日志
						Log log = new Log();
						log.setLogUserId(sellAdminId);// 操作的id
						log.setLogType("收费员售卡");
						// 获得存在session的字段名
						log.setLogRemark("收费员操作账号为：" + ad.getAdminAcc());
						Integer integer = mapper.doLog(log);
						if (integer == 0) {
							return "logErr";
						} else {
							return "suc";
						}
					}
				}
			}
		}
	}
}
