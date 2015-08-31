package cn.melinkr.platform.kfroute;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import cn.melinkr.platform.util.TimestampFormatUtil;

/**
 * @author: zhangyl
 * @time: 2015-07-07 19:10
 * @version: 1.0
 * 设备日志上报对象，对应表：wroutersyslog
 */
public class RouterSysLog implements Serializable{
	private long wLogId;
	private String mac;
	private String wanIp;
	private Timestamp startupTimestamp;
	private long remainedRam;
	private String resourceIp;
	private int resourcePort;
	private int clientConnNum;
	private int requestUrlNum;
	private long totalTraffic;
	private long totalForeignTraffic;
	private long prevTraffic;
	private long prevTotalForeignTraffic;
	private long circleTotalTraffic;
	private long circleForeignTotalTraffic;
	public long getwLogId() {
		return wLogId;
	}
	public void setwLogId(long wLogId) {
		this.wLogId = wLogId;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getWanIp() {
		return wanIp;
	}
	public void setWanIp(String wanIp) {
		this.wanIp = wanIp;
	}

	public String getStartupTimestamp() {
		return TimestampFormatUtil.format(startupTimestamp);//startupTimestamp;
	}
	public void setStartupTimestamp(Timestamp startupTimestamp) {
		this.startupTimestamp = startupTimestamp;
	}
	public long getRemainedRam() {
		return remainedRam;
	}
	public void setRemainedRam(long remainedRam) {
		this.remainedRam = remainedRam;
	}
	public String getResourceIp() {
		return resourceIp;
	}
	public void setResourceIp(String resourceIp) {
		this.resourceIp = resourceIp;
	}
	public int getResourcePort() {
		return resourcePort;
	}
	public void setResourcePort(int resourcePort) {
		this.resourcePort = resourcePort;
	}
	public int getClientConnNum() {
		return clientConnNum;
	}
	public void setClientConnNum(int clientConnNum) {
		this.clientConnNum = clientConnNum;
	}
	public int getRequestUrlNum() {
		return requestUrlNum;
	}
	public void setRequestUrlNum(int requestUrlNum) {
		this.requestUrlNum = requestUrlNum;
	}
	public long getTotalTraffic() {
		return totalTraffic;
	}
	public void setTotalTraffic(long totalTraffic) {
		this.totalTraffic = totalTraffic;
	}
	public long getTotalForeignTraffic() {
		return totalForeignTraffic;
	}
	public void setTotalForeignTraffic(long totalForeignTraffic) {
		this.totalForeignTraffic = totalForeignTraffic;
	}
	public long getPrevTraffic() {
		return prevTraffic;
	}
	public void setPrevTraffic(long prevTraffic) {
		this.prevTraffic = prevTraffic;
	}
	public long getPrevTotalForeignTraffic() {
		return prevTotalForeignTraffic;
	}
	public void setPrevTotalForeignTraffic(long prevTotalForeignTraffic) {
		this.prevTotalForeignTraffic = prevTotalForeignTraffic;
	}
	public long getCircleTotalTraffic() {
		return circleTotalTraffic;
	}
	public void setCircleTotalTraffic(long circleTotalTraffic) {
		this.circleTotalTraffic = circleTotalTraffic;
	}
	public long getCircleForeignTotalTraffic() {
		return circleForeignTotalTraffic;
	}
	public void setCircleForeignTotalTraffic(long circleForeignTotalTraffic) {
		this.circleForeignTotalTraffic = circleForeignTotalTraffic;
	}
	@Override
	public String toString() {
		return "WrouterSysLog [wLogId=" + wLogId + ", mac=" + mac + ", wanIp="
				+ wanIp + ", startupTimestamp=" + startupTimestamp
				+ ", remainedRam=" + remainedRam + ", resourceIp=" + resourceIp
				+ ", resourcePort=" + resourcePort + ", clientConnNum="
				+ clientConnNum + ", requestUrlNum=" + requestUrlNum
				+ ", totalTraffic=" + totalTraffic + ", totalForeignTraffic="
				+ totalForeignTraffic + ", prevTraffic=" + prevTraffic
				+ ", prevTotalForeignTraffic=" + prevTotalForeignTraffic
				+ ", circleTotalTraffic=" + circleTotalTraffic
				+ ", circleForeignTotalTraffic=" + circleForeignTotalTraffic
				+ "]";
	}
	
	
}
