package cn.com.newloading.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.bean.Admin;
import cn.com.newloading.service.MecSummaryService;

/**
 * 体检总结
 */
@Controller
public class MecSummaryController {
	/**
	 * 获取 用户，项目记录信息
	 */
	@Autowired
	private MecSummaryService bs;
	@RequestMapping("/getInfo.action")
	@ResponseBody
	public  JSONObject getInfoByMecNum(String mecNum) {
		JSONObject jo = new JSONObject();
		jo = bs.getAllInfo(mecNum);
		return jo;
	}
	/**
	 * 通过项目记录id拿到体检小结
	 * @param irId
	 * @return
	 */
	@RequestMapping("/getResult.action")
	@ResponseBody
	public  JSONObject getResult(HttpServletRequest req) {
		JSONObject jo = new JSONObject();
		String irId = req.getSession().getAttribute("irId").toString();
		jo = bs.getResult(irId);
		return jo;
	}
	/**
	 * 通过 体检号码 插入 体检总结
	 * @param irId
	 * @return
	 */
	@RequestMapping("/summaryLast.action")
	@ResponseBody
	public  JSONObject summaryLast(HttpSession session,String mecNum ,String req,String guide ) {
		Admin ad=(Admin) session.getAttribute("sessionAdminKey");
		String docId = ad.getAdminId();
		String docName = ad.getAdminName();
		JSONObject ob = new JSONObject();
		ob = bs.addResult(docName,docId, mecNum, req, guide);
		return ob;
	}

	/**
	 * 科室id存入后端
	 * 
	 * @param adId
	 * @return
	 */
	@RequestMapping("/toSetIrId.action")
	@ResponseBody
	public JSONObject toSetIrId(HttpServletRequest req, String irId) {
		req.getSession().setAttribute("irId", irId);
		JSONObject obj = new JSONObject();
		obj.put("res", "成功");
		return obj;
	}
}
