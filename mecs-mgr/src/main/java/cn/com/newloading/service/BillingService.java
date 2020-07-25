package cn.com.newloading.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.bean.Admin;
public interface BillingService {

	/**
	 * *********************************************邱 start******************************************
	 * 开单
	 * 根据卡号获取体检人信息,卡内余额,体检人体检记录
	 * @param cardNum
	 * @return
	 */
	JSONObject getUserInfoByCardNum(String cardNum);
	
	/**
	 * 获取所有的套餐信息
	 * @return
	 */
	JSONObject getAllComboInfo(String comboName,String comboState);
	
	/**
	 * 根据套餐ID获取某个套餐的详情
	 * @param comboId
	 * @return
	 */
	JSONObject getDetailsByComboId(String comboId);
	
	/**
	 * 确认开单
	 * @param comboList 套餐集合
	 * @param cardNum 卡号
	 * @param admin 开单人员
	 * @return
	 * @throws Exception 
	 */
	JSONObject sureBilling(List<String> comboList,String mrPrice,String cardNum,Admin admin) throws Exception ;
	
	/**
	 * 根据体检记录号码获取需要打印的信息
	 * @param mrNum
	 * @return
	 */
	JSONObject getPrintInfo(String mrNum);
}
