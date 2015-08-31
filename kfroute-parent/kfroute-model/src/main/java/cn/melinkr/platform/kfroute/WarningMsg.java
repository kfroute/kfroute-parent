package cn.melinkr.platform.kfroute;

import java.sql.Timestamp;

import cn.melinkr.platform.util.TimestampFormatUtil;

public class WarningMsg {
	private int warningId;
	private String warningEntity;
	private int warningType;
	private String warningCode;
	private String warningMsg;
	private String warningLevel;
	private Timestamp warningTime;
	private int resolveFlag;
	private Timestamp resolveTime;
	private String resolveNote;
	private String resolveBy;
	public int getWarningId() {
		return warningId;
	}
	public void setWarningId(int warningId) {
		this.warningId = warningId;
	}
	public String getWarningCode() {
		return warningCode;
	}
	public void setWarningCode(String warningCode) {
		this.warningCode = warningCode;
	}
	public String getWarningMsg() {
		return warningMsg;
	}
	public void setWarningMsg(String warningMsg) {
		this.warningMsg = warningMsg;
	}

	public void setWarningLevel(String warningLevel) {
		this.warningLevel = warningLevel;
	}
	public String getWarningTime() {
		return TimestampFormatUtil.format(warningTime);
	}
	public void setWarningTime(Timestamp warningTime) {
		this.warningTime = warningTime;
	}
	public int getResolveFlag() {
		return resolveFlag;
	}
	public void setResolveFlag(int resolveFlag) {
		this.resolveFlag = resolveFlag;
	}
	public String getResolveTime() {
		return TimestampFormatUtil.format(resolveTime);
	}
	public void setResolveTime(Timestamp resolveTime) {
		this.resolveTime = resolveTime;
	}
	public String getResolveNote() {
		return resolveNote;
	}
	public void setResolveNote(String resolveNote) {
		this.resolveNote = resolveNote;
	}
	public String getResolveBy() {
		return resolveBy;
	}
	public void setResolveBy(String resolveBy) {
		this.resolveBy = resolveBy;
	}
	
	public String getWarningEntity() {
		return warningEntity;
	}
	public void setWarningEntity(String warningEntity) {
		this.warningEntity = warningEntity;
	}
	public int getWarningType() {
		return warningType;
	}
	public void setWarningType(int warningType) {
		this.warningType = warningType;
	}
	public String getWarningLevel() {
		return warningLevel;
	}
	@Override
	public String toString() {
		return "WarningMsg [warningId=" + warningId + ", warningEntity="
				+ warningEntity + ", warningType=" + warningType
				+ ", warningCode=" + warningCode + ", warningMsg=" + warningMsg
				+ ", warningLevel=" + warningLevel + ", warningTime="
				+ warningTime + ", resolveFlag=" + resolveFlag
				+ ", resolveTime=" + resolveTime + ", resolveNote="
				+ resolveNote + ", resolveBy=" + resolveBy + "]";
	}

	

}
