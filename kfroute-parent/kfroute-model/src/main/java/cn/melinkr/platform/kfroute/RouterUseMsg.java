package cn.melinkr.platform.kfroute;

public class RouterUseMsg {
	private int useId;
	private int routerId;
	private String mac;
	private String activeTime;
	private String onlineTimestamp;
	private Double longitude;
	private Double latitude;
	private String wanIp;
	private String realIp;
	private String resourceIp;
	private String resourcePort;
	private String pingVal;
	private String pkgLossRate;
	private String speed;
	private String totalTraffic;
	private String totalForeignTraffic;
	private int runStatus;
	private String offlineTimestamp;
	private String updateTimestamp;
	
	public int getUseId() {
		return useId;
	}
	public void setUseId(int useId) {
		this.useId = useId;
	}
	public int getRouterId() {
		return routerId;
	}
	public void setRouterId(int routerId) {
		this.routerId = routerId;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getActiveTime() {
		return activeTime;
	}
	public void setActiveTime(String activeTime) {
		this.activeTime = activeTime;
	}
	public String getOnlineTimestamp() {
		return onlineTimestamp;
	}
	public void setOnlineTimestamp(String onlineTimestamp) {
		this.onlineTimestamp = onlineTimestamp;
	}
	public String getWanIp() {
		return wanIp;
	}
	public void setWanIp(String wanIp) {
		this.wanIp = wanIp;
	}
	public String getResourceIp() {
		return resourceIp;
	}
	public void setResourceIp(String resourceIp) {
		this.resourceIp = resourceIp;
	}
	public String getResourcePort() {
		return resourcePort;
	}
	public void setResourcePort(String resourcePort) {
		this.resourcePort = resourcePort;
	}
	public String getPingVal() {
		return pingVal;
	}
	public void setPingVal(String pingVal) {
		this.pingVal = pingVal;
	}
	public String getPkgLossRate() {
		return pkgLossRate;
	}
	public void setPkgLossRate(String pkgLossRate) {
		this.pkgLossRate = pkgLossRate;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getTotalTraffic() {
		return totalTraffic;
	}
	public void setTotalTraffic(String totalTraffic) {
		this.totalTraffic = totalTraffic;
	}
	public String getTotalForeignTraffic() {
		return totalForeignTraffic;
	}
	public void setTotalForeignTraffic(String totalForeignTraffic) {
		this.totalForeignTraffic = totalForeignTraffic;
	}
	public String getOfflineTimestamp() {
		return offlineTimestamp;
	}
	public void setOfflineTimestamp(String offlineTimestamp) {
		this.offlineTimestamp = offlineTimestamp;
	}
	public String getUpdateTimestamp() {
		return updateTimestamp;
	}
	public void setUpdateTimestamp(String updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}
	
	
	
	public int getRunStatus() {
		return runStatus;
	}
	public void setRunStatus(int runStatus) {
		this.runStatus = runStatus;
	}
	public String getRealIp() {
		return realIp;
	}
	public void setRealIp(String realIp) {
		this.realIp = realIp;
	}
	
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	@Override
	public String toString() {
		return "RouterUseMsg [useId=" + useId + ", routerId=" + routerId
				+ ", mac=" + mac + ", activeTime=" + activeTime
				+ ", onlineTimestamp=" + onlineTimestamp + ", longitude="
				+ longitude + ", latitude=" + latitude + ", wanIp=" + wanIp
				+ ", realIp=" + realIp + ", resourceIp=" + resourceIp
				+ ", resourcePort=" + resourcePort + ", pingVal=" + pingVal
				+ ", pkgLossRate=" + pkgLossRate + ", speed=" + speed
				+ ", totalTraffic=" + totalTraffic + ", totalForeignTraffic="
				+ totalForeignTraffic + ", runStatus=" + runStatus
				+ ", offlineTimestamp=" + offlineTimestamp
				+ ", updateTimestamp=" + updateTimestamp + "]";
	}
	

}
