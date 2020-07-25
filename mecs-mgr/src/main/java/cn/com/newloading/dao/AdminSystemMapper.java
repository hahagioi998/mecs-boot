package cn.com.newloading.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import cn.com.newloading.bean.dto.MenuDto;

/**
 * 登录主页dao
 */
public interface AdminSystemMapper {

	/**
	 * 获取登录者的角色ID
	 * @param adminId
	 * @return
	 */
	List<String> getRoleIdByAdminId(@Param("adminId") String adminId);
	
	/**
	 * 获取menudto
	 * @param roleIdList
	 * @return
	 */
	Set<MenuDto> getMenuDtoByRoleId(@Param("roleIdList") List<String> roleIdList);
}
