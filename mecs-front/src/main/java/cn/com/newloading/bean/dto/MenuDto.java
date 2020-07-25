package cn.com.newloading.bean.dto;

import java.util.List;

import cn.com.newloading.bean.Menu;
/**
 * 拓展Menu
 */
public class MenuDto extends Menu {

	public List<Menu> menuSet;

	public MenuDto() {
		super();
	}

	public MenuDto(List<Menu> menuSet) {
		super();
		this.menuSet = menuSet;
	}

	public List<Menu> getMenuSet() {
		return menuSet;
	}

	public void setMenuSet(List<Menu> menuSet) {
		this.menuSet = menuSet;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MenuDto [menuSet=");
		builder.append(menuSet);
		builder.append("]");
		return builder.toString();
	}
	
}
