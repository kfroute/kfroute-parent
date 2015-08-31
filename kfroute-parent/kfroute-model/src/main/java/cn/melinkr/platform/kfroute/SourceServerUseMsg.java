package cn.melinkr.platform.kfroute;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import cn.melinkr.platform.util.TimestampFormatUtil;

public class SourceServerUseMsg {
	private int useId;
	private int serverId;
	private Timestamp activeTimestamp;
	private Timestamp startTimestamp;
	private Timestamp shutdownTimestamp;
	private String ipAddress;
	private int sysStatus;
	private String cpuStatus;
	private String memStatus;
	private String diskStatus;
	private int currentConnNum;
	private long totalIncoming;
	private long totalOutgoing;
	private Timestamp updateTimestamp;
	
	
	public int getUseId() {
		return useId;
	}
	public void setUseId(int useId) {
		this.useId = useId;
	}
	public int getServerId() {
		return serverId;
	}
	public void setServerId(int serverId) {
		this.serverId = serverId;
	}
	public String getActiveTimestamp() {
		return TimestampFormatUtil.format(activeTimestamp);//activeTimestamp;
	}
	public void setActiveTimestamp(Timestamp activeTimestamp) {
		this.activeTimestamp = activeTimestamp;
	}
	public String getStartTimestamp() {
		return TimestampFormatUtil.format(startTimestamp);//startTimestamp;
	}
	public void setStartTimestamp(Timestamp startTimestamp) {
		this.startTimestamp = startTimestamp;
	}
	public String getShutdownTimestamp() {
		return TimestampFormatUtil.format(shutdownTimestamp);//shutdownTimestamp;
	}
	public void setShutdownTimestamp(Timestamp shutdownTimestamp) {
		this.shutdownTimestamp = shutdownTimestamp;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public int getSysStatus() {
		return sysStatus;
	}
	public void setSysStatus(int sysStatus) {
		this.sysStatus = sysStatus;
	}
	public String getCpuStatus() {
		return cpuStatus;
	}
	public void setCpuStatus(String cpuStatus) {
		this.cpuStatus = cpuStatus;
	}
	public String getMemStatus() {
		return memStatus;
	}
	public void setMemStatus(String memStatus) {
		this.memStatus = memStatus;
	}
	public String getDiskStatus() {
		return diskStatus;
	}
	public void setDiskStatus(String diskStatus) {
		this.diskStatus = diskStatus;
	}
	public int getCurrentConnNum() {
		return currentConnNum;
	}
	public void setCurrentConnNum(int currentConnNum) {
		this.currentConnNum = currentConnNum;
	}
	public long getTotalIncoming() {
		return totalIncoming;
	}
	public void setTotalIncoming(long totalIncoming) {
		this.totalIncoming = totalIncoming;
	}
	public long getTotalOutgoing() {
		return totalOutgoing;
	}
	public void setTotalOutgoing(long totalOutgoing) {
		this.totalOutgoing = totalOutgoing;
	}
	
	public Timestamp getUpdateTimestamp() {
		return updateTimestamp;
	}
	public void setUpdateTimestamp(Timestamp updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}
	@Override
	public String toString() {
		return "SourceServerUseMsg [useId=" + useId + ", serverId=" + serverId
				+ ", activeTimestamp=" + activeTimestamp + ", startTimestamp="
				+ startTimestamp + ", shutdownTimestamp=" + shutdownTimestamp
				+ ", ipAddress=" + ipAddress + ", sysStatus=" + sysStatus
				+ ", cpuStatus=" + cpuStatus + ", memStatus=" + memStatus
				+ ", diskStatus=" + diskStatus + ", currentConnNum="
				+ currentConnNum + ", totalIncoming=" + totalIncoming
				+ ", totalOutgoing=" + totalOutgoing + ", updateTimestamp="
				+ updateTimestamp + "]";
	}
	
}
