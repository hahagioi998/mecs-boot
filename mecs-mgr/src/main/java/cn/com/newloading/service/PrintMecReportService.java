package cn.com.newloading.service;

import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.bean.Admin;

public interface PrintMecReportService {
	
	/**
	 * 根据体检码获取需要打印的信息
	 * @param mrNum
	 * @return
	 */
	JSONObject getPrintMecReportInfo(String mrNum,Admin admin);
	
	/**
	 * 根据体检码获取详情
	 * @param mrNum
	 * @return
	 */
	JSONObject getPrintMecReport(String mrNum);


}
