package cn.com.newloading.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.newloading.bean.Admin;
import cn.com.newloading.bean.dto.MenuDto;
import cn.com.newloading.dao.AdminSystemMapper;
import cn.com.newloading.service.AdminSystemService;

@Service
public class AdminSystemServiceImpl implements AdminSystemService {

	@Autowired
	private AdminSystemMapper adminSystemMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Set<MenuDto> menuList(Admin admin) {
		// TODO Auto-generated method stub
		/**
		 * 获取角色ID
		 */
		List<String> roleIdList = adminSystemMapper.getRoleIdByAdminId(admin.getAdminId());
		/*
		 * 批量查找
		 */
		Set<MenuDto> menuList = adminSystemMapper.getMenuDtoByRoleId(roleIdList);
		return menuList;
	}

}
