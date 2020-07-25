package cn.com.newloading.dao;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;

/**
 * 插入日志
 * 
 */
public interface LogMapper {

	/**
	 * //用户对账时插入日志表记录 操作人id，操作类型，，
	 * @param operId 操作人id
	 * @param operType 操作类型，，
	 * @param logRemark 描述
	 * @return
	 */
	Integer addLog(@Param("operId")int operId,@Param("operType")String operType,@Param("logRemark")String logRemark);

	
	Integer delLog(@Param("logIdList")ArrayList<String> logIdList);
	
}
