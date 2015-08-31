package cn.melinkr.platform.busi.server.busiDao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.melinkr.platform.busi.server.busiDao.IUploadWarningMsgDao;
import cn.melinkr.platform.kfroute.RouterUseMsg;
import cn.melinkr.platform.kfroute.WarningMsg;

import com.tydic.framework.base.dao.EntityManagerImpl;

public class UploadWarningMsgDaoImpl extends EntityManagerImpl<RouterUseMsg,Integer> implements IUploadWarningMsgDao{
	private static final Logger logger = Logger
	.getLogger(UploadWarningMsgDaoImpl.class);

	@Override
	public boolean insertWarningMsg(WarningMsg warningMsg) throws SQLException {
		// TODO Auto-generated method stub
		entityDao.update("warningMsg.insertWarningMsg", warningMsg);
		return true;
	}
	
}
