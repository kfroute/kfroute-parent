package cn.melinkr.platform.busi.server.busiDao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.melinkr.platform.busi.server.busiDao.IQueryAvailableServerIpDao;
import cn.melinkr.platform.kfroute.ServerAvailableIp;

import com.tydic.framework.base.dao.EntityManagerImpl;

public class QueryAvailableServerIpDaoImpl extends EntityManagerImpl<ServerAvailableIp,Integer> implements IQueryAvailableServerIpDao{
	private static final Logger logger = Logger
	.getLogger(QueryAvailableServerIpDaoImpl.class);


	@Override
	public List<ServerAvailableIp> queryServerIpList(Map paramMap) throws SQLException {
		// TODO Auto-generated method stub
		List<ServerAvailableIp>  ipList= entityDao.findBy("serverInterMsg.findServerAvailableIp", paramMap);
		return ipList;
	
	}
	
}
