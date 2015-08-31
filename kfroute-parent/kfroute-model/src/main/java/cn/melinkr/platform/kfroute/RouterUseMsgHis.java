package cn.melinkr.platform.kfroute;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.melinkr.platform.util.TimestampFormatUtil;

public class RouterUseMsgHis {
	private RouterUseMsg routerUseMsg;
	private Timestamp hisTime;
	private String hisNote;
	private int hisType;
	private String yearMonth = new SimpleDateFormat("yyyyMM").format(new Date());
	
	
	
	
	public RouterUseMsg getRouterUseMsg() {
		return routerUseMsg;
	}
	public void setRouterUseMsg(RouterUseMsg routerUseMsg) {
		this.routerUseMsg = routerUseMsg;
	}
	
	public String getHisTime() {
		return TimestampFormatUtil.format(hisTime);
	}
	public void setHisTime(Timestamp hisTime) {
		this.hisTime = hisTime;
	}
	public String getHisNote() {
		return hisNote;
	}
	public void setHisNote(String hisNote) {
		this.hisNote = hisNote;
	}
	public int getHisType() {
		return hisType;
	}
	public void setHisType(int hisType) {
		this.hisType = hisType;
	}
	@Override
	public String toString() {
		return "RouterUseMsgHis [routerUseMsg=" + routerUseMsg + ", hisTime="
				+ hisTime + ", hisNote=" + hisNote + ", hisType=" + hisType
				+ ", yearMonth=" + yearMonth + "]";
	}
	
	
	

}
