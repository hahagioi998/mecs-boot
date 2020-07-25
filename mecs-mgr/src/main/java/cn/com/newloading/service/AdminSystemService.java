package cn.com.newloading.service;

import java.util.Set;

import cn.com.newloading.bean.Admin;
import cn.com.newloading.bean.dto.MenuDto;

public interface AdminSystemService {

	/**
	 * 根据登录者获取相应的菜单
	 * @param admin
	 * @return
	 */
	Set<MenuDto> menuList(Admin admin);
	
}
