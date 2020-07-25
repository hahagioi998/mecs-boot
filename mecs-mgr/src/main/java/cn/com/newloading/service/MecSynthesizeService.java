package cn.com.newloading.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 获取综合信息业务
 */
public interface MecSynthesizeService {

	/**
	 * 获取体检综合信息
	 * @param nowPage
	 * @param sTime
	 * @param eTime
	 * @param userName
	 * @param userTel
	 * @param mecCode
	 * @return
	 */
	JSONObject getMecSynthesize(String nowPage,String sTime,String eTime,String userName,String userTel,String mecCode);
	
}
