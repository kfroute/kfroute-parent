package cn.melinkr.platform.kfroute;

public class ServerAvailableIp {
	private String serverIp;
	private double longitude;
	private double latitude;
	private int belongGroup;
	private String distance;
	public String getServerIp() {
		return serverIp;
	}
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
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
	public int getBelongGroup() {
		return belongGroup;
	}
	public void setBelongGroup(int belongGroup) {
		this.belongGroup = belongGroup;
	}
	
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	@Override
	public String toString() {
		return "ServerAvailableIp [serverIp=" + serverIp + ", longitude="
				+ longitude + ", latitude=" + latitude + ", belongGroup="
				+ belongGroup + ", distance=" + distance + "]";
	}
	
	
}
