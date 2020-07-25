package cn.com.newloading.utils;

import java.util.Random;

/**
 * 生成标识码,重要用于生成体检号码
 */
public class CreateCodeUtil {

	/*
	 * 生成码,根据传参进来,参数为生成几位的码
	 */
	public static String createCode(int length) {
		String code = "";
		Random ran = new Random();//创建随机数对象
		String[] str = {"0","1","2","3","4","5","6","7","8","9"};
		for (int i = 0; i < length; i++) {
			int index = ran.nextInt(10);//获取一个0-9的随机数,作为下标
			code += str[index];
		}
		return code;
	}
}
