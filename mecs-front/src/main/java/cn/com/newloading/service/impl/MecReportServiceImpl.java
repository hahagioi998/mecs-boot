package cn.com.newloading.service.impl;

import java.util.List;

import cn.com.newloading.dao.MecReportMapper;
import cn.com.newloading.bean.MecRecord;
import cn.com.newloading.service.MecReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

@Service
public class MecReportServiceImpl implements MecReportService {
	
	@Autowired
	private MecReportMapper mecReportMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject getMecRecord(String cardNum, String mecCode) {
		JSONObject obj = new JSONObject();
		/*
		 * 数据库获取体检记录
		 */
		List<MecRecord> mecRecordList = mecReportMapper.getMecRecord(cardNum, mecCode);
		obj.put("mecRecordList", mecRecordList);
		obj.put("res", "suc");
		return obj;
	}

}
