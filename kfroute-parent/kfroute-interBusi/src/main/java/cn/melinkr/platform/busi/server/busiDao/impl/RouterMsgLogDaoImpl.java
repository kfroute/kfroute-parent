package cn.melinkr.platform.busi.server.busiDao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.melinkr.platform.busi.server.busiDao.IRouterMsgLogDao;
import cn.melinkr.platform.kfroute.RouterSysLog;
import cn.melinkr.platform.kfroute.RouterUseMsg;

import com.tydic.framework.base.dao.EntityManagerImpl;

public class RouterMsgLogDaoImpl extends EntityManagerImpl<RouterSysLog,Integer> implements IRouterMsgLogDao{
	private static final Logger logger = Logger
	.getLogger(RouterMsgLogDaoImpl.class);
	@Override
	public boolean saveRouteMsgLog(List<RouterSysLog> wrouterSysLog) throws SQLException {
		entityDao.saveBatch("routerMsgLog.insertWroutersyslog", wrouterSysLog.toArray());
		return true;
		
		
	}
	@Override
	public RouterUseMsg queryUseRouterMsgByMac(String routerMac) throws SQLException {
		// TODO Auto-generated method stub
		Map queryParam = new HashMap();
		queryParam.put("mac", routerMac);
		RouterUseMsg routerUseMsg = (RouterUseMsg)entityDao.findMap("routerUseMsg.queryUseRouterMsg", queryParam);
		return routerUseMsg;
	}
	@Override
	public boolean updateUseRouterMsg(RouterUseMsg routerUseMsg)
			throws SQLException {
		entityDao.update("routerUseMsg.updateUseRouterMsg", routerUseMsg);
		return true;
	}
}
