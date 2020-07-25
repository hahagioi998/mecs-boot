package cn.com.newloading.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.newloading.bean.Log;
import cn.com.newloading.dao.FrontLogMapper;
import cn.com.newloading.service.FrontLogService;

@Service
public class FrontLogServiceImpl implements FrontLogService {

	@Autowired
	private FrontLogMapper frontLogMapper;

	@Transactional(rollbackFor = Exception.class)
	public ArrayList<Log> getLog(String beginTime, String endTime, String name) {
		ArrayList<Log> frontLogList = frontLogMapper.getLog(beginTime, endTime, name);
		return frontLogList;
	}

	@Transactional(rollbackFor = Exception.class)
	public Integer getAllCount(String beginTime, String endTime, String name) {
		Integer count = frontLogMapper.getAllCount(beginTime, endTime, name);
		return count;
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean delLog(List<String> logIdList) {
		Integer del = frontLogMapper.delLog(logIdList);
		if (del > 0) {
			return true;
		} else {
		}
		return false;
	}

}
