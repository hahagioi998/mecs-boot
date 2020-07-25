package cn.com.newloading.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.service.MecSynthesizeService;

/**
 * 体检综合查询
 */
@Controller
public class MecSynthesizeController {

	@Autowired
	private MecSynthesizeService mecSynthesizeService;
	
	/**
	 * 有条件的获取体检综合信息
	 * @param nowPage
	 * @param sTime
	 * @param eTime
	 * @param userName
	 * @param userTel
	 * @param mecCode
	 * @return
	 */
	@RequestMapping("/getMecSynthesize.action")
	@ResponseBody
	public JSONObject getMecSynthesize(String nowPage,String sTime,String eTime,String userName,String userTel,String mecCode) {
		JSONObject obj = new JSONObject();
		obj = mecSynthesizeService.getMecSynthesize(nowPage, sTime, eTime, userName, userTel, mecCode);
		return obj;
	}
}
