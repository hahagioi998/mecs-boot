package cn.com.newloading.service.impl;

import cn.com.newloading.dao.LoginMapper;
import cn.com.newloading.bean.User;
import cn.com.newloading.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginServiceImpl implements LoginService{
   
	@Autowired
	private LoginMapper mapper;
    @Transactional(rollbackFor = Exception.class)
	public User login(String cardNum) {
		User user = mapper.loginByCardNum(cardNum);
		return user;
	}
}
