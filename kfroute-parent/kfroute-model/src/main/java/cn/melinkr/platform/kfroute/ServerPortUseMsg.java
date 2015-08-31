package cn.melinkr.platform.kfroute;

import java.sql.Timestamp;

import cn.melinkr.platform.util.TimestampFormatUtil;

/**
 * @author: zhangyl
 * @time: 2015-08-17 11:20
 * @version: 1.0
 * 资源服务器端口实时状态对象，对应表：dserverportusemsg
 */
public class ServerPortUseMsg {
	private int sLogId;
	private int serverId;
	private String serverIp;
	private int serverPort;
	private long portTotalOutgoing;//总出去流量
	private long portTotalIncoming;//总进入流量
	private int portStatus;
	private Timestamp openTimestamp;//端口开始使用时间，以路由器设备通知为准
	private Timestamp closeTimestamp;//端口关闭时间，如果15分钟没有收到路由器上报数据，则默认路由器下线
	private Timestamp portUpdateTimestamp;
	public int getsLogId() {
		return sLogId;
	}
	public void setsLogId(int sLogId) {
		this.sLogId = sLogId;
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
		return serverPort;
	}
	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}
	public long getPortTotalOutgoing() {
		return portTotalOutgoing;
	}
	public void setPortTotalOutgoing(long portTotalOutgoing) {
		this.portTotalOutgoing = portTotalOutgoing;
	}
	public long getPortTotalIncoming() {
		return portTotalIncoming;
	}
	public void setPortTotalIncoming(long portTotalIncoming) {
		this.portTotalIncoming = portTotalIncoming;
	}
	public int getPortStatus() {
		return portStatus;
	}
	public void setPortStatus(int portStatus) {
		this.portStatus = portStatus;
	}
	public String getOpenTimestamp() {
		return TimestampFormatUtil.format(openTimestamp);//openTimestamp;
	}
	public void setOpenTimestamp(Timestamp openTimestamp) {
		this.openTimestamp = openTimestamp;
	}
	public String getCloseTimestamp() {
		return TimestampFormatUtil.format(closeTimestamp);//closeTimestamp;
	}
	public void setCloseTimestamp(Timestamp closeTimestamp) {
		this.closeTimestamp = closeTimestamp;
	}
	public String getPortUpdateTimestamp() {
		return TimestampFormatUtil.format(portUpdateTimestamp);//portUpdateTimestamp;
	}
	public void setPortUpdateTimestamp(Timestamp portUpdateTimestamp) {
		this.portUpdateTimestamp = portUpdateTimestamp;
	}
	@Override
	public String toString() {
		return "ServerPortUseMsg [sLogId=" + sLogId + ", serverId=" + serverId
				+ ", serverIp=" + serverIp + ", serverPort=" + serverPort
				+ ", portTotalOutgoing=" + portTotalOutgoing
				+ ", portTotalIncoming=" + portTotalIncoming + ", portStatus="
				+ portStatus + ", openTimestamp=" + openTimestamp
				+ ", closeTimestamp=" + closeTimestamp
				+ ", portUpdateTimestamp=" + portUpdateTimestamp + "]";
	}
	
	

}
