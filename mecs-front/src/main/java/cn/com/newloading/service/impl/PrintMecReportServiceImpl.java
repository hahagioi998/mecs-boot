package cn.com.newloading.service.impl;

import java.util.List;

import cn.com.newloading.dao.PrintMecReportMapper;
import cn.com.newloading.bean.*;
import cn.com.newloading.service.PrintMecReportService;
import cn.com.newloading.utils.MyUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;


@Service
public class PrintMecReportServiceImpl implements PrintMecReportService {

	@Autowired
	private PrintMecReportMapper printMecReporMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject getPrintMecReportInfo(String mrNum,String cardN) {
		JSONObject obj = new JSONObject();
		/*
		 * 获取用户的信息
		 */
		User user = printMecReporMapper.getUserByMrNum(mrNum);
		obj.put("user", user);
		/*
		 * 获取体检记录bean
		 */
		MecRecord mecRecord = printMecReporMapper.getMecRecordByMrNum(mrNum);
		obj.put("mecRecord", mecRecord);
		String guide = mecRecord.getMrGuide();
		guide = guide.replace("<img src=\"../", "<img src=\"" + MyUtil.imgPath);
		mecRecord.setMrGuide(guide);
		String req = mecRecord.getMrReq();
		req = req.replace("<img src=\"../", "<img src=\"" + MyUtil.imgPath);
		mecRecord.setMrReq(req);
		/*
		 * 获取相应的项目记录  List
		 */
		List<ItemsRecord> itemsList = printMecReporMapper.itemsList(mrNum);
		for (int i = 0; i < itemsList.size(); i++) {
			String irRes = itemsList.get(i).getItemsRes();
			if(irRes != null && !"".equals(irRes)) {
				irRes = irRes.replace("<img src=\"../", "<img src=\"" + MyUtil.imgPath);
				itemsList.get(i).setItemsRes(irRes);
			}
			
		}
		obj.put("itemsList", itemsList);
		
		/*
		 * 去修改体检记录状态
		 */
		Integer changeState= printMecReporMapper.changeMecRecordState(mrNum);
		
		/*
		 * 根据卡号获取用户ID
		 */
		String userId = printMecReporMapper.getUserIdByCardNum(cardN);
		/*
		 * 去插入数据库
		 */
		Log mgrLog = new Log(null, userId, "打印体检报告", "打印,体检号码为[" + mrNum + "]", null, null); 
		Integer addFrontLog = printMecReporMapper.addFrontLog(mgrLog);//  7
		
		if(changeState > 0 && addFrontLog > 0) {
			obj.put("res", "suc");
		}else {
			obj.put("res", "err");
		}
		return obj;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject getPrintMecReport(String mrNum) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		/*
		 * 获取用户的信息
		 */
		User user = printMecReporMapper.getUserByMrNum(mrNum);
		obj.put("user", user);
		/*
		 * 获取体检记录bean
		 */
		MecRecord mecRecord = printMecReporMapper.getMecRecordByMrNum(mrNum);
		obj.put("mecRecord", mecRecord);
		/*
		 * 获取相应的项目记录  List
		 */
		List<ItemsRecord> itemsList = printMecReporMapper.itemsList(mrNum);
		obj.put("itemsList", itemsList);
		
		return obj;
	}

}
