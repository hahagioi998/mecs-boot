package cn.com.newloading.controller;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.bean.dto.ItemsRecordDto;
import cn.com.newloading.service.BackMoneyService;
import cn.com.newloading.utils.IsNullUtil;
import cn.com.newloading.utils.LimitUtil;

import org.springframework.stereotype.Controller;
	/**
	 * 退费
 */

@Controller
public class BackMoenyController {
	
	@Autowired
	private BackMoneyService bs;
	/**
	 * 根据卡号去获取体检人信息
	 * @param cardNum
	 * @return
	 */
	@RequestMapping("/backMoney.action")
	@ResponseBody
	public JSONObject backMoney(HttpSession session,String cardNum,String itemsState,String currPage,String type,
			Integer cardItemsId ,String money) {
		JSONObject jo = new JSONObject();
		ArrayList<String > list1 = new ArrayList<>();
		list1.add(currPage);
		list1.add(cardNum);
		boolean bo = IsNullUtil.isNull(list1);
		//非法输入，输入卡号为空
		if (!bo) {
			return jo;
		}
		int beginPage = Integer.valueOf(currPage) * LimitUtil.BACKMONEYLIMIT - LimitUtil.BACKMONEYLIMIT ;
		if ("SELECT".equals(type)) {//启动页面查询表格数据
			//查询余额
			String balance  = bs.getBalanceByCard(cardNum);
			//查询用户名
			String userName = bs.getUserNameByCard(cardNum);
			//查询总记录数
			int count = bs.getItemsCountByCard(cardNum, itemsState);
			//查询集合
			ArrayList<ItemsRecordDto> list = bs.getItemsByCard(cardNum, itemsState, new RowBounds(beginPage, LimitUtil.BACKMONEYLIMIT));
			jo.put("balance", balance);
			jo.put("userName", userName);
			jo.put("count", count);
			jo.put("list", list);
			jo.put("limit", LimitUtil.BACKMONEYLIMIT);
			jo.put("ret", "1");
		}else if ("BACKMONEY".equals(type)) {//用户点了退费按钮
			//查询用户名
//			Admin ad = new Admin("101", "", "", "", "", "", "","" , "");
//			session.setAttribute("sessionAdminKey", ad);
			String userName = bs.getUserNameByCard(cardNum);
			jo = bs.modifyItemsStateAndBalanceById(session,cardItemsId, itemsState, cardNum, money, new RowBounds(beginPage,LimitUtil.BACKMONEYLIMIT));
		    jo.put("userName", userName);
		    
		}
		
		return jo;
		
	}
	
	

}
