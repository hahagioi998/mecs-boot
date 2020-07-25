package cn.com.newloading.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.bean.dto.MecRecordDto;
import cn.com.newloading.bean.dto.UserDto;
import cn.com.newloading.dao.LogMapper;
import cn.com.newloading.dao.MecSummaryMapper;
import cn.com.newloading.service.MecSummaryService;
/**
 * 体检总结
 */
@Service
public class MecSummaryServiceImpl implements MecSummaryService {
	/**
	 * 获取页面的  用户信息，以及项目体检记录信息
	 */
	@Autowired
	private MecSummaryMapper mapper;
	@Autowired
	private LogMapper logMapper;
	public JSONObject getAllInfo(String mecNum) {
		JSONObject jo = new JSONObject();
		UserDto user = mapper.getUserInfoByMecNum(mecNum);
		MecRecordDto mec = mapper.getUserMecRecordByMecNum(mecNum);
		jo.put("user", user);
		jo.put("mec", mec);
		return jo;
	}
	/**
	 * 通过项目记录id或者体检结果
	 * 
	 * @param irId
	 * @return
	 */
	@Override
	public JSONObject getResult(String irId) {
		JSONObject jo = new JSONObject();
		String s  = mapper.getResult(irId);
		jo.put("result", s);
		return jo;
	}
	/**
	 * 增加总结结果
	 */
	@Override
	public JSONObject addResult(String docId,String docName, String mecNum, String req, String guide) {
		Integer in = mapper.addResult(docName, mecNum, req, guide);
		JSONObject ob = new JSONObject();
		if (in >0) {//新增成功
			ob.put("sign", "suc");
			//增加日志
			logMapper.addLog(Integer.valueOf(docName), "体检总结", docId+" 给体检号为："+mecNum+" 的体检记录做了体检总结");
			return ob;
		}else {//新增失败
			ob.put("sign", "err");
			return ob;
		}
	}
}
