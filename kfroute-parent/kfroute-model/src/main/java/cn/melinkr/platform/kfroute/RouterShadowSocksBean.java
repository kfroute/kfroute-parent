package cn.melinkr.platform.kfroute;

import java.util.Map;

/**
 * 
 * @author: zhangyl
 * @time: 2015-08-09 23:22
 * @version: 1.0
 * 服务器端shadowSocks的json配置对象
 */
public class RouterShadowSocksBean {
	private String server;
	private int server_port;
	private int local_port;
	private String password;
	private String method;
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public int getServer_port() {
		return server_port;
	}
	public void setServer_port(int server_port) {
		this.server_port = server_port;
	}
	public int getLocal_port() {
		return local_port;
	}
	public void setLocal_port(int local_port) {
		this.local_port = local_port;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	@Override
	public String toString() {
		return "RouterShadowSocksBean [server=" + server + ", server_port="
				+ server_port + ", local_port=" + local_port + ", password="
				+ password + ", method=" + method + "]";
	}

	
}
