package cn.com.newloading.bean.dto;

import cn.com.newloading.bean.Department;
import cn.com.newloading.bean.Dict;
import cn.com.newloading.bean.ItemsRecord;
import cn.com.newloading.bean.MecRecord;

public class ItemsRecordDto extends ItemsRecord{
	private Dict dict;
	private MecRecord mecr;
	private Department dep;
	public ItemsRecordDto() {
		// TODO Auto-generated constructor stub
	}
	public ItemsRecordDto(Dict dict, MecRecord mecr, Department dep) {
		super();
		this.dict = dict;
		this.mecr = mecr;
		this.dep = dep;
	}
	public Dict getDict() {
		return dict;
	}
	public void setDict(Dict dict) {
		this.dict = dict;
	}
	public MecRecord getMecr() {
		return mecr;
	}
	public void setMecr(MecRecord mecr) {
		this.mecr = mecr;
	}
	public Department getDep() {
		return dep;
	}
	public void setDep(Department dep) {
		this.dep = dep;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ItemsRecordDto [dict=");
		builder.append(dict);
		builder.append(", mecr=");
		builder.append(mecr);
		builder.append(", dep=");
		builder.append(dep);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	
	

}
