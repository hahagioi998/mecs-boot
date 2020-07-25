package cn.com.newloading.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.dao.CardMapper;
import cn.com.newloading.dao.LogMapper;
import cn.com.newloading.service.CardDeleteService;

@Service
public class CardDeleteServiceImpl implements CardDeleteService {

	@Autowired
	private CardMapper cardMapper;

	@Autowired
	private LogMapper logMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject delCard(String cardId, String cardNum, String adminId) {

		JSONObject obj = new JSONObject();

		Integer delCard = cardMapper.delCard(cardId);

		Integer addLog = logMapper.addLog(Integer.parseInt(adminId), "删除卡", "管理员删除体检卡:" + cardNum);

		if (delCard > 0 && addLog > 0) {
			obj.put("del", "删除成功");
		} else {
			obj.put("del", "删除失败");
		}

		return obj;
	}

}
