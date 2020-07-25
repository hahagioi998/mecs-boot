package cn.com.newloading.bean.dto;

import java.util.List;

import cn.com.newloading.bean.ItemsRecord;
import cn.com.newloading.bean.MecRecord;
/**
 * 用于显示体检记录
 */
public class MecRecordDto extends MecRecord {

	private List<ItemsRecord> irList;//项目记录对象

	public MecRecordDto() {
		super();
	}

	public MecRecordDto(List<ItemsRecord> irList) {
		super();
		this.irList = irList;
	}

	public List<ItemsRecord> getIrList() {
		return irList;
	}

	public void setIrList(List<ItemsRecord> irList) {
		this.irList = irList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MecRecordDto [irList=");
		builder.append(irList);
		builder.append("]");
		return builder.toString();
	}
	
	
}
