package cn.melinkr.platform.kfroute;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.melinkr.platform.util.TimestampFormatUtil;

public class SourceServerMsgHis {
	private SourceServerMsg sourceServerMsg;
	private Timestamp hisTime;
	private String hisNote;
	private int hisType;
	private int hisBy;

	
	
	
	
	public SourceServerMsg getSourceServerMsg() {
		return sourceServerMsg;
	}
	public void setSourceServerMsg(SourceServerMsg sourceServerMsg) {
		this.sourceServerMsg = sourceServerMsg;
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
	public int getHisBy() {
		return hisBy;
	}
	public void setHisBy(int hisBy) {
		this.hisBy = hisBy;
	}
	
	
	public int getHisType() {
		return hisType;
	}
	public void setHisType(int hisType) {
		this.hisType = hisType;
	}
	@Override
	public String toString() {
		return "SourceServerMsgHis [sourceServerMsg=" + sourceServerMsg
				+ ", hisTime=" + hisTime + ", hisNote=" + hisNote
				+ ", hisType=" + hisType + ", hisBy=" + hisBy + "]";
	}
	

}
