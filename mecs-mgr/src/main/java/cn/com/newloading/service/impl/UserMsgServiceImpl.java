package cn.com.newloading.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.bean.User;
import cn.com.newloading.dao.UserMapper;
import cn.com.newloading.service.UserMsgService;
import cn.com.newloading.utils.LimitUtil;

@Service
public class UserMsgServiceImpl implements UserMsgService {

	@Autowired
	private UserMapper userMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject userMsgQuery(String currPage, String userName, String number,String userTel, String startTime, String endTime) {

		if (currPage == null) {
			currPage = "1";
		}
		int limit = LimitUtil.USERLIMIT;
		int begin = (Integer.parseInt(currPage) - 1) * limit;

		RowBounds rb = new RowBounds(begin, limit);

		List<User> allUser = userMapper.getAllUser(userName, userTel, startTime, endTime, number, rb);
		Integer allUserCount = userMapper.getAllUserCount(userName, userTel, startTime, endTime, number);

		JSONObject obj = new JSONObject();
		obj.put("user", allUser);
		obj.put("count", allUserCount);
		obj.put("limit", limit);

		return obj;
	}

}
