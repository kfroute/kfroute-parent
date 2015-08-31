package cn.melinkr.platform.kfroute;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import cn.melinkr.platform.util.TimestampFormatUtil;

public class SourceServerMsg {
	private Integer serverId;
	private String serverName;
	private int shadowSocksId = 1;//默认为1
	private String operSystem;
	private String sysKernel;
	private String fileHandles;
	private String cpuModel;
	private String cpuFreq;
	private Timestamp activeTimestamp;
	private Integer sysStatus;
	private String ipAddress;
	private String centerName;
	private String operator;
	private double longitude;
	private double latitude;
	private String location;
	private String bandWidth;
	private String rate;
	private String cityName;
	private Integer belongGroup;
	private String belongGroupName;
	private Timestamp updateTimestamp;
	private String updateBy;
	private Timestamp createTimestamp;
	private String createBy;
	private String createAccept;
	private String  opNote;
	private String note;

	public Integer getServerId() {
		return serverId;
	}
	public void setServerId(Integer serverId) {
		this.serverId = serverId;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public int getShadowSocksId() {
		return shadowSocksId;
	}
	public void setShadowSocksId(int shadowSocksId) {
		this.shadowSocksId = shadowSocksId;
	}
	public String getOperSystem() {
		return operSystem;
	}
	public void setOperSystem(String operSystem) {
		this.operSystem = operSystem;
	}
	public String getSysKernel() {
		return sysKernel;
	}
	public void setSysKernel(String sysKernel) {
		this.sysKernel = sysKernel;
	}
	public String getFileHandles() {
		return fileHandles;
	}
	public void setFileHandles(String fileHandles) {
		this.fileHandles = fileHandles;
	}
	public String getCpuModel() {
		return cpuModel;
	}
	public void setCpuModel(String cpuModel) {
		this.cpuModel = cpuModel;
	}
	public String getCpuFreq() {
		return cpuFreq;
	}
	public void setCpuFreq(String cpuFreq) {
		this.cpuFreq = cpuFreq;
	}
	public String getActiveTimestamp() {
		return TimestampFormatUtil.format(activeTimestamp);//activeTimestamp==null?null:activeTimestamp.toString();
	}
	public void setActiveTimestamp(Timestamp activeTimestamp) {
		this.activeTimestamp = activeTimestamp;
	}
	public Integer getSysStatus() {
		return sysStatus;
	}
	public void setSysStatus(Integer sysStatus) {
		this.sysStatus = sysStatus;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	
	
	
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getBandWidth() {
		return bandWidth;
	}
	public void setBandWidth(String bandWidth) {
		this.bandWidth = bandWidth;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public Integer getBelongGroup() {
		return belongGroup;
	}
	public void setBelongGroup(Integer belongGroup) {
		this.belongGroup = belongGroup;
	}
	public String getOpNote() {
		return opNote;
	}
	public void setOpNote(String opNote) {
		this.opNote = opNote;
	}
	
	public String getCreateTimestamp() {
		return TimestampFormatUtil.format(createTimestamp);//createTimestamp==null?"":createTimestamp.toString();
	}
	public void setCreateTimestamp(Timestamp createTimestamp) {
		this.createTimestamp = createTimestamp;
	}
	public String getCreateAccept() {
		return createAccept;
	}
	public void setCreateAccept(String createAccept) {
		this.createAccept = createAccept;
	}
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	public String getUpdateTimestamp() {
		return TimestampFormatUtil.format(updateTimestamp);
	}
	public void setUpdateTimestamp(Timestamp updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	public String getBelongGroupName() {
		return belongGroupName;
	}
	public void setBelongGroupName(String belongGroupName) {
		this.belongGroupName = belongGroupName;
	}
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	@Override
	public String toString() {
		return "SourceServerMsg [serverId=" + serverId + ", serverName="
				+ serverName + ", shadowSocksId=" + shadowSocksId
				+ ", operSystem=" + operSystem + ", sysKernel=" + sysKernel
				+ ", fileHandles=" + fileHandles + ", cpuModel=" + cpuModel
				+ ", cpuFreq=" + cpuFreq + ", activeTimestamp="
				+ activeTimestamp + ", sysStatus=" + sysStatus + ", ipAddress="
				+ ipAddress + ", centerName=" + centerName + ", operator="
				+ operator + ", longitude=" + longitude + ", latitude="
				+ latitude + ", location=" + location + ", bandWidth="
				+ bandWidth + ", rate=" + rate + ", belongGroup=" + belongGroup
				+ ", belongGroupName=" + belongGroupName + ", updateTimestamp="
				+ updateTimestamp + ", updateBy=" + updateBy
				+ ", createTimestamp=" + createTimestamp + ", createBy="
				+ createBy + ", createAccept=" + createAccept + ", opNote="
				+ opNote + ", note=" + note + "]";
	}

}
