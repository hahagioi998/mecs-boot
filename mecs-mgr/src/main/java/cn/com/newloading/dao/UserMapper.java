package cn.com.newloading.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import cn.com.newloading.bean.User;

public interface UserMapper {

	/**
	 * 查询体检人信息
	 * 
	 * @param userName
	 * @param userTel
	 * @param startTime
	 * @param endTime
	 * @param number
	 * @param rb
	 * @return
	 */
	List<User> getAllUser(@Param("name") String userName, @Param("tel") String userTel,
			@Param("start") String startTime, @Param("end") String endTime, @Param("number") String number,
			RowBounds rb);

	/**
	 * 获取体检人数量
	 * 
	 * @param userName
	 * @param userTel
	 * @param startTime
	 * @param endTime
	 * @param number
	 * @return
	 */
	Integer getAllUserCount(@Param("name") String userName, @Param("tel") String userTel,
			@Param("start") String startTime, @Param("end") String endTime, @Param("number") String number);
}
