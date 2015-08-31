package cn.melinkr.platform.kfroute;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author: zhangyl
 * @time: 2015-07-09 16:40
 * @version: 1.0
 * 资源服务器系统日志上报对象，对应表：wserversyslog
 */
public class ServerSysLog implements Serializable{
	private int slogId;
	private int serverId;
	private String serverIp;
	private String cpuStatus;
	private String memStatus;
	private String diskStatus;
	private int currentConnNum;
	private long totalIncoming;
	private long totalOutgoing;
	private long prevTotalIncoming;
	private long prevTotalOutgoing;
	private long circleTotalIncoming;
	private long circleTotalOutgoing;
	private int processStatus;
	private String processDetail;
	private Timestamp serverUpdateTimestamp;
	


	

	public int getSlogId() {
		return slogId;
	}

	public void setSlogId(int slogId) {
		this.slogId = slogId;
	}

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
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

	public long getPrevTotalIncoming() {
		return prevTotalIncoming;
	}

	public void setPrevTotalIncoming(long prevTotalIncoming) {
		this.prevTotalIncoming = prevTotalIncoming;
	}

	public long getPrevTotalOutgoing() {
		return prevTotalOutgoing;
	}

	public void setPrevTotalOutgoing(long prevTotalOutgoing) {
		this.prevTotalOutgoing = prevTotalOutgoing;
	}

	public long getCircleTotalIncoming() {
		return circleTotalIncoming;
	}

	public void setCircleTotalIncoming(long circleTotalIncoming) {
		this.circleTotalIncoming = circleTotalIncoming;
	}

	public long getCircleTotalOutgoing() {
		return circleTotalOutgoing;
	}

	public void setCircleTotalOutgoing(long circleTotalOutgoing) {
		this.circleTotalOutgoing = circleTotalOutgoing;
	}

	public int getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(int processStatus) {
		this.processStatus = processStatus;
	}

	public String getProcessDetail() {
		return processDetail;
	}

	public void setProcessDetail(String processDetail) {
		this.processDetail = processDetail;
	}

	public Timestamp getServerUpdateTimestamp() {
		return serverUpdateTimestamp;
	}

	public void setServerUpdateTimestamp(Timestamp serverUpdateTimestamp) {
		this.serverUpdateTimestamp = serverUpdateTimestamp;
	}

	@Override
	public String toString() {
		return "ServerSysLog [slogId=" + slogId + ", serverId=" + serverId
				+ ", serverIp=" + serverIp + ", cpuStatus=" + cpuStatus
				+ ", memStatus=" + memStatus + ", diskStatus=" + diskStatus
				+ ", currentConnNum=" + currentConnNum + ", totalIncoming="
				+ totalIncoming + ", totalOutgoing=" + totalOutgoing
				+ ", prevTotalIncoming=" + prevTotalIncoming
				+ ", prevTotalOutgoing=" + prevTotalOutgoing
				+ ", circleTotalIncoming=" + circleTotalIncoming
				+ ", circleTotalOutgoing=" + circleTotalOutgoing
				+ ", processStatus=" + processStatus + ", processDetail="
				+ processDetail + ", serverUpdateTimestamp="
				+ serverUpdateTimestamp + "]";
	}
	
	

}
