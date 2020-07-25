package cn.com.newloading.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.newloading.bean.ItemsRecord;
import cn.com.newloading.bean.Log;
import cn.com.newloading.bean.MecRecord;
import cn.com.newloading.bean.User;

/**
 * 体检报告打印查询信息
 */
public interface PrintMecReportMapper {

	/**
	 * 根据体检号码获取用户
	 * @param mrNum
	 * @return
	 */
	User getUserByMrNum(@Param("mrNum") String mrNum);
	
	/**
	 * 根据体检号获取体检记录信息
	 * @param mrNum
	 * @return
	 */
	MecRecord getMecRecordByMrNum(@Param("mrNum") String mrNum);
	
	/**
	 * 根据体检号码获取项目记录
	 * @param mrNum
	 * @return
	 */
	List<ItemsRecord> itemsList(@Param("mrNum") String mrNum);
	
	/**
	 * 打印体检报告的时候去改变体检报告的状态
	 * @param mrNum
	 * @return
	 */
	Integer changeMecRecordState(@Param("mrNum") String mrNum);
	
	/**
	 * 根据卡号获取用户的ID
	 * @param cardN
	 * @return
	 */
	String getUserIdByCardNum(@Param("cardN") String cardN);
	
	/**
	 * 插入日志表
	 * @param mgrLog
	 * @return
	 */
	Integer addFrontLog(Log mgrLog);
}
