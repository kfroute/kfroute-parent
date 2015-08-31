package cn.melinkr.platform.kfroute;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.melinkr.platform.util.TimestampFormatUtil;

public class SourceServerUseMsgHis {
	private SourceServerUseMsg sourceServerUseMsg;
	private Timestamp hisTime;
	private String hisNote;
	private int hisType;
	private String yearMonth = new SimpleDateFormat("yyyyMM").format(new Date());
	public SourceServerUseMsg getSourceServerUseMsg() {
		return sourceServerUseMsg;
	}
	public void setSourceServerUseMsg(SourceServerUseMsg sourceServerUseMsg) {
		this.sourceServerUseMsg = sourceServerUseMsg;
	}
	public Timestamp getHisTime() {
		return hisTime;
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
		return "SourceServerUseMsgHis [sourceServerUseMsg="
				+ sourceServerUseMsg + ", hisTime=" + hisTime + ", hisNote="
				+ hisNote + ", hisType=" + hisType + ", yearMonth=" + yearMonth
				+ "]";
	}
	
	
}
