package cn.com.newloading.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.newloading.bean.User;
import cn.com.newloading.bean.dto.MecRecordDto;

public interface PrintOrderMapper {

	/**
	 * -------------------------------------邱-------------------------------------------
	 * 根据卡号获取体检人信息
	 * @param cardNum
	 * @return
	 */
	User getUserInfoByCardNum(@Param("cardNum") String cardNum);
	
	/**
	 * 根据卡号获取体检记录
	 * @param cardNum
	 * @return
	 */
	List<MecRecordDto> getMecRecordByCardNum(@Param("cardNum") String cardNum);
}
