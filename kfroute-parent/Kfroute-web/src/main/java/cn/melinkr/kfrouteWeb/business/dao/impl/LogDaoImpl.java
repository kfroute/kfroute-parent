package cn.melinkr.kfrouteWeb.business.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.melinkr.kfrouteWeb.business.dao.LogDao;
import cn.melinkr.platform.kfroute.LoginOpr;
import cn.melinkr.platform.kfroute.SelectFormBean;

import com.tydic.framework.base.dao.EntityManagerImpl;

public class LogDaoImpl extends EntityManagerImpl<LoginOpr, Integer>  implements LogDao{
	private static final Logger logger = Logger
		.getLogger(LogDaoImpl.class);
	
	
	@Override
	public void saveOperLog(LoginOpr loginOpr) throws SQLException {
		// TODO Auto-generated method stub
		entityDao.save("routerCommon.insertLoginOpr",loginOpr);
	}

}
