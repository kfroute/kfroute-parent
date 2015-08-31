package cn.melinkr.platform.busi.server.busiDao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.melinkr.platform.busi.server.busiDao.IUploadTestMsgAndGetServerPortDao;
import cn.melinkr.platform.kfroute.RouterMsg;
import cn.melinkr.platform.kfroute.RouterUseMsg;
import cn.melinkr.platform.kfroute.RouterUseMsgHis;
import cn.melinkr.platform.kfroute.ServerPortUseMsg;
import cn.melinkr.platform.kfroute.ServerPortUseMsgHis;
import cn.melinkr.platform.kfroute.SourceServerPortMsg;

import com.tydic.framework.base.dao.EntityManagerImpl;

public class UploadTestMsgAndGetServerPortDaoImpl extends EntityManagerImpl<RouterUseMsg,Integer> implements IUploadTestMsgAndGetServerPortDao{
	private static final Logger logger = Logger
	.getLogger(UploadTestMsgAndGetServerPortDaoImpl.class);

	@Override
	public boolean insertUseRouterMsg(RouterUseMsg routerUseMsg) throws SQLException {
		entityDao.save("routerUseMsg.insertUseRouterMsg", routerUseMsg);
		return true;
		
	}
	@Override
	public boolean insertUseRouterMsgHis(RouterUseMsgHis routerUseMsgHis) throws SQLException {
		entityDao.save("routerUseMsg.insertUseRouterMsgHis", routerUseMsgHis);
		return true;
		
	}

	@Override
	public RouterUseMsg queryUseRouterMsgByMac(String routerMac) throws SQLException {
		// TODO Auto-generated method stub
		Map queryParam = new HashMap();
		queryParam.put("mac", routerMac);
		RouterUseMsg routerUseMsg = entityDao.findObj("routerUseMsg.queryUseRouterMsg", queryParam);
		return routerUseMsg;
	}

	@Override
	public boolean updateUseRouterMsg(RouterUseMsg routerUseMsg)
			throws SQLException {
		entityDao.update("routerUseMsg.updateUseRouterMsg", routerUseMsg);
		return true;
	}

	@Override
	public List<SourceServerPortMsg> queryUsableServerPortMsg(String servIp)
			throws SQLException {
		// TODO Auto-generated method stub
		Map queryParam = new HashMap();
		queryParam.put("serverIp", servIp);
		List<SourceServerPortMsg> sourceServerPortMsgList = (List<SourceServerPortMsg>)entityDao.find("serverInterMsg.findUsableServerShadowSockPortMsg", queryParam);;
		return sourceServerPortMsgList;
	}

	@Override
	public RouterMsg queryRouterBaseMsgByMac(String routerMac)
			throws SQLException {
		// TODO Auto-generated method stub
		Map queryParam = new HashMap();
		queryParam.put("mac", routerMac);
		RouterMsg routerMsg = (RouterMsg)(entityDao.findMap("routerUseMsg.queryBaseRouterMsg", queryParam));
		return routerMsg;
	}
	
	@Override
	public ServerPortUseMsg queryUseServerPortMsgByIpPort(String serverIp,
			int serverPort) throws SQLException {
		// TODO Auto-generated method stub
		Map queryParam = new HashMap();
		queryParam.put("serverIp", serverIp);
		queryParam.put("serverPort", serverPort);
		ServerPortUseMsg serverPortUseMsg = (ServerPortUseMsg)entityDao.findMap("routerUseMsg.queryUseServerPortMsg", queryParam);
		return serverPortUseMsg;
	}
	@Override
	public boolean saveUseServerPortMsg(ServerPortUseMsg serverPortUseMsg)
			throws SQLException {
		entityDao.save("routerUseMsg.insertUseServerPortMsg", serverPortUseMsg);
		return true;
	}
	@Override
	public boolean saveUseServerPortMsgHis(
			ServerPortUseMsgHis serverPortUseMsgHis) throws SQLException {
		// TODO Auto-generated method stub
		entityDao.save("routerUseMsg.insertUseServerPortMsgHis", serverPortUseMsgHis);
		return true;
	}
	@Override
	public boolean updateUseServerPortMsg(ServerPortUseMsg serverPortUseMsg)
			throws SQLException {
		// TODO Auto-generated method stub
		entityDao.update("routerUseMsg.updateServerPortUseMsg", serverPortUseMsg);
		return true;
	}
	
}
