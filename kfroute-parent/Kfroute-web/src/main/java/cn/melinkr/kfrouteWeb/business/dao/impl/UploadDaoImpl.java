package cn.melinkr.kfrouteWeb.business.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.melinkr.kfrouteWeb.business.dao.UploadDao;
import cn.melinkr.platform.kfroute.RouterMsg;

import com.melinkr.micro.auth.entity.ChnGroupMsg;
import com.tydic.framework.base.dao.EntityManagerImpl;

public class UploadDaoImpl extends EntityManagerImpl<RouterMsg, Integer>  implements UploadDao{
	private static final Logger logger = Logger
		.getLogger(UploadDaoImpl.class);
	@Override
	public Object findRouterMsgByMac(String mac) throws Exception {
		// TODO Auto-generated method stub
		try{
			Map map = new HashMap();
			map.put("mac", mac);
			return entityDao.findObj("routerManage.findDevice", map);
			//return this.queryForObject("routerManage.findDevice", map);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			return null;
		}
	}
	@Override
	public Object findSourceServerMsgByIp(String ip) throws Exception {
		// TODO Auto-generated method stub
		try{
			Map map = new HashMap();
			map.put("ipAddress", ip);
			return entityDao.findObj("serverManage.findServer", map);
			//return this.queryForObject("routerManage.findDevice", map);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			return null;
		}
	}
	@Override
	public ChnGroupMsg findGroupIdByName(String groupName) throws Exception {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("groupName", groupName);
		return (ChnGroupMsg)entityDao.findMap("routerManage.findGroupIdByName", map);
	}

}
