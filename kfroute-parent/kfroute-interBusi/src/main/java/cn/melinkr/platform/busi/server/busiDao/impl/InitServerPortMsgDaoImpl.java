package cn.melinkr.platform.busi.server.busiDao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.melinkr.platform.busi.server.busiDao.IInitServerPortMsgDao;
import cn.melinkr.platform.kfroute.ServerShadowSocksBean;
import cn.melinkr.platform.kfroute.SourceServerPortMsg;

import com.tydic.framework.base.dao.EntityManagerImpl;

public class InitServerPortMsgDaoImpl extends EntityManagerImpl<ServerShadowSocksBean,Integer> implements IInitServerPortMsgDao{
	private static final Logger logger = Logger
	.getLogger(InitServerPortMsgDaoImpl.class);

	@Override
	public ServerShadowSocksBean queryServerPortMsg(Map queryParam) throws SQLException {
		ServerShadowSocksBean serverShadowSocksBean = entityDao.findObj("serverInterMsg.findServerShadowSockMsg", queryParam);
		if(serverShadowSocksBean==null){
			throw new SQLException("FAIL-003~查询的服务器ip【"+queryParam.get("serverIp")+"】不存在或者没有配置ShadowSocks");
		}
		List<SourceServerPortMsg> sourceServerPortMsgList = (List<SourceServerPortMsg>)entityDao.find("serverInterMsg.findServerShadowSockPortMsg", queryParam);
		if(sourceServerPortMsgList==null||sourceServerPortMsgList.size()==0){
			throw new SQLException("FAIL-004~查询的服务器ip【"+queryParam.get("serverIp")+"】不存在或者没有可用端口");
		}
		Map portPasswordMap = new HashMap();
		for(SourceServerPortMsg sourceServerPortMsg:sourceServerPortMsgList){
			portPasswordMap.put(sourceServerPortMsg.getPort()+"", sourceServerPortMsg.getPassword());
		}
		serverShadowSocksBean.setPort_password(portPasswordMap);
		return serverShadowSocksBean;
		
	}
	
}
