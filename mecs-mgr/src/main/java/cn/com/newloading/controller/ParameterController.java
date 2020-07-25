package cn.com.newloading.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.bean.Dict;
import cn.com.newloading.service.ParameterService;
import cn.com.newloading.utils.LimitUtil;

@Controller
public class ParameterController {

	@Autowired
	ParameterService parameterService;

	@RequestMapping("/showDict.action")
	@ResponseBody
	public JSONObject showDict(String beginTime, String endTime, String name, String nowPage) {

		Integer offset = (Integer.valueOf(nowPage) - 1) * LimitUtil.DEPLIMIT;
		int limit = LimitUtil.DEPLIMIT;

		RowBounds rb = new RowBounds(offset, limit);
		ArrayList<Dict> dictList = parameterService.getDict(beginTime, endTime, name, rb);
		Integer count = parameterService.getCount(beginTime, endTime, name);
		JSONObject obj = new JSONObject();
		obj.put("dictList", dictList);
		obj.put("count", count);
		obj.put("limit", limit);
		return obj;

	}

	@RequestMapping("/delDict.action")
	@ResponseBody
	public JSONObject delDict(String dictId) {
		boolean bo = parameterService.delDict(dictId);
		String res = null;
		if (bo) {
			res = "删除成功！";
		} else {
			res = "err";
		}
		JSONObject obj = new JSONObject();
		obj.put("res", res);
		return obj;

	}

	@RequestMapping("/addDict.action")
	@ResponseBody
	public JSONObject addDict(String dictCode, String dictName, String dictType, String dictDescribe) {// 新增参数
		String res = null;
		boolean bo = parameterService.addDict(dictCode, dictName, dictType, dictDescribe);
		if (bo) {
			res = "新增成功！";
		} else {
			res = "新增失败";
		}

		JSONObject obj = new JSONObject();
		obj.put("res", res);
		return obj;

	}

	@RequestMapping("/showDictMessage.action")
	@ResponseBody
	public JSONObject showDictMessage(HttpServletRequest req) {// 编辑参数时回填消息
		String dictId = req.getSession().getAttribute("dictId").toString();
		String dictName = parameterService.getDictName(dictId);
		String dictDescribe = parameterService.getDictDescribe(dictId);

		JSONObject obj = new JSONObject();
		obj.put("dictName", dictName);
		obj.put("dictDescribe", dictDescribe);
		obj.put("dictId", dictId);
		return obj;
	}

	@RequestMapping("/editDict.action")
	@ResponseBody
	public JSONObject editDict(String dictName, String dictDescribe, String dictId) {// 编辑参数
		String res = null;
		boolean bo = parameterService.updateDict(dictName, dictDescribe, dictId);
		if (bo) {
			res = "操作成功！";
		} else {
			res = "操作失败！";
		}

		JSONObject obj = new JSONObject();
		obj.put("res", res);
		return obj;

	}

	/**
	 * 套餐id存入后端
	 * @param comboId
	 * @return
	 */
	@RequestMapping("/toSetDictId.action")
	@ResponseBody
	public JSONObject toSetComboId(HttpServletRequest req, String dictId) {
		req.getSession().setAttribute("dictId", dictId);
		JSONObject obj = new JSONObject();
		obj.put("res", "成功");
		return obj;
	}
}
