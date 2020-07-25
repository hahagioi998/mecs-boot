package cn.com.newloading.utils;

import java.util.List;

/**
 * 非空判断工具类
 */
public class IsNullUtil {

	public static boolean isNull(List<String> list) {
		/**
		 * 判断集合中的每一个元素是否为空 若出现一个空,返回false 全不为空,返回true
		 */
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == null || "".equals(list.get(i))) {
				return false;
			}
		}
		return true;
	}
}
