package cn.melinkr.platform.kfroute;

public class ServerPortPool {
	private long portId;
	private int portValue;
	public long getPortId() {
		return portId;
	}
	public void setPortId(long portId) {
		this.portId = portId;
	}
	public int getPortValue() {
		return portValue;
	}
	public void setPortValue(int portValue) {
		this.portValue = portValue;
	}
	@Override
	public String toString() {
		return "ServerPortPool [portId=" + portId + ", portValue=" + portValue
				+ "]";
	}
	
	
}
