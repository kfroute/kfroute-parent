package cn.melinkr.platform.kfroute;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author: zhangyl
 * @time: 2015-07-09 16:40
 * @version: 1.0
 * 资源服务器端口日志上报对象，对应表：wserverportsyslog
 */
public class ServerPortSysLog implements Serializable{
	private int slogId;
	private int serverId;
	private String serverIp;
	private int ServerPort;
	private long portTotalIncoming;
	private long portTotalOutgoing;
	private long portPrevTotalIncoming;
	private long portPrevTotalOutgoing;
	private long portCircleTotalIncoming;
	private long portCircleTotalOutgoing;
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
	public int getServerPort() {
		return ServerPort;
	}
	public void setServerPort(int serverPort) {
		ServerPort = serverPort;
	}
	public long getPortTotalIncoming() {
		return portTotalIncoming;
	}
	public void setPortTotalIncoming(long portTotalIncoming) {
		this.portTotalIncoming = portTotalIncoming;
	}
	public long getPortTotalOutgoing() {
		return portTotalOutgoing;
	}
	public void setPortTotalOutgoing(long portTotalOutgoing) {
		this.portTotalOutgoing = portTotalOutgoing;
	}
	public long getPortPrevTotalIncoming() {
		return portPrevTotalIncoming;
	}
	public void setPortPrevTotalIncoming(long portPrevTotalIncoming) {
		this.portPrevTotalIncoming = portPrevTotalIncoming;
	}
	public long getPortPrevTotalOutgoing() {
		return portPrevTotalOutgoing;
	}
	public void setPortPrevTotalOutgoing(long portPrevTotalOutgoing) {
		this.portPrevTotalOutgoing = portPrevTotalOutgoing;
	}
	public long getPortCircleTotalIncoming() {
		return portCircleTotalIncoming;
	}
	public void setPortCircleTotalIncoming(long portCircleTotalIncoming) {
		this.portCircleTotalIncoming = portCircleTotalIncoming;
	}
	public long getPortCircleTotalOutgoing() {
		return portCircleTotalOutgoing;
	}
	public void setPortCircleTotalOutgoing(long portCircleTotalOutgoing) {
		this.portCircleTotalOutgoing = portCircleTotalOutgoing;
	}
	public Timestamp getServerUpdateTimestamp() {
		return serverUpdateTimestamp;
	}
	public void setServerUpdateTimestamp(Timestamp serverUpdateTimestamp) {
		this.serverUpdateTimestamp = serverUpdateTimestamp;
	}
	@Override
	public String toString() {
		return "ServerPortSysLog [slogId=" + slogId + ", serverId=" + serverId
				+ ", serverIp=" + serverIp + ", ServerPort=" + ServerPort
				+ ", portTotalIncoming=" + portTotalIncoming
				+ ", portTotalOutgoing=" + portTotalOutgoing
				+ ", portPrevTotalIncoming=" + portPrevTotalIncoming
				+ ", portPrevTotalOutgoing=" + portPrevTotalOutgoing
				+ ", portCircleTotalIncoming=" + portCircleTotalIncoming
				+ ", portCircleTotalOutgoing=" + portCircleTotalOutgoing
				+ ", serverUpdateTimestamp=" + serverUpdateTimestamp + "]";
	}
	
	


}
