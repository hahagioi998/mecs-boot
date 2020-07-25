package cn.com.newloading.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.bean.Item;
import cn.com.newloading.dao.ItemMapper;
import cn.com.newloading.dao.LogMapper;
import cn.com.newloading.service.ItemAlterService;

@Service
public class ItemAlterServiceImpl implements ItemAlterService {

	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private LogMapper logMapper;
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject alterItem(String doId,String itemName, String itemId) {

		JSONObject obj = new JSONObject();

		String selectItemName = itemMapper.selectItemName(itemName);
		if (selectItemName == null) {

			Integer delCard = itemMapper.alterItemName(itemName, itemId);

			if (delCard > 0) {
				obj.put("alter", "修改成功");
				 //插入日志
				Item iemById = itemMapper.getIemById(itemId);
				logMapper.addLog(Integer.valueOf(doId),"修改细项", "修改了"+iemById.getItemName()+"细项");
			} else {
				obj.put("alter", "修改失败");
			}
		} else {
			obj.put("alter", "细项名已存在！请重新命名");
		}

		return obj;

	}

}
