package cn.melinkr.platform.kfroute;

import java.sql.Timestamp;

/**
 * @author: joki
 * @time: 2015-08-07 16:25
 * @version: 1.0
 * 资源服务器端口信息对象
 *
 */
public class SourceServerPortMsg {
	private int relationId;
	private int sourceId;
	private String sourceIp;
	private int port;
	private String password;
	private Integer useFlag;//端口启用状态
	private Integer timeOut;//连接超时时间设置
	private Timestamp updateTime;
	private String updateDesc;
	private Integer runStatus;
	private Timestamp activeTime;
	
	public int getRelationId() {
		return relationId;
	}
	public void setRelationId(int relationId) {
		this.relationId = relationId;
	}
	public int getSourceId() {
		return sourceId;
	}
	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}
	public String getSourceIp() {
		return sourceIp;
	}
	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getUseFlag() {
		return useFlag;
	}
	public void setUseFlag(Integer useFlag) {
		this.useFlag = useFlag;
	}
	public Integer getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(Integer timeOut) {
		this.timeOut = timeOut;
	}
	public String getUpdateTime() {
		return updateTime==null?null:updateTime.toString();
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateDesc() {
		return updateDesc;
	}
	public void setUpdateDesc(String updateDesc) {
		this.updateDesc = updateDesc;
	}
	public Integer getRunStatus() {
		return runStatus;
	}
	public void setRunStatus(Integer runStatus) {
		this.runStatus = runStatus;
	}
	public Timestamp getActiveTime() {
		return activeTime;
	}
	public void setActiveTime(Timestamp activeTime) {
		this.activeTime = activeTime;
	}
	@Override
	public String toString() {
		return "SourceServerPortMsg [relationId=" + relationId + ", sourceId="
				+ sourceId + ", sourceIp=" + sourceIp + ", port=" + port
				+ ", password=" + password + ", useFlag=" + useFlag
				+ ", timeOut=" + timeOut + ", updateTime=" + updateTime
				+ ", updateDesc=" + updateDesc + ", runStatus=" + runStatus
				+ ", activeTime=" + activeTime + "]";
	}
	
	
	

}
