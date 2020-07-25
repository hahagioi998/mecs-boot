package cn.com.newloading.service;

import com.alibaba.fastjson.JSONObject;


public interface PrintMecReportService {
	
	/**
	 * 根据体检码获取需要打印的信息
	 * @param mrNum
	 * @return
	 */
	JSONObject getPrintMecReportInfo(String mrNum,String cardN);
	
	/**
	 * 根据体检码获取详情
	 * @param mrNum
	 * @return
	 */
	JSONObject getPrintMecReport(String mrNum);


}
