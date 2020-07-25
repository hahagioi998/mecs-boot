package cn.com.newloading.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.bean.Admin;
import cn.com.newloading.bean.Combo;
import cn.com.newloading.bean.Items;
import cn.com.newloading.bean.Log;
import cn.com.newloading.bean.MecRecord;
import cn.com.newloading.bean.User;
import cn.com.newloading.bean.dto.ItemsDto;
import cn.com.newloading.dao.BillingMapper;
import cn.com.newloading.service.BillingService;
import cn.com.newloading.utils.CreateCodeUtil;


@Service
public class BillingServiceImpl implements BillingService {

	@Autowired
	private BillingMapper billingMapper;

	@Transactional(rollbackFor = Exception.class)
	public JSONObject getUserInfoByCardNum(String cardNum) {
		JSONObject obj = new JSONObject();
		// 获取卡内余额
		String balance = billingMapper.getCardBalance(cardNum);
		/*
		 * 判断卡号的正确性 balance == null 说明卡号的不正确 直接返回错误信息 否则继续
		 */
		if (balance == null || "".equals(balance)) {
			obj.put("res", "cardNumErr");// 卡号错误标识
			return obj;
		}
		obj.put("res", "cardNumSuc");// 卡号正确标识
		obj.put("balance", balance);
		// 获取用户信息
		User user = billingMapper.getUserInfoByCardNum(cardNum);
		obj.put("user", user);

		/*
		 * 获取用户的体检记录 1,先根据卡号获取持卡人的所有卡ID 2,再根据获取到的卡ID去逐个获取体检记录
		 */
		List<String> cardIdList = billingMapper.getCardIdByUserId(cardNum);
		
		List<MecRecord> mecRecordList = billingMapper.getMecRecordList(cardIdList);
		obj.put("mecRecordList", mecRecordList);
		return obj;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject getAllComboInfo(String comboName,String comboState) {
		JSONObject obj = new JSONObject();
		List<Combo> comboList = billingMapper.getAllComboInfo(comboName,comboState);
		/**
		 * 将获取到的套餐集合存入json
		 */
		obj.put("comboList", comboList);
		return obj;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject getDetailsByComboId(String comboId) {
		JSONObject obj = new JSONObject();
		/*
		 * 获取详情
		 */
		List<ItemsDto> itemsDtoList = billingMapper.getDetailsByComboId(comboId);
		obj.put("itemsDtoList", itemsDtoList);
		return obj;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject sureBilling(List<String> comboList, String mrPrice, String cardNum, Admin admin)
			throws Exception {
		JSONObject obj = new JSONObject();
		/*
		 * 1,获取体检记录ID,需用于项目记录表 2,根据套餐ID获取套餐名,需用于体检记录表 3,根据卡号获取卡ID,需用于体检记录表 4,需要插入体检记录表
		 * 5,批量查找项目,获取项目名和项目价格,需用于项目记录表 6,需要插入项目记录表 7,需要插入日志表
		 */
		List<String> conboNameList = billingMapper.getConboName(comboList);// 2
		/*
		 * 套餐名采用逗号拼接,例如"A项目,B项目"
		 */
		String comboName = conboNameList.get(0);
		for (int i = 1; i < conboNameList.size(); i++) {
			comboName += "," + conboNameList.get(i);
		}

		String cardId = billingMapper.getCardIdByCardNum(cardNum);// 3
		/*
		 * 创建一个体检记录表,方便插入体检记录数据
		 * 调用一个工具类,自动生成一个体检号码
		 */
		String mrNum;
		while(true) {
		    mrNum = CreateCodeUtil.createCode(10);//生成体检号码
			/*
			 * 去数据库体检号码是否重复
			 */
		    String check = billingMapper.checkMecNumber(mrNum);
		    /*
		     * 如果重复了就需要继续生成,否则跳出循环
		     */
		    if(check == null) {
		    	break;
		    }
		}
		
		MecRecord mecRecord = new MecRecord(null, mrNum, cardId, comboName, mrPrice, admin.getAdminId(), null, null, null, "1", null, null, null, null);
		Integer addMecRecord = billingMapper.addMecRecord(mecRecord);//   4
		
		List<Items> itemsList = billingMapper.getItemsNameByComboId(comboList);//  5
		
		for (int i = 0; i < itemsList.size(); i++) {
			itemsList.get(i).setItemsDemo(mecRecord.getMrId());
		}

		Integer addItemsRecord = 0;
		for (int i = 0; i < itemsList.size(); i++) {
			addItemsRecord = billingMapper.addItemsRecord(itemsList.get(i));// 6
		}

		/*
		 * 创建日志记录对象
		 */
		Log mgrLog = new Log(null, admin.getAdminId(), "开单", "开单,体检号码为[" + mrNum + "]", null, null); 
		Integer addMgrLog = billingMapper.addMgrLog(mgrLog);//  7
		

		if (addMecRecord > 0 && addItemsRecord > 0 && addMgrLog > 0) {
			obj.put("mrNum", mrNum);
			obj.put("res", "suc");
		} else {
			obj.put("res", "err");
		}
		return obj;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject getPrintInfo(String mrNum) {
		JSONObject obj = new JSONObject();
		/*
		 * 获取用户的信息
		 */
		User user = billingMapper.getUserByMrId(mrNum);
		obj.put("user", user);

		/*
		 * 获取开单时间
		 */
		String time = billingMapper.getBilTimeByMrId(mrNum);
		obj.put("time", time);

		/*
		 * 获取体检项目 的信息
		 */
		List<Items> itemsList = billingMapper.getItemsInfoByMrId(mrNum);
		obj.put("itemsList", itemsList);

		return obj;
	}
}
