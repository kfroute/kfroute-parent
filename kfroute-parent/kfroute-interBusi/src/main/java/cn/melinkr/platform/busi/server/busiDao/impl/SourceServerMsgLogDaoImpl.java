package cn.melinkr.platform.busi.server.busiDao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.shiro.dao.DataAccessException;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

import cn.melinkr.platform.busi.server.busiDao.ISourceServerMsgLogDao;
import cn.melinkr.platform.kfroute.ServerLog;
import cn.melinkr.platform.kfroute.ServerPortSysLog;
import cn.melinkr.platform.kfroute.ServerPortUseMsg;
import cn.melinkr.platform.kfroute.ServerSysLog;
import cn.melinkr.platform.kfroute.SourceServerUseMsg;

import com.tydic.framework.base.dao.EntityManagerImpl;

public class SourceServerMsgLogDaoImpl extends EntityManagerImpl<ServerSysLog,Integer> implements ISourceServerMsgLogDao{
	private static final Logger logger = Logger
	.getLogger(SourceServerMsgLogDaoImpl.class);
	 /**
     * 记录的主键生成器
     */
    protected DataFieldMaxValueIncrementer serverLogId;
	@Override
	public boolean saveServerMsgLog(List<ServerLog> serverLogList) throws SQLException {
		try{
			for(ServerLog serverLog:serverLogList){
				if(serverLog==null){//空日志记录不做处理
					continue;
				}
				int id = serverLogId.nextIntValue();
				ServerSysLog serverSysLog = serverLog.getServerSysLog();
				serverSysLog.setSlogId(id);
				List<ServerPortUseMsg> serverPortUseMsgList = new ArrayList<ServerPortUseMsg>();
				for(ServerPortSysLog portLog:serverLog.getServerPortSysLogList()){
					portLog.setSlogId(id);
					ServerPortUseMsg serverPortUseMsg = new ServerPortUseMsg();
					serverPortUseMsg.setServerIp(serverSysLog.getServerIp());
					serverPortUseMsg.setServerPort(portLog.getServerPort());
					serverPortUseMsg.setPortTotalIncoming(portLog.getPortTotalIncoming());
					serverPortUseMsg.setPortTotalOutgoing(portLog.getPortTotalOutgoing());
					serverPortUseMsg.setPortStatus(1);
					serverPortUseMsg.setPortUpdateTimestamp(new Timestamp(System.currentTimeMillis()));
					serverPortUseMsgList.add(serverPortUseMsg);
				}
				Map queryServerParam = new HashMap();
				queryServerParam.put("serverIp", serverSysLog.getServerIp());
				SourceServerUseMsg sourceServerUseMsg = (SourceServerUseMsg)entityDao.findMap("serverUseMsg.findSourceServerUseMsg", queryServerParam);
				if(sourceServerUseMsg==null){//未查询到服务器实时记录，插入一条新的记录，正常情况下不会出现，服务器启动时上报状态时，就会生成这个信息
					sourceServerUseMsg = new SourceServerUseMsg();
					sourceServerUseMsg.setActiveTimestamp(new Timestamp(System.currentTimeMillis()));
					sourceServerUseMsg.setStartTimestamp(new Timestamp(System.currentTimeMillis()));
					sourceServerUseMsg.setIpAddress(serverSysLog.getServerIp());
					sourceServerUseMsg.setSysStatus(1);
					sourceServerUseMsg.setCpuStatus(serverSysLog.getCpuStatus());
					sourceServerUseMsg.setMemStatus(serverSysLog.getMemStatus());
					sourceServerUseMsg.setDiskStatus(serverSysLog.getDiskStatus());
					sourceServerUseMsg.setCurrentConnNum(serverSysLog.getCurrentConnNum());
					sourceServerUseMsg.setTotalIncoming(serverSysLog.getTotalIncoming());
					sourceServerUseMsg.setTotalOutgoing(serverSysLog.getTotalOutgoing());
					entityDao.save("serverUseMsg.insertServerUseMsg", sourceServerUseMsg);
				}else{
					sourceServerUseMsg.setSysStatus(1);
					sourceServerUseMsg.setCpuStatus(serverSysLog.getCpuStatus());
					sourceServerUseMsg.setMemStatus(serverSysLog.getMemStatus());
					sourceServerUseMsg.setDiskStatus(serverSysLog.getDiskStatus());
					sourceServerUseMsg.setCurrentConnNum(serverSysLog.getCurrentConnNum());
					sourceServerUseMsg.setTotalIncoming(serverSysLog.getTotalIncoming());
					sourceServerUseMsg.setTotalOutgoing(serverSysLog.getTotalOutgoing());
					sourceServerUseMsg.setUpdateTimestamp(new Timestamp(System.currentTimeMillis()));
					entityDao.save("serverUseMsg.updateServerUseMsg", sourceServerUseMsg);
				}
				entityDao.save("serverMsgLog.insertServersyslog", serverLog.getServerSysLog());
				entityDao.saveBatch("serverMsgLog.insertServerPortsyslog", serverLog.getServerPortSysLogList().toArray());
				entityDao.saveBatch("serverUseMsg.updateServerPortUseMsg", serverPortUseMsgList.toArray());
			}
		}catch(DataAccessException e){
			e.printStackTrace();
			throw new SQLException("FAIL-008~服务器序列号获取失败");
		}
		return true;
		
	}
	public DataFieldMaxValueIncrementer getServerLogId() {
		return serverLogId;
	}
	public void setServerLogId(DataFieldMaxValueIncrementer serverLogId) {
		this.serverLogId = serverLogId;
	}
	
}
