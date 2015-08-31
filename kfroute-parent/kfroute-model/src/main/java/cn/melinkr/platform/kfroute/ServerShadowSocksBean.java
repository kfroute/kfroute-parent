package cn.melinkr.platform.kfroute;

import java.util.Map;

/**
 * 
 * @author: zhangyl
 * @time: 2015-08-09 23:22
 * @version: 1.0
 * 服务器端shadowSocks的json配置对象
 */
public class ServerShadowSocksBean {
	private String server;
	private String local_address;
	private int local_port;
	private Map port_password;
	private int timeout;
	private String method;
	private boolean fast_open;
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getLocal_address() {
		return local_address;
	}
	public void setLocal_address(String local_address) {
		this.local_address = local_address;
	}
	public int getLocal_port() {
		return local_port;
	}
	public void setLocal_port(int local_port) {
		this.local_port = local_port;
	}
	public Map getPort_password() {
		return port_password;
	}
	public void setPort_password(Map port_password) {
		this.port_password = port_password;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public boolean getFast_open() {
		return fast_open;
	}
	public void setFast_open(boolean fast_open) {
		this.fast_open = fast_open;
	}
	@Override
	public String toString() {
		return "ServerShadowSocksBean [server=" + server + ", local_address="
				+ local_address + ", local_port=" + local_port
				+ ", port_password=" + port_password + ", timeout=" + timeout
				+ ", method=" + method + ", fast_open=" + fast_open + "]";
	}
	
	
}
