package cn.com.newloading.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import cn.com.newloading.bean.Account;
import cn.com.newloading.bean.Dict;

/**
 * 卡对账业务对应的 dao
 */
public interface AccountMapper {
	/**
	 * //根据卡号来查询对账记录的总条数(动态查询条件，时间，（消费 充值）类型)
	 * @param cardNum 卡号
	 * @param beginTime 开始时间
	 * @param overTime 结束时间
	 * @param type 消费充值类型
	 * @return
	 */
	Integer getAccountCountByCard(@Param("cardNum")String cardNum,@Param("beginTime")String beginTime,@Param("overTime")String overTime,@Param("type")String type);
	/**
	 * //根据卡号来查询对账记录的集合
	 * @param cardNum 卡号
	 * @param beginTime 开始时间
	 * @param overTime 结束时间
	 * @param type 消费充值类型
	 * @param rb 分页 rowbounds
	 * @return
	 */
	List<Account> getAccountByCard(@Param("cardNum")String cardNum,@Param("beginTime")String beginTime,@Param("overTime")String overTime,@Param("type")String type,@Param("rb")RowBounds rb);
	/**
	 * //根据卡号获取卡余额
	 * @param cardNum卡号
	 * @return
	 */
	String getBalanceByCard(@Param("cardNum")String cardNum);
	/**
	 * 根据卡号获取用户id
	 * @param cardNum
	 * @return
	 */
	Integer getUserIdByCard(@Param("cardNum")String cardNum);
	/**
	 * //用户对账时插入日志表记录 操作人id，操作类型，，
	 * @param operId
	 * @param operType
	 * @param logRemark
	 * @param operaManId
	 * @return
	 */
	Integer addLogForAccount(@Param("operId")int operId,@Param("operType")String operType,@Param("logRemark")String logRemark);
   /**
    * 获取用户名
    * @param userId
    * @return
    */
    String getUserNameByUserId(@Param("userId")Integer userId);
    /**
     * 获取下拉框动态查询的数据 通过字典type
     */
    ArrayList<Dict> getDeskInfoByDictType(@Param("type")String type);
}
