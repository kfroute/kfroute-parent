package cn.melinkr.platform.kfroute;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @author: zhangyl
 * @time: 2015-07-09 16:40
 * @version: 1.0
 * 资源服务器日志上报对象，包含系统日志和端口日志
 *
 */
public class ServerLog implements Serializable{
	private ServerSysLog serverSysLog;
	private List<ServerPortSysLog> serverPortSysLogList;
	public List<ServerPortSysLog> getServerPortSysLogList() {
		return serverPortSysLogList;
	}
	public void setServerPortSysLogList(List<ServerPortSysLog> serverPortSysLogList) {
		this.serverPortSysLogList = serverPortSysLogList;
	}
	public ServerSysLog getServerSysLog() {
		return serverSysLog;
	}
	public void setServerSysLog(ServerSysLog serverSysLog) {
		this.serverSysLog = serverSysLog;
	}
	public static void main(String[] args){
		List<ServerLog> serverLogList = new ArrayList<ServerLog>();
		ServerLog serverLog = new ServerLog();
		ServerSysLog _serverSysLog = new ServerSysLog();
		_serverSysLog.setCircleTotalIncoming(343243234);
		_serverSysLog.setCircleTotalIncoming(343);
		
		List<ServerPortSysLog> _serverPortList = new ArrayList<ServerPortSysLog>();
		ServerPortSysLog _serverPort = new ServerPortSysLog();
		_serverPort.setServerIp("2.2.2.2");
		_serverPort.setServerId(200034);
		_serverPort.setServerPort(10034);
		_serverPort.setPortCircleTotalIncoming(34323423);
		_serverPort.setPortCircleTotalOutgoing(342342432);
		_serverPort.setPortPrevTotalIncoming(343242334);
		_serverPort.setPortPrevTotalOutgoing(3432432432L);
		_serverPort.setPortTotalIncoming(4323234232L);
		_serverPort.setPortTotalOutgoing(3432342432L);
		_serverPortList.add(_serverPort);
		serverLog.setServerSysLog(_serverSysLog);
		serverLog.setServerPortSysLogList(_serverPortList);
		serverLogList.add(serverLog);
		String msg=JSON.toJSONString(serverLogList);
		System.out.println(msg);
		
	}
	
	
	
}
